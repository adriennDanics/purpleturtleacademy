addNewImage={
    addEventListenerToButtons: function () {
        let buttonToChangeImage = document.getElementById("change-image");
        buttonToChangeImage.addEventListener("click", function () {
            let appendToThis = document.getElementById("image-form");
            addNewImage.makeInputField(appendToThis, "new-image", "change-image");
        });
        let buttonToEditName = document.getElementById("change-user-name");
        buttonToEditName.addEventListener("click", function () {
            let appendToThisInsteadForName = document.getElementById("name-form");
            addNewImage.makeInputField(appendToThisInsteadForName, "new-user-name", "change-user-name");
        })

    },

    makeInputField: function (elementToAppendTo, idString, buttonIdString) {
        let inputField = document.createElement("input");
        inputField.setAttribute("id", idString);
        inputField.setAttribute("name", idString);
        inputField.setAttribute("type", "text");
        inputField.classList.add("form-control");

        let submitButton = document.createElement("button");
        submitButton.setAttribute("type", "submit");
        submitButton.innerText = "Submit";
        submitButton.classList.add("btn");
        addNewImage.addEventListenerToSubmitImageButton(submitButton, buttonIdString);
        elementToAppendTo.appendChild(inputField);
        elementToAppendTo.appendChild(submitButton);
    },

    addEventListenerToSubmitImageButton: function (buttonToListenTo, buttonIdString) {
        buttonToListenTo.addEventListener("click", function () {
            let removeable = document.getElementById(buttonIdString);
            while (removeable.firstChild-1) {
                removeable.removeChild(removeable.firstChild);
            }
        })
    }
};

addNewImage.addEventListenerToButtons();