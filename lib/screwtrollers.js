var winston = require('winston');

var screwcore = require('./screwcore');

exports.gets = function(listen) {
    listen.get('/', function(request, response) {
       response.json(screwcore.greeting());
    });
    
    listen.get('/ideas', function(request, response) {
       screwcore.ideas(function(error, ideas) {
          error ? response.json(error, error.status) : response.json(ideas, 200);
       });
    });
    
    listen.get('/idea/:id([0-9a-zA-Z]+)', function (request, response) {
       screwcore.ideaById(request.params.id, function(error, idea) {
          error ? response.json(error, error.status) : response.json(idea, 200);
       });
    });
}

exports.puts = function(listen) {
    listen.put('/idea/:id([0-9a-zA-Z]+)', function (request, response) {
       response.json('Update the idea : ' + request.params.id + ' with the message : ' + request.body);
    });    
}

exports.posts = function(listen) {
    listen.post('/idea', function (request, response) {
       screwcore.push(request.body.title || '', request.body.message || '', function(error) {
          error && winston.info('Error on creating an idea. ' + error);
       });
    
       response.json('Idea send to mongo');
    });
}