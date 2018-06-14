dom = {
    init: function() {
        dom.acceptFriendRequest();
        dom.rejectFriendRequest();
    },

    acceptFriendRequest: function() {
        let loggedInStudentId = document.getElementById("loggedInStudent").dataset.loggedinid;
        let acceptButtons = document.getElementsByClassName("acceptButtons");
        for(let button of acceptButtons) {
            button.addEventListener('click', function() {
                let requesterId = button.dataset.id;
                $.post('/accept-request', {
                    loggedInStudentId: loggedInStudentId,
                    requesterId: requesterId
                });
                let elemetToDeteteId = "student" + requesterId;
                dom.removeElemetFromDom(elemetToDeteteId)
            })
        }
    },

    rejectFriendRequest: function() {
        let loggedInStudentId = document.getElementById("loggedInStudent").dataset.loggedinid;
        let rejectButtons = document.getElementsByClassName("rejectButtons");
        for(let button of rejectButtons) {
            button.addEventListener('click', function() {
                let requesterId = button.dataset.id;
                $.post('/reject-request', {
                    loggedInStudentId: loggedInStudentId,
                    requesterId: requesterId,
                });
                let elemetToDeteteId = "student" + requesterId;
                dom.removeElemetFromDom(elemetToDeteteId)
            })
        }
    },

    removeElemetFromDom: function(id) {
        let elemToDelete = document.getElementById(id);
        $(elemToDelete).remove();
    }
};

dom.init();