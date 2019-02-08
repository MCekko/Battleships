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