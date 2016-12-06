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
    console.log("anyone home?");
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

function recievePath(data) {
    path = data.path;
    edges = data.edges;

}
