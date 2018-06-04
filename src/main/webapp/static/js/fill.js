dom = {
    _cucc: "",
    _id: "",

    init: function () {
        dom.getAnswers();
    },

    // checkAnswers: function () {
    //     let answerInputs = document.getElementsByClassName("input");
    //     let submitButton = document.getElementById("submit");
    //     submitButton.addEventListener('click', function () {
    //
    //     })
    // },
    
    getAnswers: function () {
        let questionId = document.getElementById("fillInId").innerText;
        dom._id = questionId;
        $.getJSON("/fill_in_answers?id=" + questionId, function(response) {
            dom._cucc = response;
        })
    }

};

dom.init();