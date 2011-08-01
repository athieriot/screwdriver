var journey = require('journey');
var http = require('http');
var fs = require('fs');

var mrouter = new (journey.Router)();

mrouter.map(function () {
   //Default URL
   this.root.bind(function (req, res) {
      res.send("Welcome to my application")
   });
  
   //GET request on /databases
   this.get('/databases').bind(function (req, res) {
      res.send('it work')
      //do something
   });
   
   //GET request on a specific database - /database/users21
   this.get(/^databases\/([A-Za-z0-9_]+)$/).bind(function (req, res, id) {
      //id contains 'user21' part
   });
      
   /**PUT request example. 
   * You can deal with as many parameters as you need, just write the regexp for it and assign a parameter. 
   * Here is an example to update a document on a collection on MongoDB database. 
   * We have 3 user parameters - 6 parameters in URL:
   **/
   this.put(/^databases\/([A-Za-z0-9_]+)\/collections+\/([A-Za-z0-9_]+)\/documents+\/([A-Za-z0-9_]+)$/).bind(function (req, res, dbid, collid, docid, data) {
      res.send('URLpdate '+docid+ 'in '+collid + 'on: '+ dbid);
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
