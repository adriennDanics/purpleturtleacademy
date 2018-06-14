layout = {

    init: function () {
        layout.notificationDivEventListener();
        layout.removeBell();
    },

    notificationDivEventListener: function () {
        let notificationDiv = document.getElementById("notification_bell");
        notificationDiv.addEventListener('click', function () {
            let notificationDropdown = document.getElementById("notification_dropdown");
            let style = window.getComputedStyle(notificationDropdown);
            style.getPropertyValue("opacity") === "1" ? notificationDropdown.style.opacity = "0" : notificationDropdown.style.opacity = "1";
            style.getPropertyValue("top") === "30px" ? notificationDropdown.style.top = "55px" : notificationDropdown.style.top = "30px";
            console.log("mouseover");
        });
    },

    removeBell: function() {
        let requestButton = document.getElementById("friend-request-button");
        let bell = document.getElementById("notification_bell");
        requestButton.addEventListener('click', function() {
            $(bell).remove();
            $.post('/remove-notification', {})
        })
    }

};

layout.init();