let BEGINNER = 10;
let INTERMEDIATE = 20;
let ADVANCED = 25;
let LEVEL = document.getElementById("get-level").dataset.level;
let XP = Number(document.getElementById("get-xp").dataset.xp);
let ID = document.getElementById("get-id").dataset.quizid;

function main(){
    $(document).ready(function () {
        let answers = document.getElementsByClassName("quiz_answers");
        let next = Number(document.getElementById("get-next").dataset.next);
        $.each(answers, function (i) {
            answers[i].addEventListener("click", function () {
                if(answers[i].getAttribute("id") ==="true"){
                    answers[i].classList.add("greenback");
                    modifyXPPositive();
                    checkIfNext(next);
                } else {
                    modifyXPNegative();
                    answers[i].classList.add("redback");
                    let xp = document.getElementById("get-xp");
                    xp.innerText = XP+" XP";
                }
            })
        })
    });
}

function modifyXPNegative() {
    if(LEVEL==="BEGINNER"){
        XP-=BEGINNER-5;
    }else if(LEVEL==="INTERMEDIATE"){
        XP-=INTERMEDIATE-5;
    } else {
        XP-=ADVANCED-5;
    }
    sendXP();
}

function modifyXPPositive() {
    if(LEVEL==="BEGINNER"){
        XP+=BEGINNER;
    }else if(LEVEL==="INTERMEDIATE"){
        XP+=INTERMEDIATE;
    } else {
        XP+=ADVANCED;
    }
    sendXP();
}

function sendXP() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/quiz?id=" + ID,
        data: JSON.stringify({'xp': XP}),
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: function () {

        }
    });
}

function checkIfNext(next) {
    if(next <= 0){
        setTimeout(function () {
            window.location.replace("/assignments");
        }, 1000);
    } else {
        setTimeout(function () {
            window.location.replace("http://localhost:8080/question?id="+String(next-1));
        }, 1000);
    }
}

main();