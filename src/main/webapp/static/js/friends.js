dom = {
    init: function() {
        dom.acceptFriendRequest();
    },

    acceptFriendRequest: function() {
        let requesterId = document.getElementById("requesterStudent").dataset.requesterstudent;
        let requestButtons = document.getElementsByClassName("requestButtons");
        for(let button of requestButtons) {
            button.addEventListener('click', function() {
                let requestedId = button.dataset.id;
                $.post('/save-friend-request', {
                    requesterId: requesterId,
                    requestedId: requestedId
                });
                let elemetToDeteteId = "student" + requestedId;
                dom.removeAddedFriendFromDom(elemetToDeteteId)
            })
        }
    },

    removeAddedFriendFromDom: function(id) {
        let elemToDelete = document.getElementById(id);
        $(elemToDelete).remove();
    }
};

dom.init();