let tags = ["Character:Single ",
  "Character:Multiple ",
  "Shape:Masculine ",
  "Shape:Feminine ",
  "Shape:Androgynous ",
  "Type:Large ",
  "Type:Average ",
  "Type:Thin ",
  "Pose:Sitting ",
  "Pose:Kneeling ",
  "Pose:Squatting ",
  "Pose:Standing ",
  "Pose:Lying ",
  "Pose:Walking ",
  "Pose:Running ",
  "Pose:Jumping ",
  "Clothing:Formal ",
  "Clothing:Casual ",
  "Clothing:Period ",
  "Clothing:Futuristic ",
  "Color:Warm ",
  "Color:Cold ",
  "Saturation:Pastel ",
  "Saturation:Neon "];

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
    tagElement.setAttribute("class", "mx-2 my-3 text-light text-center");
    console.log(inputElement.value);

    if(inputElement.value.startsWith("Character")){
      charElement.innerHTML = inputElement.value;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#db3a2b;border-radius:10px;width:12rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Shape")){
      shapeElement.innerHTML = inputElement.value;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#db982b;border-radius:10px;width:12rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Type")){
      typeElement.innerHTML = inputElement.value;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#51db2b;border-radius:10px;width:12rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Pose")){
      poseElement.innerHTML = inputElement.value;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#2bdb9d;border-radius:10px;width:12rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Clothing")){
      clothElement.innerHTML = inputElement.value;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#2b9ddb;border-radius:10px;width:12rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Color")){
      colorElement.innerHTML = inputElement.value;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#2b49db;border-radius:10px;width:12rem;");
      tagList.appendChild(tagElement);
    } else if (inputElement.value.startsWith("Saturation")){
      saturElement.innerHTML = inputElement.value;
      tagElement.innerHTML = inputElement.value;
      tagElement.setAttribute("style", "background-color:#a02bdb;border-radius:10px;width:12rem;");
      tagList.appendChild(tagElement);
    }

    inputElement.innerHTML = '';

  }