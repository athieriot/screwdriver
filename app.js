var express = require('express');
var winston = require('winston');

var settings = require('./lib/settings').Settings;

var screwtrollers = require('./lib/screwtrollers');
var screwcore = require('./lib/screwcore');

var app = module.exports = express.createServer();

// Configuration

app.configure(function() {
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
screwtrollers.start(app);

app.listen(process.env.C9_PORT || 3000);
winston.log("Express server listening on port %d in %s mode", app.address().port, app.settings.env);