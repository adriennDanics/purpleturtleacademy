editProfileInfo={
    addEventListenerToButtons: function () {
        let buttonToChangeImage = document.getElementById("change-image");
        buttonToChangeImage.addEventListener("click", function () {
            buttonToChangeImage.setAttribute("hidden", "hidden");
            let appendToThis = document.getElementById("image-form");
            editProfileInfo.makeInputFieldForImage(appendToThis, "new-image");
        });

        let buttonToEditName = document.getElementById("change-user-name");
        buttonToEditName.addEventListener("click", function () {
            let appendToThisInsteadForName = document.getElementById("name-form");
            buttonToEditName.setAttribute("hidden", "hidden");
            editProfileInfo.makeInputFieldForName(appendToThisInsteadForName, "new-user-name");
        });

        let buttonToEditPassword = document.getElementById("change-password");
        buttonToEditPassword.addEventListener("click", function () {
            let appendToThisForPassword = document.getElementById("password-form");
            buttonToEditPassword.setAttribute("hidden", "hidden");
            editProfileInfo.makeInputFieldForPassword(appendToThisForPassword, "old-password");
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
        editProfileInfo.addEventListenerToNewNameField(inputField, elementToAppendTo)
    },

    addEventListenerToNewNameField: function (elementToPayAttentionTo, elementThatContainsTheName) {
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
    },

    makeInputFieldForPassword: function (elementToAppendTo, idString) {
        let inputField = document.createElement("input");
        inputField.setAttribute("id", idString);
        inputField.setAttribute("name", idString);
        inputField.setAttribute("type", "password");
        inputField.setAttribute("placeholder", "Old password");
        inputField.classList.add("form-control");

        let inputFieldNewPass = document.createElement("input");
        inputFieldNewPass.setAttribute("id", "new-password");
        inputFieldNewPass.setAttribute("name", "new-password");
        inputFieldNewPass.setAttribute("type", "password");
        inputFieldNewPass.setAttribute("placeholder", "New password");
        inputFieldNewPass.classList.add("form-control");

        let inputFieldConfirmation = document.createElement("input");
        inputFieldConfirmation.setAttribute("id", "new-password-confirmation");
        inputFieldConfirmation.setAttribute("name", "new-password-confirmation");
        inputFieldConfirmation.setAttribute("type", "password");
        inputFieldConfirmation.setAttribute("placeholder", "Confirm password");
        inputFieldConfirmation.classList.add("form-control");

        let submitButton = document.createElement("button");
        submitButton.setAttribute("type", "button");
        submitButton.setAttribute("id", "password-submit");
        submitButton.innerText = "Submit";
        submitButton.classList.add("btn");
        editProfileInfo.addEventListenerToSubmitNewPasswordButton(submitButton, inputField, inputFieldNewPass, inputFieldConfirmation);
        elementToAppendTo.appendChild(inputField);
        elementToAppendTo.appendChild(inputFieldNewPass);
        elementToAppendTo.appendChild(inputFieldConfirmation);
        elementToAppendTo.appendChild(submitButton);
    },

    addEventListenerToSubmitNewPasswordButton: function (buttonToListenTo, oldPass, newPass, passConfirm) {
        buttonToListenTo.addEventListener("click", function () {
            if(newPass.value === passConfirm.value){
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/profile/newpassword",
                    data: JSON.stringify({'old': oldPass.value, 'new': newPass.value}),
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (response) {
                        if(response.message){
                            let message = document.createElement("p");
                            message.innerText = response.message;
                            message.classList.add("warning");
                            newPass.parentElement.appendChild(message);
                            editProfileInfo.removeUnnecessaryInputFields();
                        }
                    }
                });
                editProfileInfo.removeUnnecessaryInputFields();
            } else {
                let message = document.createElement("p");
                message.innerText = "New passwords don't match!";
                message.classList.add("warning");
                newPass.parentElement.appendChild(message);
            }
        })
    },

    removeUnnecessaryInputFields: function () {
        document.getElementById("old-password").remove();
        document.getElementById("new-password").remove();
        document.getElementById("new-password-confirmation").remove();
        document.getElementById("password-submit").remove();
        let buttonToEditPassword = document.getElementById("change-password");
        buttonToEditPassword.removeAttribute("hidden");
    }
};

editProfileInfo.addEventListenerToButtons();