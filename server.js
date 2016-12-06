var express = require('express');
var app = express();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var path = require('path');


http.listen(3000, function(){
  console.log('listening on *:8080');
});

app.use(express.static(path.join(__dirname, 'public')));

io.on('connection', function(socket) {
    console.log('client connected');

    //Whenever someone disconnects this piece of code executed
    socket.on('disconnect', function() {
        console.log(socket.name + ' disconnected');
    });

});