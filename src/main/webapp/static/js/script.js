function main() {
    $(document).ready(function () {
        document.getElementById("check_solution").addEventListener("click", function () {
            $.getJSON('/kata', function (response) {
                getSolution(response);
            })
        })
    });
}

function getSolution(data){
    $(document).ready(function () {
        $.getJSON('/kata', function (response) {
            checkSolution(data, response);
        })
    });
}

function checkSolution(data, comparable) {
    let showResponse = document.getElementById("show");
    if(data===comparable){
        showResponse.style.backgroundColor("green");
    } else {
        showResponse.style.backgroundColor("red");
    }
}

main();