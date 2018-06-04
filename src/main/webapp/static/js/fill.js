dom = {
    _id: "",
    _correctAnswersObject: "",
    _correctAnswersList: [],
    _userAnswers: [],

    init: function () {
        dom.getCorrectAnswers();
        dom.getUserAnswers();
    },
    
    getCorrectAnswers: function() {
        let questionId = document.getElementById("fillInId").innerText;
        dom._id = questionId;
        // $.getJSON("/fill_in_answers?id=" + questionId, function(response) {
        //     dom._correctAnswersObject = response;
        // });

        fetch("/fill_in_answers?id=" + questionId)
            .then(response => response.json())
            .then(function(result) {
                dom._correctAnswersObject = result;
                dom.makeCorrectAnswerList();
            });
    },

    makeCorrectAnswerList: function() {
        for(let key in dom._correctAnswersObject) {
            dom._correctAnswersList.push(dom._correctAnswersObject[key])
        }
    },

    getUserAnswers: function() {
        let submitButton = document.getElementById("submit");
        submitButton.addEventListener('click', function () {
            let answerInputs = document.getElementsByClassName("answer");
            for(let answer of answerInputs) {
                dom._userAnswers.push(answer.value);
            }
        })
    },

    checkAnswers: function() {

    }

};

dom.init();