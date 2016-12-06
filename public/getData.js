//var connection = io();
var nodes;
var edges

/*function setupConnection() {
    connection.on('connect', function(msg) {
        console.log("Connected to server");
    });

    connection.on('disconnect', function(msg) {
        console.log("disconnect from server");
    });


    //Setup Alert message
    connection.on('pass path', recievePath);
}*/

function getEdges() {
    edges = [
        {from: 1, to: 3},
        {from: 1, to: 2},
        {from: 2, to: 4},
        {from: 2, to: 5}
    ];
    return edges;
}

function getNodes() {
        
    nodes = [
        {id: 1, label: 'Node 1'},
        {id: 2, label: 'Node 2'},
        {id: 3, label: 'Node 3'},
        {id: 4, label: 'Node 4'},
        {id: 5, label: 'Node 5'}
    ];
    return nodes;
}

function getBarData() {
    var barData = [
        {x: 3, y: 10, group:0},
        {x: 6, y: 25, group:0},
        {x: 9, y: 30, group:0},
        {x: 12, y: 10, group:0},
        {x: 15, y: 15, group:0},
        {x: 18, y: 30, group:0},
        {x: 3, y: 12, group:1},
        {x: 6, y: 15, group:1},
        {x: 9, y: 34, group:1},
        {x: 12, y: 24, group:1},
        {x: 15, y: 5,  group:1},
        {x: 18, y: 12, group:1},
        {x: 3, y: 22, group:2},
        {x: 6, y: 14, group:2},
        {x: 9, y: 24, group:2},
        {x: 12, y: 21, group:2},
        {x: 15, y: 30, group:2},
        {x: 18, y: 18, group:2}
    ];
    return barData;
}

function recievePath(data) {
    path = data.path;
    edges = data.edges;

}
