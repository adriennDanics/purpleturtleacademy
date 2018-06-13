dom = {
    init: function() {
        dom.sendFriendRequest();
    },

    sendFriendRequest: function() {
        let requesterId = document.getElementById("requesterStudent").dataset.requesterstudent;
        let requestButtons = document.getElementsByClassName("requestButtons");
        for(let button of requestButtons) {
            button.addEventListener('click', function() {
                let requestedId = button.dataset.id;
                $.post('/save-friend-request', {
                    requesterId: requesterId,
                    requestedId: requestedId
                })
            })
        }
    }
};

dom.init();