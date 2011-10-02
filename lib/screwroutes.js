var screwcore = require('./lib/screwcore');

function gets(listen) {
    // GET
    listen.get('/', function(request, response){
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
}

// PUT
app.put('/idea/:id([0-9a-zA-Z]+)', function (request, response) {
   response.json('Update the idea : ' + request.params.id + ' with the message : ' + request.body);
});

// POST
app.post('/idea', function (request, response) {
   screwcore.push(request.body.title || '', request.body.message || '', function(error) {
      error && winston.info('Error on creating an idea. ' + error);
   });

   response.json('Idea send to mongo');
});