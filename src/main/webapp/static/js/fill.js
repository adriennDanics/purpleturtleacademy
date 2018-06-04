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
            if(dom.checkAnswers()) {
                alert("stimmel");
            } else {
                alert("szar vagy!");
            }
        })
    },

    checkAnswers: function() {
        for(let i = 0; i < dom._correctAnswersList.length; i++) {
            if(dom._correctAnswersList[i].toLowerCase() != dom._userAnswers[i].toLowerCase()) {
                return false;
            }
        }
        return true;
    }

};

dom.init();