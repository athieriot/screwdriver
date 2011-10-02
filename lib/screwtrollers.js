var winston = require('winston');

var screwcore = require('./screwcore');

exports.start = function(server) {
    gets(server);
    //puts(server);
    //posts(server); 
    
    // Start
    screwcore.connect();
};

gets = function(listen) {
    listen.get('/', function(request, response) {
       response.json(screwcore.greeting());
    });
    
    listen.get('/fullTexts', function(request, response) {
       screwcore.fullTexts(function(error, fullTexts) {
          error ? response.json(error, error.status) : response.json(fullTexts, 200);
       });
    });
    
    listen.get('/fullText/:id([0-9a-zA-Z]+)', function (request, response) {
       screwcore.fullTextById(request.params.id, function(error, fullText) {
          error ? response.json(error, error.status) : response.json(fullText, 200);
       });
    });
};

/*puts = function(listen) {
    listen.put('/idea/:id([0-9a-zA-Z]+)', function (request, response) {
       response.json('Update the idea : ' + request.params.id + ' with the message : ' + request.body);
    });    
};

posts = function(listen) {
    listen.post('/idea', function (request, response) {
       screwcore.push(request.body.title || '', request.body.message || '', function(error) {
          error && winston.info('Error on creating an idea. ' + error);
       });
    
       response.json('Idea send to mongo');
    });
};*/