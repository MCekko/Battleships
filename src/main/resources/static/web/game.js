var data;

function getData() {
    fetch("/api/game_view/" + getParameterByName("gp") , {
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

function getParameterByName(name) {
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}
function createdTable() {

    var playersInGame = document.getElementById("PlayerinGame");
    var dataGamePlayer2 = data.Game.GamePlayers;
        playersInGame.textContent = dataGamePlayer2[0].Player.email + " vs " + dataGamePlayer2[1].Player.email


    var arrayNumbers = [" ",1,2,3,4,5,6,7,8,9,10];

    var arrayLetter = ["A","B","C","D","E","F","G","H","I","J"];

    var PrincipalTable = document.getElementById("Table");
    var createdTHEAD = document.createElement("thead");
    PrincipalTable.appendChild(createdTHEAD);

    var createdTRNumbers = document.createElement("tr");
    createdTHEAD.appendChild(createdTRNumbers);

    for(var i = 0; i < arrayNumbers.length; i++){
        var createdTH = document.createElement("th");
        createdTRNumbers.appendChild(createdTH);
        createdTH.textContent = arrayNumbers[i];
    }
    var createdTBODY = document.createElement("tbody");
    PrincipalTable.appendChild(createdTBODY);

    for(var j = 0; j < arrayLetter.length; j++) {
        var createdTRLetter = document.createElement("tr");
        createdTBODY.appendChild(createdTRLetter);
        var createdTD = document.createElement("td");
        createdTRLetter.appendChild(createdTD);
        createdTD.textContent = arrayLetter[j];

        for (var w = 0; w < arrayLetter.length; w++) {
            var createdTD2 = document.createElement("td");
            createdTRLetter.appendChild(createdTD2);
            createdTD2.setAttribute("id", arrayLetter[j] + arrayNumbers[w +1]);
        }
    }

    var dataShips2 =data.Ship;
    for(var b = 0; b < dataShips2.length; b++){
        var dataLocation2 = dataShips2[b].Location;

        for (var q= 0; q <dataLocation2.length; q++) {
            console.log(document.getElementById(dataLocation2[q]));
            document.getElementById(dataLocation2[q]).setAttribute("class", "locationShips");
            }
    }

}

function createdTableShot() {
    var arrayNumbers2 = [" ",1,2,3,4,5,6,7,8,9,10];

    var arrayLetter2 = ["A","B","C","D","E","F","G","H","I","J"];

    var PrincipalTable2 = document.getElementById("Table2");
    var createdTHEAD2 = document.createElement("thead");
    PrincipalTable2.appendChild(createdTHEAD2);

    var createdTRNumbers2 = document.createElement("tr");
    createdTHEAD2.appendChild(createdTRNumbers2);

    for(var i = 0; i < arrayNumbers2.length; i++){
        var createdTH2 = document.createElement("th");
        createdTRNumbers2.appendChild(createdTH2);
        createdTH2.textContent = arrayNumbers2[i];
    }
    var createdTBODY2 = document.createElement("tbody");
    PrincipalTable2.appendChild(createdTBODY2);

    for(var j = 0; j < arrayLetter2.length; j++) {
        var createdTRLetter2 = document.createElement("tr");
        createdTBODY2.appendChild(createdTRLetter2);
        var createdTD2 = document.createElement("td");
        createdTRLetter2.appendChild(createdTD2);
        createdTD2.textContent = arrayLetter2[j];

        for (var w = 0; w < arrayLetter2.length; w++) {
            var createdTD3 = document.createElement("td");
            createdTRLetter2.appendChild(createdTD3);
            createdTD3.setAttribute("id", arrayLetter2[j] + arrayNumbers2[w +1] + "s");
        }
    }

    var dataShot =data.Salvoes;
    var dataGamePlayer2 = data.Game.GamePlayers;
    for(var a = 0; a < dataGamePlayer2.length; a++) {

        var dataIDPlayer2 = dataGamePlayer2[a].Player.id;
    }
    for(var b = 0; b < dataShot.length; b++){
        var dataShotLocation = dataShot[b].salvo;

        for (var q= 0; q < dataShotLocation.length; q++) {
            var dataShotLocation2 = dataShotLocation[q].Location;
            var dataTurn = dataShotLocation[q].Turn;
            var dataPlayerShot = dataShotLocation[q].Player;
            console.log(dataShotLocation2);

            for (var t = 0; t < dataShotLocation2.length;t++){
                if (dataIDPlayer2 == dataPlayerShot) {
                    document.getElementById(dataShotLocation2[t] + "s").setAttribute("class", "locationShot");
                    document.getElementById(dataShotLocation2[t] + "s").textContent = dataTurn;
                }
                 else if (dataIDPlayer2 != dataPlayerShot){
                document.getElementById(dataShotLocation2[t]).setAttribute("class", "locationHit");
                document.getElementById(dataShotLocation2[t]).textContent = dataTurn;
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
        var dataPlayer =  dataGamePlayer[j].Player.email;
    }
    for (let i = 0; i < dataShips.length; i++) {
        var dataTypeShip =  dataShips[i].Type;
        var dataLocation = dataShips[i].Location;
    }
    console.log(idGame);
    console.log(dataPlayer);
    console.log(dataShips);
    console.log(dataTypeShip);
    console.log(dataLocation);

}
console.log(getParameterByName("gp"));
getData();