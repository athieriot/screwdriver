var assert = require('assert');

exports['test Greetin'] = function() {
   var greet = require('screwcore').greeting();

   assert.isNotNull(greet);
};
