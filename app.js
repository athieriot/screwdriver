require.paths.unshift('./lib');

var express = require('express');
var winston = require('winston');
var screwcore = require('screwcore');

var app = module.exports = express.createServer();

// Configuration

app.configure(function(){
   app.use(express.logger());
   app.use(express.bodyParser());
   app.use(express.favicon());
   app.use(express.methodOverride());
   app.use(app.router);
   app.use(express.static(__dirname + '/public'));
});

app.configure('development', function(){
  app.use(express.errorHandler({ dumpExceptions: true, showStack: true })); 
});

app.configure('production', function(){
  app.use(express.errorHandler()); 
});

// Routes
// GET
app.get('/', function(request, response){
   response.json(screwcore.greeting());
});

app.get('/ideas', function(request, response) {
   screwcore.ideas(function(error, ideas) {
      error ? response.json(error, error.status) : response.json(ideas, 200);
   });
});

app.get('/idea/:id([0-9a-zA-Z]+)', function (request, response) {
   screwcore.ideaById(request.params.id, function(error, idea) {
      error ? response.json(error, error.status) : response.json(idea, 200);
   });
});

// PUT
app.put('/idea/:id([0-9a-zA-Z]+)', function (request, response) {
   response.json('Update the idea : ' + request.params.id + ' with the data : ' + request.body);
});

// POST
app.post('/idea', function (request, response) {
   screwcore.push(request.body.title || '', request.body.data || '', function(error) {
      error && winston.info('Error on creating an idea. ' + error);
   });

   response.json('Idea send to mongo');
});

// Start

screwcore.connect();

app.listen(3000);
console.log("Express server listening on port %d in %s mode", app.address().port, app.settings.env);
