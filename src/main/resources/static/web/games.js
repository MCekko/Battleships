
var data;
var password;
var name;
getData();
function getData() {
    fetch("/api/games", {
        method: "GET",
    }).then(function (response) {
        if (response.ok) {

            return response.json();
        }

    }).then(function (json) {
        data = json;

        // CreatedGame();
        hideandShowLogin();
        hideButtonGames();
        getDataGame();
        insertScores ();
        console.log(data);
    }).catch(function (error) {
        console.log("Request failed:" + error.message);
    });

}

function getDataGame() {
    for (var i = 0; i < data.games.length; i++) {
        var idGame = data.games[i].id;
        var dateGame = data.games[i].Date;
        var newDate = new Date(dateGame);
        var finalDateGame = newDate.getFullYear() + "/" + newDate.getMonth() + 1 + "/" + newDate.getDay() + " " + newDate.getHours() + ":" + newDate.getMinutes() + ":" + newDate.getSeconds();
        var dataGamePlayer = data.games[i].GamePlayers;
        var getIDOL = document.getElementById("listGame");
        var createdLi = document.createElement("li");
        getIDOL.appendChild(createdLi);
        createdLi.setAttribute("id", "listGameLI");
        for (var w = 0; w < dataGamePlayer.length; w++) {
            var player1 = dataGamePlayer[0].Player.email;
            var player2 = dataGamePlayer[1] ? dataGamePlayer[1].Player.email : "waiting Player";
            if (data.PlayerLogin !== null) {
                console.log("Està logeado!!!")
                if (dataGamePlayer.length > 1) {
                    if (data.PlayerLogin.email == player1 || data.PlayerLogin.email == player2) {
                        createdLi.innerHTML = finalDateGame + " " + player1 + " vs " + player2 + "<a id='prueba' class='button3'> Entry </a>";
                        var LinkEntry = document.getElementById("prueba");
                        LinkEntry.setAttribute("id", "Entry" + i);
                        var EntryLink = document.getElementById("Entry" + i);
                        EntryLink.setAttribute("href", "http://localhost:8080/web/game.html?gp=" + dataGamePlayer[w].id);
                    }else{
                        createdLi.innerHTML = finalDateGame + " " + player1 + " vs " + player2;
                    }

                }else{
                    if (data.PlayerLogin.email === player1){
                        console.log("Soy yo el ùnico player")
                        createdLi.innerHTML = finalDateGame + " " + player1 +  " vs waiting Player" + "<a id='prueba' class='button3'> Entry </a>";
                        var LinkEntry = document.getElementById("prueba");
                        LinkEntry.setAttribute("id", "Entry" + i);
                        var EntryLink = document.getElementById("Entry" + i);
                        EntryLink.setAttribute("href", "http://localhost:8080/web/game.html?gp=" + dataGamePlayer[w].id);
                    } else{
                        createdLi.innerHTML = `finalDateGame + " " + player1 + "vs waiting Player" + <a id='Join' onclick='JoinButton(${dataGamePlayer[w].id})' class='button3'> Join </a>`;
                    }
                }
            } else {
                console.log("No estàs loggeado")
                createdLi.innerHTML = finalDateGame + " " + player1 + " vs " + player2;
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
            window.location.reload();
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

            window.location.reload();

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
            Login();

        }).then(function () {

    })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}

function CreatedGame() {

    fetch("/api/games", {
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        method: 'POST',
    })
        .then(function (response) {
            console.log('Request success: ', response);
            return response.json()
        }).then(function (json) {
        console.log(JSON.stringify(json))
        window.open("http://localhost:8080/web/game.html?gp=" + json.GpID);
        window.location.reload();
    })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}

function JoinButton(gameID) {

    fetch("/api/game/" + gameID + "/players", {
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        method: 'POST',
    })
        .then(function (response) {
            console.log('Request success: ', response);
            return response.json()
        }).then(function (json) {
        console.log(JSON.stringify(json))
        window.open("http://localhost:8080/web/game.html?gp=" + json.gameID);
        // window.location.reload();
    })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}

function hideandShowLogin() {
    if (data.PlayerLogin == null) {
        var x = document.getElementById("logout");
        x.style.display = "none";
        var ñ = document.getElementById("createdgame");
        ñ.style.display = "none";
    }
    else if(data.PlayerLogin) {
        var y = document.getElementById("login");
        y.style.display = "none";
        var w = document.getElementById("form1");
        w.style.display = "none";
    }
}

function hideButtonGames() {
    if (data.PlayerLogin == null){
        var m = document.getElementsByClassName("button3");
        for (var t = 0; t < m.length; t++){
            m[t].style.display = "none";
        }
    }
}