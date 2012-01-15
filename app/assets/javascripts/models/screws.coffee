define ['lib/backbone'], () ->
   Screw = Backbone.Model.extend({
      defaults:
         part1: 'hello',
         part2: 'world'
   })

   Backbone.Collection.extend({
      model: Screw
   })

