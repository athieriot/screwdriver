define ['require', 'domReady', 'views/searchview.min'], (require) ->
   domReady = require('domReady')
   SearchView = require('views/searchview.min')

   domReady () ->
      new SearchView() 
