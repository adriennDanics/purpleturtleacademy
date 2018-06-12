editProfileInfo={
    addEventListenerToButtons: function () {
        let buttonToChangeImage = document.getElementById("change-image");
        buttonToChangeImage.addEventListener("click", function () {
            let appendToThis = document.getElementById("image-form");
            editProfileInfo.makeInputFieldForImage(appendToThis, "new-image");
        });

        let buttonToEditName = document.getElementById("change-user-name");
        buttonToEditName.addEventListener("click", function () {
            let appendToThisInsteadForName = document.getElementById("name-form");
            buttonToEditName.setAttribute("hidden", "hidden");
            editProfileInfo.makeInputFieldForName(appendToThisInsteadForName, "new-user-name");
        })

    },

    makeInputFieldForImage: function (elementToAppendTo, idString) {
        let inputField = document.createElement("input");
        inputField.setAttribute("id", idString);
        inputField.setAttribute("name", idString);
        inputField.setAttribute("type", "text");
        inputField.setAttribute("placeholder", "Leave the field blank for default picture");
        inputField.classList.add("form-control");

        let submitButton = document.createElement("button");
        submitButton.setAttribute("type", "submit");
        submitButton.innerText = "Submit";
        submitButton.classList.add("btn");
        editProfileInfo.addEventListenerToSubmitImageButton(submitButton, "change-image");
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
    },
    makeInputFieldForName: function (elementToAppendTo, idString) {
        let oldUserName = elementToAppendTo.innerText;
        elementToAppendTo.setAttribute("hidden", "hidden");

        let inputField = document.createElement("input");
        inputField.setAttribute("id", idString);
        inputField.setAttribute("name", idString);
        inputField.setAttribute("type", "text");
        inputField.setAttribute("value", oldUserName);
        inputField.classList.add("form-control");
        elementToAppendTo.parentElement.appendChild(inputField);
        inputField.focus();
        editProfileInfo.addEventListenerToNowNameField(inputField, elementToAppendTo)
    },

    addEventListenerToNowNameField: function (elementToPayAttentionTo, elementThatContainsTheName) {
        elementToPayAttentionTo.addEventListener("blur", function () {
            let newName = elementToPayAttentionTo.value;
            elementThatContainsTheName.innerText = newName;
            elementThatContainsTheName.removeAttribute("hidden");
            elementThatContainsTheName.parentElement.removeChild(elementToPayAttentionTo);

            let buttonToEditName = document.getElementById("change-user-name");
            buttonToEditName.removeAttribute("hidden");
            editProfileInfo.sendNewName(newName);
        })
    },

    sendNewName: function (newNameToSend) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/profile/newname",
            data: JSON.stringify(newNameToSend),
            async: false,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function () {

            }
        });
        debugger;
    }
};

editProfileInfo.addEventListenerToButtons();