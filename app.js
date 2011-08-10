
/**
 * Module dependencies.
 */

var express = require('express');
var winston = require('winston');
var screwcore = require('./lib/screwcore');

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
app.get('/', function(req, res){
   res.json(screwcore.greeting());
});

app.get('/ideas', function(req, res) {
   screwcore.ideas(function(error, ideas) {
      error ? res.json(error, error.status) : res.json(ideas, 200);
   });
});

app.get('/idea/:id([0-9]+)', function (req, res) {
   res.json('Display idea with id : ' + req.params.id);
});

// PUT
app.put('/idea/:id([0-9]+)', function (req, res) {
   res.json('Update the idea : ' + req.params.id + ' with the data : ' + req.body);
});

// POST
app.post('/idea', function (req, res) {
   screwcore.add(req.body.title || '', req.body.data || '', function(error) {
      error && winston.info('Error on creating an idea. ' + error);
   });

   res.json('Idea send to mongo');
});

// Start

screwcore.connect();

app.listen(3000);
console.log("Express server listening on port %d in %s mode", app.address().port, app.settings.env);
