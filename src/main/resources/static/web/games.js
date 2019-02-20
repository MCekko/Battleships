
var data;
var password;
var name;
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
        insertScores ();
        console.log(data);
    }).catch(function (error) {
        console.log("Request failed:" + error.message);
    });

}

getData();

function getDataGame() {
    for (var i = 0; i < data.games.length; i++) {
        var idGame = data.games[i].id;
        var dateGame = data.games[i].Date;
        var newDate = new Date(dateGame);
        var finalDateGame = newDate.getFullYear() + "/" + newDate.getMonth()+1 + "/" + newDate.getDay() + " " + newDate.getHours() + ":" + newDate.getMinutes() + ":" + newDate.getSeconds();
        var dataGamePlayer = data.games[i].GamePlayers;
        var getIDOL = document.getElementById("listGame");
        var createdLi = document.createElement("li");
        getIDOL.appendChild(createdLi);
        createdLi.setAttribute("id", "listGameLI");
        for (var w = 0; w < dataGamePlayer.length; w++) {

            var player1 = dataGamePlayer[0].Player.email;
            if (dataGamePlayer.length > 1) {
                var player2 = dataGamePlayer[1].Player.email;
                createdLi.innerHTML = finalDateGame + " " + player1 + " vs " + player2;
            } else {
                createdLi.innerHTML = finalDateGame + " " + player1 + " vs waiting Player";

            }
        }
    }
}

function insertScores () {
    var arrayThead = ["Name", "Total", "Won", "Lost", "Tied"];
    var newThead = document.createElement("thead");
    var newTbody = document.createElement("tbody");
    newTbody.setAttribute("id", "tbodyScore");
    newThead.setAttribute("id", "theadScore");
    document.getElementById("TableScore").appendChild(newThead);
    document.getElementById("TableScore").appendChild(newTbody);

    for (var i = 0; i < arrayThead.length; i++) {
        console.log(newThead)
        var newTd = document.createElement("td");
        newTd.innerHTML = arrayThead[i];
        newThead.appendChild(newTd);
    }


    for (var i = 0; i < data.leaderBoard.length; i++) {
        if (data.leaderBoard[i].player != "") {
            var newTr1 = document.createElement("tr");


            newTr1.setAttribute("id", data.leaderBoard[i].player);
            newTbody.appendChild(newTr1);
            for (var j = 0; j < data.leaderBoard.length; j++) {
                var newtd2 = document.createElement("td");
                var newtd3 = document.createElement("td");
                var newtd4 = document.createElement("td");
                var newtd5 = document.createElement("td");
                var newtd6 = document.createElement("td");
                newtd2.innerHTML = data.leaderBoard[i].player;
                newtd3.innerHTML = data.leaderBoard[i].total;
                newtd4.innerHTML = data.leaderBoard[i].won;
                newtd5.innerHTML = data.leaderBoard[i].lost;
                newtd6.innerHTML = data.leaderBoard[i].tied;
            }
            newTr1.appendChild(newtd2);
            newTr1.appendChild(newtd3);
            newTr1.appendChild(newtd4);
            newTr1.appendChild(newtd5);
            newTr1.appendChild(newtd6);
        }
    }
}

function Login() {

    password = document.getElementById("password").value;
    name = document.getElementById("name").value;
    fetch("/api/login", {
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        body: 'name=' + name + '&password=' + password,
    })
        .then(function (data) {
            console.log('Request success: ', data);


        }).then(function () {

    })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}

function Logout() {
    fetch("/api/logout", {
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
    })
        .then(function (data) {
            console.log('Request success: ', data);


        }).then(function () {

    })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}
function Signup() {

    password = document.getElementById("password").value;
    name = document.getElementById("name").value;

    fetch("/api/players", {
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        method: 'POST',
        body: JSON.stringify({
            user: name,
            password: password,
        })
    })
        .then(function (data) {
            console.log('Request success: ', data);


        }).then(function () {

    })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}

// function hideDiv() {
//     var x = document.getElementById("listGame");
//     x.style.display = "none";
//     var y = document.getElementById("TableScore");
//     y.style.display = "none";
// }