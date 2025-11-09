/* let tags = ["Character:Single",
  "Character:Multiple",
  "Shape:Masculine",
  "Shape:Feminine",
  "Shape:Androgynous",
  "Type:Large",
  "Type:Average",
  "Type:Thin",
  "Type:Lean",
  "Type:Muscular",
  "Pose:Sitting",
  "Pose:Kneeling",
  "Pose:Squatting",
  "Pose:Standing",
  "Pose:Lying",
  "Pose:Walking",
  "Pose:Running",
  "Pose:Jumping",
  "Clothing:Formal",
  "Clothing:Casual",
  "Clothing:Period",
  "Clothing:Futuristic",
  "Color:Warm",
  "Color:Cold",
  "Saturation:Pastel",
  "Saturation:Neon"];

  let tagsScenery = ["Nature:Mountain",
    "Nature:Forest",
    "Nature:Desert",
    "Nature:Plain",
    "Nature:Sea",
    "Nature:Sky",
    "Nature:River",
    "Color:Warm",
    "Color:Cold",
    "Structure:Small",
    "Structure:Medium",
    "Structure:Large",
    "Saturation:Pastel",
    "Saturation:Neon"];

  let n = tags.length; */

/*   function predictCharacter(value){
    document.getElementById('datalist').innerHTML = '';

    for(let i = 0; i < n; i++){
        const tagSplit = tags[i].split(":");
        const tag = tagSplit[1];

        if((tag.toLowerCase()).indexOf(value.toLowerCase()) > -1){
            let node = document.createElement("option");
            let val = document.createTextNode(tags[i]);
            node.appendChild(val);

            document.getElementById("datalist")
            .appendChild(node);
        }
    }
  } */

/*   function predictScenery(value){
      document.getElementById('datalist').innerHTML = '';

      for(let i = 0; i < n; i++){
          const tagSplit = tagsScenery[i].split(":");
          const tag = tagSplit[1];

          if((tag.toLowerCase()).indexOf(value.toLowerCase()) > -1){
              let node = document.createElement("option");
              let val = document.createTextNode(tagsScenery[i]);
              node.appendChild(val);

              document.getElementById("datalist")
              .appendChild(node);
          }
      }
    } */

/*     function addTag(){

    const charElement = document.getElementById("Character");
    const shapeElement = document.getElementById("Shape");
    const typeElement = document.getElementById("Type");
    const poseElement = document.getElementById("Pose");
    const clothElement = document.getElementById("Clothing");
    const colorElement = document.getElementById("Color");
    const saturElement = document.getElementById("Saturation");
    const inputElement = document.getElementById("tags");
    const tagList = document.getElementById("taglist");

    const tagElement = document.createElement("p");
    tagElement.setAttribute("class", "mx-2 my-3 text-light d-flex align-items-center justify-content-center");
    console.log(inputElement.value);

    if(inputElement.value.startsWith("Character") && !charElement.value.startsWith("Character")){
      let shortenedTag = inputElement.value.substring(10);
      charElement.value = shortenedTag;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#db3a2b;border-radius:10px;width:12rem;height:2rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Shape") && !shapeElement.value.startsWith("Shape")){
      let shortenedTag = inputElement.value.substring(6);
      shapeElement.value = shortenedTag;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#db982b;border-radius:10px;width:12rem;height:2rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Type") && !typeElement.value.startsWith("Type")){
      let shortenedTag = inputElement.value.substring(5);
      typeElement.value = shortenedTag;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#51db2b;border-radius:10px;width:12rem;height:2rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Pose") && !poseElement.value.startsWith("Pose")){
      let shortenedTag = inputElement.value.substring(5);
      poseElement.value = shortenedTag;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#2bdb9d;border-radius:10px;width:12rem;height:2rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Clothing") && !clothElement.value.startsWith("Clothing")){
      let shortenedTag = inputElement.value.substring(9);
      clothElement.value = shortenedTag;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#2b9ddb;border-radius:10px;width:12rem;height:2rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Color") && !colorElement.value.startsWith("Color")){
      let shortenedTag = inputElement.value.substring(6);
      colorElement.value = shortenedTag;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#2b49db;border-radius:10px;width:12rem;height:2rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Saturation") && !saturElement.value.startsWith("Saturation")){
      let shortenedTag = inputElement.value.substring(11);
      saturElement.value = shortenedTag;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#a02bdb;border-radius:10px;width:12rem;height:2rem;");
      tagList.appendChild(tagElement);
    }

    inputElement.value = '';

  } */

/*   function addTagScenery(){

      const natureElement = document.getElementById("Nature");
      const structureElement = document.getElementById("Structure");
      const colorElement = document.getElementById("Color");
      const saturElement = document.getElementById("Saturation");
      const inputElement = document.getElementById("tags");
      const tagList = document.getElementById("taglist");

      const tagElement = document.createElement("p");
      tagElement.setAttribute("class", "mx-2 my-3 text-light d-flex align-items-center justify-content-center");
      console.log(inputElement.value);

      if(inputElement.value.startsWith("Nature") && !natureElement.value.startsWith("Nature")){
        let shortenedTag = inputElement.value.substring(7);
        natureElement.value = shortenedTag;
        tagElement.innerHTML = inputElement.value;
        tagElement.setAttribute("style", "background-color:#db3a2b;border-radius:10px;width:12rem;height:2rem;");
        tagList.appendChild(tagElement);
      } else if(inputElement.value.startsWith("Structure") && !structureElement.value.startsWith("Structure")){
        let shortenedTag = inputElement.value.substring(10);
        structureElement.value = shortenedTag;
        tagElement.innerHTML = inputElement.value;
        tagElement.setAttribute("style", "background-color:#51db2b;border-radius:10px;width:12rem;height:2rem;");
        tagList.appendChild(tagElement);
      } else if (inputElement.value.startsWith("Color") && !colorElement.value.startsWith("Color")){
        let shortenedTag = inputElement.value.substring(6);
        colorElement.value = shortenedTag;
        tagElement.innerHTML = inputElement.value;
        tagElement.setAttribute("style", "background-color:#2b49db;border-radius:10px;width:12rem;height:2rem;");
        tagList.appendChild(tagElement);
      } else if (inputElement.value.startsWith("Saturation") && !saturElement.value.startsWith("Saturation")){
        let shortenedTag = inputElement.value.substring(11);
        saturElement.value = shortenedTag;
        tagElement.innerHTML = inputElement.value;
        tagElement.setAttribute("style", "background-color:#a02bdb;border-radius:10px;width:12rem;height:2rem;");
        tagList.appendChild(tagElement);
      }

      inputElement.value = '';

    } */

/*     function addTags(){
          const natureElement = document.getElementById("Nature");
          const structureElement = document.getElementById("Structure");
          const colorElement = document.getElementById("Color");
          const saturElement = document.getElementById("Saturation");
          const tagList = document.getElementById("taglist");

          console.log("in addtags");

          if(natureElement.value !== null){
                  tagElement.innerHTML = natureElement.value;
                  tagElement.setAttribute("style", "background-color:#db3a2b;border-radius:10px;width:12rem;height:2rem;");
                  tagList.appendChild(tagElement);
          } if(structureElement.value !== null){
                  tagElement.innerHTML = structureElement.value;
                  tagElement.setAttribute("style", "background-color:#51db2b;border-radius:10px;width:12rem;height:2rem;");
                  tagList.appendChild(tagElement);
          } if (colorElement.value !== null){
                  tagElement.innerHTML = colorElement.value;
                  tagElement.setAttribute("style", "background-color:#2b49db;border-radius:10px;width:12rem;height:2rem;");
                  tagList.appendChild(tagElement);
          } if (saturElement.value !== null){
                  tagElement.innerHTML = saturElement.value;
                  tagElement.setAttribute("style", "background-color:#a02bdb;border-radius:10px;width:12rem;height:2rem;");
                  tagList.appendChild(tagElement);
          }
    } */

    function redirect(){
      const homeButton = document.getElementById("homeButton");

      homeButton.click();
    }

    function checkSelect(){
      //const selection = document.querySelectorAll(select > option)
      const selection = document.getElementById("selection")
      const inputField = document.getElementById("reportTypeStr")

      if(selection.value == "Spam"){
        inputField.value = "spam"
        return
      } else if(selection.value == "Duplicate"){
        inputField.value = "duplicate"
        return
      } else if(selection.value == "Copyright"){
        inputField.value = "copyright"
        return
      } else if(selection.value == "Law"){
        inputField.value = "law"
        return
      } else {
        inputField.value = null
        return
      }
    }

    function fillValues(){
      const titleElement = document.getElementById("title")
      const descElement = document.getElementById("description")

      titleElement.value = "Profile picture"
      descElement.value = "Picture used to represent a profile"
    }

    function fillImageIds(){
      const paragraphElements = document.querySelectorAll('p')
      const imageId = document.getElementById("imageId")
      const id = imageId.getAttribute("value")

      console.log(paragraphElements.length)

      for(let i = 0; i < paragraphElements.length; i++){
        paragraphElements[i].setAttribute("field", id)
      }
    }

    function checkCheckboxes(){

      const galleryElements = document.querySelectorAll(".gallery")
      const galleryList = document.getElementById("galleryNames")
      let nameArray = galleryList.value.split("*")

      for(let i = 0; i < galleryElements.length; i++){
        let isPresent = false;
        let presentIndex = 0
        let checkbox = galleryElements[i].querySelector(".form-check-input")
        let divElement = checkbox.parentNode
        let label = divElement.querySelector("label")
        let labelContent = label.textContent
        console.log("current label: " + labelContent)

        if(checkbox.checked){
          for(let j = 0; j < nameArray.length; j++){
            if(nameArray[j] == labelContent){
              isPresent = true
              break
            }
          }

          if(!isPresent){
            const joinedArray = nameArray.join("*")
            galleryList.value = joinedArray + "*" + labelContent
            console.log("add")
            console.log(galleryList.value)
            console.log("----")
            return
          }
          
        }
        else{
          for(let j = 0; j < nameArray.length; j++){
            if(nameArray[j] == labelContent){
              presentIndex = j
              isPresent = true
              break
            }
          }
          if(isPresent){
            nameArray[presentIndex] = "%"
            const preSplitArray = nameArray.join("*")
            const splitArray = preSplitArray.split("%")
            galleryList.value = splitArray.join("")
            console.log("remove")
            console.log(galleryList.value)
            console.log("----")
            console.log(isPresent)
            return
          }
        
        }

      }

      /*
      for(let i = 0; i < galleryElements.length; i++){
        const checkbox = galleryElements[i].querySelector(".form-check-input")
        const divElement = checkbox.parentNode
        const label = divElement.querySelector("label")
        let galleryName = label.textContent
        for(let i = 1; i < nameArray.length; i++){
            if(nameArray[i].trim() == galleryName){
              isPresent = true
              index = i;
              console.log(i)
              break
            }
          }
        if(checkbox.checked && !isPresent){
          galleryList.value += "," + galleryName
          console.log(galleryList.value)
        } else if (!checkbox.checked){
          nameArray[i] = ""
          galleryList.value = nameArray.join(",")
          console.log(nameArray)
        }
      }*/

    }