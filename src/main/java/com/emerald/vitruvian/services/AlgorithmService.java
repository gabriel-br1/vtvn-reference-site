package com.emerald.vitruvian.services;

import com.emerald.vitruvian.Entities.AlgorithmProfileEntity;
import com.emerald.vitruvian.Entities.ImageEntryEntity;
import com.emerald.vitruvian.Entities.UserEntity;
import com.emerald.vitruvian.mappers.AlgorithmMapper;
import com.emerald.vitruvian.models.AlgorithmProfileDTO;
import com.emerald.vitruvian.repositories.AlgorithmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class AlgorithmService {

    @Autowired
    private AlgorithmRepo algorithmRepo;

    @Autowired
    private AlgorithmMapper algorithmMapper;

    public void add(AlgorithmProfileDTO profile){
        algorithmRepo.save(algorithmMapper.toEntity(profile));
    }

    public void edit(AlgorithmProfileDTO profile, UserEntity user){
        AlgorithmProfileEntity oldProfile = user.getAlgorithmProfile();
        oldProfile.setTagsByFrequency(profile.getTagsByFrequency());
        AlgorithmProfileEntity newProfile = new AlgorithmProfileEntity();
        algorithmMapper.updateAlgorithmProfileEntity(oldProfile, newProfile);
        algorithmRepo.save(newProfile);
    }

    public void updateAlgorithmProfile(UserEntity userEntity, ImageEntryEntity imageEntryEntity) {

        if(userEntity != null){
            String savedTags = userEntity.getAlgorithmProfile().getTagsByFrequency();
            String tagsString = "";
            if(savedTags != null){
                tagsString = savedTags;
            }

            String combinedTags = imageEntryEntity.getTags() + " " + tagsString;
            String[] updatedTagsByFrequency = orderTags(combinedTags).toArray(new String[0]);
            AlgorithmProfileDTO newProfile = algorithmMapper.toDTO(userEntity.getAlgorithmProfile());
            newProfile.setTagsByFrequency(String.join(" ", updatedTagsByFrequency));
            edit(newProfile, userEntity);

        }

    }

    private List<String> orderTags(String combinedTags){
        HashMap<String, Integer> tagNumber = new HashMap<>();
        List<String> testForDuplicateTags = new ArrayList<>();
        int tagCount = 0;
        for(String tag : combinedTags.split(" ")){
            if( tagCount >= 100){
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

        // adds just one kind of tag multiple times???
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

}
