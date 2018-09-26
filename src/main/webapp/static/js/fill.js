dom = {
    _id: "",
    _correctAnswersObject: "",
    _correctAnswersList: [],
    _userAnswers: [],
    _responseDiv: "",
    _xp: Number(document.getElementById("get-xp").dataset.xp),
    _xpChange: document.getElementById("get-xp-change").dataset.xpchange,

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
            dom._correctAnswersList.push(dom._correctAnswersObject[key]);
            dom._correctAnswersList.reverse();
        }
    },

    getUserAnswers: function() {
        let submitButton = document.getElementById("submitFillInAnswer");
        submitButton.addEventListener('click', function () {
            let answerInputs = document.getElementsByClassName("answer");
            for(let answer of answerInputs) {
                dom._userAnswers.push(answer.value);
            }
            if(dom.checkAnswers()) {
                dom.correctAnswerResponse();
            } else {
                dom.wrongAnswerResponse();
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

    correctAnswerResponse: function() {
        let responseParagraph = document.createElement("p");
        responseParagraph.innerHTML = "Your answer is correct!";
        dom.clearResponseDiv();
        dom._responseDiv.appendChild(responseParagraph);
        let submitButton = document.getElementById("submitFillInAnswer");
        submitButton.style.display = 'none';
        dom._xp += 20;
        let next = document.getElementById("get-next").dataset.next;
        dom.checkIfNext(next);
    },

    wrongAnswerResponse: function() {
        let responseParagraph = document.createElement("p");
        responseParagraph.innerHTML = "Your answer is wrong!";
        let xp = document.getElementById("get-xp");
        xp.innerText = dom._xp + " XP";
        dom.clearResponseDiv();
        dom._responseDiv.appendChild(responseParagraph);
        dom._userAnswers = [];
        dom._xp -= 10;
    },

    clearResponseDiv: function() {
        dom._responseDiv.innerHTML = "";
    },

    checkIfNext: function(next) {
        if (next <= 0) {
            dom.sendXP();
            window.location.replace("http://0.0.0.0:8080/quiz-result?xp=" + xpChange);
        } else {
            dom.sendXP();
            window.location.replace("http://localhost:8080/question?id="+String(next-1)+"&xp="+xpChange);
        }
    },

    sendXP: function() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/quiz?xp="+dom._xp,
        data: JSON.stringify({'xp': dom._xp}),
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: function () {

        }
    });
}
};

dom.init();
