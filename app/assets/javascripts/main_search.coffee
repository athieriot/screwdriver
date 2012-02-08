define ['require', \
        'views/searchview.min', \
        'views/connectview.min'], (require) ->
  ConnectView = require('views/connectview.min')
  SearchView = require('views/searchview.min')

  $(document).ready ->
    new ConnectView()
    new SearchView()
