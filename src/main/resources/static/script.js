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

  function predict(value){
    document.getElementById('datalist').innerHTML = '';

    l = value.length;

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