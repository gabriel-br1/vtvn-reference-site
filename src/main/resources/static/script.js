let tags = ["Character:Single",
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

  let n = tags.length;

  function predictCharacter(value){
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
  }

  function predictScenery(value){
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
    }

    function addTag(){

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

  }

  function addTagScenery(){

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

    }