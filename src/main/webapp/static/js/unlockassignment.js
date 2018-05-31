function main(){
    $(document).ready(function () {
        let answers = document.getElementsByClassName("quiz_answers");
        $.each(answers, function (i) {
            answers[i].addEventListener("click", function () {
                if(answers[i].getAttribute("id")==="true"){
                    answers[i].classList.add("greenback");
                    setTimeout(function () {
                        window.location.replace("http://localhost:8080/assignments")
                    }, 1000)
                } else {
                    answers[i].classList.add("redback");
                }
            })
        })
    });
}
main();