var mongoose = require('mongoose');
var settings = require('./settings').Settings;

var Schema = mongoose.Schema, ObjectId = Schema.ObjectId;

var Ideas = mongoose.model('Ideas', new Schema({
   title:      String,
   message:    String
}));

function greeting() {
   return 'Welcome to Screwdriver server';
}

function connect() {
   var mongo_url = 'mongodb://' + settings.mongo.user_phrase + settings.mongo.host + '/' + settings.mongo.db_name;
   console.log(mongo_url);
   mongoose.connect(mongo_url);
}

function push(title, message, errorCallback) {
   var newIdea = new Ideas();
   newIdea.title = title;
   newIdea.message = message;

   newIdea.save(errorCallback);
}

function find(model, callback, criteria) {
   model.find(criteria || {}, callback);
}

exports.greeting = greeting;
exports.connect = connect;
exports.push = push;
exports.ideas = function(callback) {find(Ideas, callback);};
exports.ideaById = function(id, callback) {find(Ideas, callback, {"_id": id});};
