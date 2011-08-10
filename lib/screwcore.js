var mongoose = require('mongoose');

var Schema = mongoose.Schema, ObjectId = Schema.ObjectId;

var address='localhost';
var base='screwdriver';

var Ideas = mongoose.model('Ideas', new Schema({
   title:   String,   
   data:    String
}));

function greeting() {
   return 'Welcome to Screwdriver server';
}

function connect() {
   mongoose.connect('mongodb://' + address + '/' + base);
}

function push(title, data, errorCallback) {
   var newIdea = new Ideas();
   newIdea.title = title;
   newIdea.data = data;

   newIdea.save(errorCallback);
}

function ideas(successCallback) {
   Ideas.find({}, successCallback);
}

exports.greeting = greeting;
exports.connect = connect;
exports.push = push;
exports.ideas = ideas;
