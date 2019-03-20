var data;

function getData() {
    fetch("/api/game_view/" + getParameterByName("gp"), {
        method: "GET",
    }).then(function (response) {
        if (response.ok) {

            return response.json();
        }

    }).then(function (json) {
        data = json;
        getDataGamePlayer();
        createdTable();
        createdTableShot();
        console.log(data);
    }).catch(function (error) {
        console.log("Request failed:" + error.message);
    });

}

function CreatedNewShip() {

    var newShip = "destroyer";
    var locationShip = ["A1", "B1", "C1"];

    fetch("/api/games/players/15/ships", {
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        method: 'POST',
        body: JSON.stringify([{
            shipType: newShip,
            listPosition: locationShip,
        }])
    })
        .then(function (data) {
            console.log('Request success: ', data);

        }).then(function () {
        console.log(data);

    })
        .catch(function (error) {
            console.log('Request failure: ', error);
        });
}

var playerOwner;
var playerOwnerID;
var playerOponent;
var idPlayerOwner;

function getOwnerofGame() {
    idPlayerOwner = getParameterByName("gp");
    var dataIDGamePlayer = data.Game.GamePlayers;
    console.log(dataIDGamePlayer);
    for (var h = 0; h < dataIDGamePlayer.length; h++) {
        var dataIDPlayerInGame = dataIDGamePlayer[h].id;
        console.log(dataIDPlayerInGame);

        if (idPlayerOwner == dataIDPlayerInGame) {
            playerOwner = dataIDGamePlayer[h].Player.email;
            playerOwnerID = dataIDGamePlayer[h].Player.id;
        } else {
            playerOponent = dataIDGamePlayer[h].Player.email;

        }
    }
    console.log(playerOwner);
    console.log(playerOwnerID);
    console.log(playerOponent);
    console.log(getParameterByName("gp"));
}


function getParameterByName(name) {
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}

var arrayNumbers = [" ", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

var arrayLetter = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

var idCells = [];

function createdTable() {
    getOwnerofGame();
    var playersInGame = document.getElementById("PlayerinGame");
    var dataGamePlayer2 = data.Game.GamePlayers;
    playersInGame.textContent = playerOwner + " vs " + playerOponent;


    var PrincipalTable = document.getElementById("Table");
    var createdTHEAD = document.createElement("thead");
    PrincipalTable.appendChild(createdTHEAD);

    var createdTRNumbers = document.createElement("tr");
    createdTHEAD.appendChild(createdTRNumbers);

    for (var i = 0; i < arrayNumbers.length; i++) {
        var createdTH = document.createElement("th");
        createdTRNumbers.appendChild(createdTH);
        createdTH.textContent = arrayNumbers[i];
    }
    var createdTBODY = document.createElement("tbody");
    PrincipalTable.appendChild(createdTBODY);

    for (var j = 0; j < arrayLetter.length; j++) {
        var createdTRLetter = document.createElement("tr");
        createdTBODY.appendChild(createdTRLetter);
        var createdTD = document.createElement("td");
        createdTRLetter.appendChild(createdTD);
        createdTD.textContent = arrayLetter[j];

        for (var w = 0; w < arrayLetter.length; w++) {
            var createdTD2 = document.createElement("td");
            createdTRLetter.appendChild(createdTD2);
            createdTD2.setAttribute("id", arrayLetter[j] + arrayNumbers[w + 1]);
            createdTD2.onclick = ala(arrayLetter[j] + arrayNumbers[w + 1]);
            idCells.push(arrayLetter[j] + arrayNumbers[w + 1]);

        }
    }
    var dataShips2 = data.Ship;
    for (var b = 0; b < dataShips2.length; b++) {
        var dataLocation2 = dataShips2[b].Location;

        for (var q = 0; q < dataLocation2.length; q++) {
            document.getElementById(dataLocation2[q]).setAttribute("class", "locationShips");
        }
    }

}

var DestroyerPosition = [];
var allShipsPosition = [];

function ala(id) {
    return function putNewShips() {
        for (var m = 0; m < DestroyerPosition.length; m++) {
            document.getElementById(DestroyerPosition[m]).setAttribute("class", "");
        }
        for (var w = 0; w < idCells.length; w++) {
            if ( idCells[w + 1] == null || idCells[w + 2] == null){
                DestroyerPosition = [];
            } else if (id === idCells[w]) {

                DestroyerPosition = [];
                DestroyerPosition = [idCells[w], idCells[w + 1], idCells[w + 2]];
                console.log(DestroyerPosition);

                for (var q = 0; q < DestroyerPosition.length; q++) {
                    console.log(DestroyerPosition[q])
                    document.getElementById(DestroyerPosition[q]).setAttribute("class", "Destroyer");
                }
            }
        }
        if (DestroyerPosition.length === 3) {
            if (DestroyerPosition[0].split("")[0] != DestroyerPosition[1].split("")[0]
                || DestroyerPosition[0].split("")[0] != DestroyerPosition[2].split("")[0]) {
                console.log(DestroyerPosition[0].split("")[0], DestroyerPosition[1].split("")[0], DestroyerPosition[2].split("")[0]);
                for (var f = 0; f < DestroyerPosition.length; f++) {
                    document.getElementById(DestroyerPosition[f]).setAttribute("class", "");
                }
                DestroyerPosition = [];
            }
        }
    }
    console.log(DestroyerPosition);
}



function createdTableShot() {

    getOwnerofGame();

    var arrayNumbers2 = [" ", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

    var arrayLetter2 = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

    var PrincipalTable2 = document.getElementById("Table2");
    var createdTHEAD2 = document.createElement("thead");
    PrincipalTable2.appendChild(createdTHEAD2);

    var createdTRNumbers2 = document.createElement("tr");
    createdTHEAD2.appendChild(createdTRNumbers2);

    for (var i = 0; i < arrayNumbers2.length; i++) {
        var createdTH2 = document.createElement("th");
        createdTRNumbers2.appendChild(createdTH2);
        createdTH2.textContent = arrayNumbers2[i];
    }
    var createdTBODY2 = document.createElement("tbody");
    PrincipalTable2.appendChild(createdTBODY2);

    for (var j = 0; j < arrayLetter2.length; j++) {
        var createdTRLetter2 = document.createElement("tr");
        createdTBODY2.appendChild(createdTRLetter2);
        var createdTD2 = document.createElement("td");
        createdTRLetter2.appendChild(createdTD2);
        createdTD2.textContent = arrayLetter2[j];

        for (var w = 0; w < arrayLetter2.length; w++) {
            var createdTD3 = document.createElement("td");
            createdTRLetter2.appendChild(createdTD3);
            createdTD3.setAttribute("id", arrayLetter2[j] + arrayNumbers2[w + 1] + "s");
        }
    }

    var dataShot = data.Salvoes;
    var dataGamePlayer2 = data.Game.GamePlayers;
    var dataShipLocation = data.Ship;

    for (var p = 0; p < dataShipLocation.length; p++) {

        var dataShips2 = dataShipLocation[p].Location;
    }

    for (var a = 0; a < dataGamePlayer2.length; a++) {

        var dataIDPlayer2 = dataGamePlayer2[a].Player.id;
    }
    for (var b = 0; b < dataShot.length; b++) {
        var dataShotLocation = dataShot[b].salvo;

        for (var q = 0; q < dataShotLocation.length; q++) {
            var dataShotLocation2 = dataShotLocation[q].Location;
            var dataTurn = dataShotLocation[q].Turn;
            var dataPlayerShot = dataShotLocation[q].Player;

            for (var t = 0; t < dataShotLocation2.length; t++) {
                if (dataPlayerShot == idPlayerOwner) {
                    document.getElementById(dataShotLocation2[t] + "s").setAttribute("class", "locationShot");
                    document.getElementById(dataShotLocation2[t] + "s").textContent = dataTurn;
                } else {
                    console.log(dataShotLocation2[t]);

                    if (document.getElementById(dataShotLocation2[t]).getAttribute("class") == "locationShips") {
                        document.getElementById(dataShotLocation2[t]).setAttribute("class", "locationHit");
                        document.getElementById(dataShotLocation2[t]).textContent = dataTurn;

                    } else {
                        document.getElementById(dataShotLocation2[t]).setAttribute("class", "locationHitWater");
                        document.getElementById(dataShotLocation2[t]).textContent = dataTurn;
                    }
                }
            }
        }
    }

}

function getDataGamePlayer() {

    var idGame = data.Game.id;
    var dataGamePlayer = data.Game.GamePlayers;
    var dataShips = data.Ship;
    for (let j = 0; j < dataGamePlayer.length; j++) {
        var dataPlayer = dataGamePlayer[j].Player.email;
    }
    for (let i = 0; i < dataShips.length; i++) {
        var dataTypeShip = dataShips[i].Type;
        var dataLocation = dataShips[i].Location;
    }
    console.log(idGame);
    console.log(dataPlayer);
    console.log(dataShips);
    console.log(dataTypeShip);
    console.log(dataLocation);

}

getData();