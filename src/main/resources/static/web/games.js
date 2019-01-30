
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
    for(var i = 0;i < data.length; i++){
       var idGame = data[i].id;

       var dataGamePlayer = data[i].GamePlayers;

       console.log(idGame);
        console.log(dateGame);
        console.log(dataGamePlayer);

    }
    for (var i = 0; i < dataGamePlayer.length; i++) {
        var gameplayers = data[i].GamePlayers[i].Player;
        var user = gameplayers.email;
        console.log(user);
    }
    var getIDOL = document.getElementById("listGame");
    for(var i = 0; i < data.length; i++){
        var dateGame = data[i].Date;
        var createdLi = document.createElement("li");
        getIDOL.appendChild(createdLi);
        createdLi.innerHTML = dateGame;
    }

}
// function createdLI() {
//     var getIDOL = document.getElementById("listGame");
//     for(var i = 0; i < data.length; i++){
//         var createdLi = document.createElement("li");
//     }
//     getIDOL.appendChild(createdLi);
//     createdLi;
// }
