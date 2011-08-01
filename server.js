var journey = require('journey');
var http = require('http');
var fs = require('fs');

var mrouter = new (journey.Router)();

mrouter.map(function () {
   //Default URL
   this.root.bind(function (req, res) {
      res.send("Welcome to Screwdriver server");
   });
  
   //GET request on /ideas
   this.get('/ideas').bind(function (req, res) {
      res.send('Display all ideas');
   });
   
   //GET request on a specific database - /database/users21
   this.get(/^idea\/([0-9_]+)$/).bind(function (req, res, id) {
      res.send("Display idea with id : " + id);
   });
      
   /**PUT request example. 
   * You can deal with as many parameters as you need, just write the regexp for it and assign a parameter. 
   * Here is an example to update a document on a collection on MongoDB database. 
   * We have 3 user parameters - 6 parameters in URL:
   **/
   this.put(/^idea\/([A-Za-z0-9_]+)$/).bind(function (req, res, id, data) {
      res.send('Update the idea : ' + id + ' with the data : ' + data);
   });
}); //end mapping

http.createServer(function (request, response) {
   var body = "";
           
   request.addListener('data', function (chunk) { body += chunk });
   request.addListener('end', function () {                      
      mrouter.handle(request, body, function (result) {                                 
         response.writeHead(result.status, result.headers);
         response.end(result.body);
      });
   });
}).listen(8081);
