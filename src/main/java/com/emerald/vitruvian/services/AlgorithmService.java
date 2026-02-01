package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.AlgorithmProfileEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.AlgorithmMapper;
import com.emerald.vitruvian.models.AlgorithmProfileDTO;
import com.emerald.vitruvian.models.ImageEntryDTO;
import com.emerald.vitruvian.repositories.AlgorithmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlgorithmService {

    @Autowired
    private AlgorithmRepo algorithmRepo;

    @Autowired
    private AlgorithmMapper algorithmMapper;

    private final int ALGORITHM_STORED_TAG_AMOUNT = 20;

    public void add(AlgorithmProfileDTO profile){
        algorithmRepo.save(algorithmMapper.toEntity(profile));
    }

    public void edit(AlgorithmProfileDTO profile, UserEntity user){
        AlgorithmProfileEntity oldProfile = user.getAlgorithmProfile();
        oldProfile.setTagsByFrequency(profile.getTagsByFrequency());
        oldProfile.setImagesInAlgorithm(profile.getImagesInAlgorithm());
        AlgorithmProfileEntity newProfile = new AlgorithmProfileEntity();
        algorithmMapper.updateAlgorithmProfileEntity(oldProfile, newProfile);
        algorithmRepo.save(newProfile);
    }

    // first checks whether the image whose tags were about to be added to the algorithm was already used, saving an unchanged version of the algorithm entity if so
    /* if it wasn't found, it is added to the ImagesInAlgorithm column of the algorithm profile and the orderTags method is called with the combined tags of the
    current image and the already existing algorithm tags */
    public void updateAlgorithmProfile(UserEntity userEntity, ImageEntryEntity imageEntryEntity) {

        if(userEntity != null){
            String savedTags = userEntity.getAlgorithmProfile().getTagsByFrequency();
            String tagsString = "";
            if(savedTags != null){
                tagsString = savedTags;
            }

            List<Long> idsInAlgorithm = new ArrayList<>();

            if(userEntity.getAlgorithmProfile().getImagesInAlgorithm() != null){
                idsInAlgorithm = userEntity.getAlgorithmProfile().getImagesInAlgorithm();
            }

            boolean imageInAlgorithm = false;
            if(idsInAlgorithm != null){
                for(long id : idsInAlgorithm){
                    if(id == imageEntryEntity.getImageId()){
                        imageInAlgorithm = true;
                    }
                }
            }

            AlgorithmProfileDTO newProfile = algorithmMapper.toDTO(userEntity.getAlgorithmProfile());

            if(!imageInAlgorithm){
                idsInAlgorithm.add(imageEntryEntity.getImageId());
                newProfile.setImagesInAlgorithm(idsInAlgorithm);
                String combinedTags = imageEntryEntity.getTags() + " " + tagsString;
                String[] updatedTagsByFrequency = orderTags(combinedTags).toArray(new String[0]);
                newProfile.setTagsByFrequency(String.join(" ", updatedTagsByFrequency));
            }

            edit(newProfile, userEntity);

        }

    }

    /* first checks for duplicate tags in the parameter String, adding it into the hashmap if unique as the key and how often it appears in the String as the value
    (using the index variable) */
    /* then it sorts the values in a list of Integers in descending order before checking the hashmap`s tag values against the sorted Integer list and adding the tags
    the number of times specified by hashmap`s tag values (how often the tag appears in the original String) to the new List of Strings that is then returned */
    private List<String> orderTags(String combinedTags){
        HashMap<String, Integer> tagNumber = new HashMap<>();
        List<String> testForDuplicateTags = new ArrayList<>();
        int tagCount = 0;
        for(String tag : combinedTags.split(" ")){
            if( tagCount >= ALGORITHM_STORED_TAG_AMOUNT){
                break;
            }
            boolean foundDuplicate = false;
            int index = 1;
            for(String savedTag : testForDuplicateTags){
                if(tag.equals(savedTag)){
                    foundDuplicate = true;
                }
            }
            if(foundDuplicate){
                continue;
            }

            // counts instances of specific tag
            for(String innerTag : combinedTags.split(" ")){
                if(tag.equals(innerTag)){
                    tagNumber.put(tag, index);
                    index++;
                    tagCount++;
                }
            }
            testForDuplicateTags.add(tag);
        }

        List<Integer> numbersOfTags = new ArrayList<>();
        for(int num : tagNumber.values()){
            numbersOfTags.add(num);
        }

        numbersOfTags.sort(Comparator.reverseOrder());
        List<String> orderedTags = new ArrayList<>();

        testForDuplicateTags.clear();
        for(int tagNum : numbersOfTags){
            for(String key : tagNumber.keySet()){
                boolean foundDuplicate = false;
                for(String dupeTest : testForDuplicateTags){
                    if(dupeTest.equals(key)){
                        foundDuplicate = true;
                    }
                }
                if(tagNumber.get(key) == tagNum && !foundDuplicate){
                    for(int i = 0; i < tagNum; i++){
                        orderedTags.add(key);
                    }
                    testForDuplicateTags.add(key);
                    break;
                }
            }
        }
        return orderedTags;
    }

    // returns a list of all images with the ones in which at least one tag matches a tag in the algorithm profile moved to the top of the list
    public List<ImageEntryDTO> imagesAlgorithmOrder(UserEntity user, List<ImageEntryDTO> allImages) {
        AlgorithmProfileEntity algorithmProfile = user.getAlgorithmProfile();
        List<ImageEntryDTO> algorithmImages = new ArrayList<>();
        List<ImageEntryDTO> alreadyAddedImages = new ArrayList<>();
        HashSet<String> nonDuplicateTags = new HashSet<>(Arrays.asList(algorithmProfile.getTagsByFrequency().split(" ")));
        for(String tag : nonDuplicateTags){
            for(ImageEntryDTO image : allImages){
                boolean foundDupe = false;
                for(ImageEntryDTO dupeTest : alreadyAddedImages){
                    if(dupeTest.getImageId() == image.getImageId()){
                        foundDupe = true;
                    }
                }

                if(foundDupe){
                    continue;
                }

                for(String imageTag : image.getTags().split(" ")){
                    if(imageTag.equals(tag)){
                        algorithmImages.add(image);
                        alreadyAddedImages.add(image);
                        break;
                    }
                }
            }
        }

        for(ImageEntryDTO image : allImages){
            boolean foundDupe = false;
            for(ImageEntryDTO dupeImage : alreadyAddedImages){
                if(image.getImageId() == dupeImage.getImageId()){
                    foundDupe = true;
                }
            }
            if(!foundDupe){
                algorithmImages.add(image);
            }
        }
        return algorithmImages;
    }
}
