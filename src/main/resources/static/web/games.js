
var data;


function getData() {
    fetch("/api/games", {
        method: "GET",
    }).then(function (response) {
        if (response.ok) {

            return response.json();
        }

    }).then(function (json) {
        data = json;
        getDataGame();
        console.log(data);
    }).catch(function (error) {
        console.log("Request failed:" + error.message);
    });

}

getData();

function getDataGame() {
    for (let i = 0; i < data.length; i++) {
        var idGame = data[i].id;
        var dateGame = data[i].Date;
        var newDate = new Date(dateGame);
        var finalDateGame = newDate.getFullYear() + "/" + newDate.getMonth()+1 + "/" + newDate.getDay() + " " + newDate.getHours() + ":" + newDate.getMinutes() + ":" + newDate.getSeconds();
        var dataGamePlayer = data[i].GamePlayers;
        var getIDOL = document.getElementById("listGame");
        var createdLi = document.createElement("li");
        getIDOL.appendChild(createdLi);
        createdLi.setAttribute("id", "listGameLI");
        var player1 = dataGamePlayer[0].Player.email;
        if (dataGamePlayer.length > 1) {
            var player2 = dataGamePlayer[1].Player.email;
            createdLi.innerHTML = finalDateGame + " " + player1 + " vs " + player2;
        } else {
            createdLi.innerHTML = finalDateGame + " " + player1 + " vs waiting Player";

        }
    }
}