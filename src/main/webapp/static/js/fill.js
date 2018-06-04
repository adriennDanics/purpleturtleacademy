dom = {
    _id: "",
    _correctAnswersObject: "",
    _correctAnswersList: [],
    _userAnswers: [],
    _responseDiv: "",

    init: function () {
        dom.getCorrectAnswers();
        dom.getUserAnswers();
        dom.getResponseDiv();
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
                dom.correctAnswer();
            } else {
                dom.wrongAnswer();
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
    },

    getResponseDiv: function () {
        dom._responseDiv = document.getElementById("responseDiv")
    },

    correctAnswer: function() {
        let responseParagraph = document.createElement("p");
        responseParagraph.innerHTML = "Your answer is correct!";
        dom._responseDiv.appendChild(responseParagraph)
    },

    wrongAnswer: function() {
        let responseParagraph = document.createElement("p");
        responseParagraph.innerHTML = "Your answer is wrong!";
        dom._responseDiv.appendChild(responseParagraph)
    }

};

dom.init();