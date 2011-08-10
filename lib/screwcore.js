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

function add(title, data, errorCallback) {
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
exports.add = add;
exports.ideas = ideas;
