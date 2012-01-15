define ['require', 'domReady', 'views/searchview'], (require) ->
   domReady = require('domReady')
   SearchView = require('views/searchview')

   domReady () ->
      new SearchView() 
