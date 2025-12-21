    function redirect(){
      const homeButton = document.getElementById("homeButton");

      homeButton.click();
    }

    function checkSelect(){
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

    function checkCheckboxesAdd(){

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

    }

        function checkCheckboxesDelete(){

      const imageElements = document.querySelectorAll(".galleryImage")
      const imageList = document.getElementById("imageNames")
      let nameArray = imageList.value.split("*")

      for(let i = 0; i < imageElements.length; i++){
        let isPresent = false;
        let presentIndex = 0
        let checkbox = imageElements[i].querySelector(".form-check-input")
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
            imageList.value = joinedArray + "*" + labelContent
            console.log("add")
            console.log(imageList.value)
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
            imageList.value = splitArray.join("")
            console.log("remove")
            console.log(imageList.value)
            console.log("----")
            console.log(isPresent)
            return
          }
        
        }

      }

    }