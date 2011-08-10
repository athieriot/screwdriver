var mongoose = require('mongoose');
var settings = require('settings').Settings;

var Schema = mongoose.Schema, ObjectId = Schema.ObjectId;

var Ideas = mongoose.model('Ideas', new Schema({
   title:   String,   
   data:    String
}));

function greeting() {
   return 'Welcome to Screwdriver server';
}

function connect() {
   mongoose.connect('mongodb://' + settings.mongo.host + '/' + settings.mongo.db_name);
}

function push(title, data, errorCallback) {
   var newIdea = new Ideas();
   newIdea.title = title;
   newIdea.data = data;

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
