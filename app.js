
/**
 * Module dependencies.
 */

var express = require('express');
var winston = require('winston');
var mongo_ideas = require('./lib/mongo_ideas');

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

app.get('/', function(req, res){
   res.json('Welcome to Screwdriver server');
});

app.get('/ideas', function(req, res) {
   mongo_ideas.all(function(err, docs) {
      err ? res.json(err, err.status): res.json(docs, 200);
   });
});

app.get('/idea/:id([0-9]+)', function (req, res) {
   res.json('Display idea with id : ' + req.params.id);
});

app.put('/idea/:id([0-9]+)', function (req, res) {
   res.json('Update the idea : ' + req.params.id + ' with the data : ' + req.body);
});

app.post('/idea', function (req, res) {
   mongo_ideas.add(req.body.title || '', req.body.data || '', function(error) {
      error && winston.info('Error on creating an idea. ' + error);
   });

   res.json('Idea send to mongo');
});

mongo_ideas.connect();

app.listen(3000);
console.log("Express server listening on port %d in %s mode", app.address().port, app.settings.env);
