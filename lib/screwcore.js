var mongoose = require('mongoose');
var settings = require('./settings').Settings;

var Schema = mongoose.Schema, ObjectId = Schema.ObjectId;

var FullText = mongoose.model('FullText', new Schema({
   title:       String,
   fullText:    String,
   source:      String
}));

function greeting() {
   return 'Welcome to Screwdriver server';
}

function connect() {
   var mongo_url = 'mongodb://' + settings.mongo.user_phrase + settings.mongo.host + '/' + settings.mongo.db_name;
   console.log(mongo_url);
   mongoose.connect(mongo_url);
}

function push(title, fullText, source, errorCallback) {
   var newFullText = new FullText();
   newFullText.title = title;
   newFullText.fullText = fullText;
   newFullText.source = source;

   newFullText.save(errorCallback);
}

function find(model, callback, criteria) {
   model.find(criteria || {}, callback);
}

exports.greeting = greeting;
exports.connect = connect;
exports.push = push;
exports.fullTexts = function(callback) {find(FullText, callback);};
exports.fullTextById = function(id, callback) {find(FullText, callback, {"_id": id});};
