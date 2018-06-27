function main() {
    $(document).ready(function () {
        addEventListenerToCourse();
    });
}

function addEventListenerToCourse() {
    let courseCards = document.getElementsByClassName("card");
    $.each(courseCards, function (i) {
        courseCards[i].addEventListener("click", function () {
            courseCards[i].setAttribute("data-toggle", "modal");
            courseCards[i].setAttribute("data-target", "#exampleModal");
            let id = courseCards[i].getAttribute("data-id");
            getCurseInfo(id);
        })
    });
}

function getCurseInfo(id) {
    $.getJSON("/courseinfo?id=" + id,
        function (response) {
            let rowToAppendContentTo = document.getElementById("modal-content");
            removeInfo();
            let newRow = document.createElement("div");
            let newName = document.createElement("div");
            let newDescription = document.createElement("div");
            newRow.classList.add("row");
            newName.classList.add("col");
            newDescription.classList.add("col");
            newDescription.innerText = response.description;
            newName.innerText = response.name;
            newRow.appendChild(newName);
            newRow.appendChild(newDescription);
            rowToAppendContentTo.appendChild(newRow);
            addEventListenerToModal(id, response.name);
        });
}

function addEventListenerToModal(id, nameOfCourse) {
    let buttonToSignUp = document.getElementById("sign-up-course");
    if (nameOfCourse === "JAVA") {
        buttonToSignUp.setAttribute("disabled", "disabled");
        buttonToSignUp.innerText = "Coming Soon";
    } else if (nameOfCourse === "ORIENTATION") {
        buttonToSignUp.setAttribute("disabled", "disabled");
        buttonToSignUp.innerText = "No need for that :)";
    } else {
        buttonToSignUp.removeAttribute("disabled");
        buttonToSignUp.innerText = "Sign Up";
    }
    let buttonToClose = document.getElementById("close-modal");
    buttonToSignUp.addEventListener("click", function () {
        signUpToCourse(id);
    });
    buttonToClose.addEventListener("click", function () {
        removeInfo();
    });
}

function signUpToCourse(id) {
    $.ajax({
        type: "POST",
        url: "/courseinfo?id=" + id,
        data: JSON.stringify("sign-up"),
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: location.reload()
    });
    removeInfo();
}

function removeInfo() {
    let rowToRemoveContentFrom = document.getElementById("modal-content");
    rowToRemoveContentFrom.innerHTML = "";
}

main();