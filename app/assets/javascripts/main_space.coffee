define ['require', \
        'views/spaceview.min', \
        'views/connectview.min'], (require) ->
  ConnectView = require('views/connectview.min')
  SpaceView = require('views/spaceview.min')

  $(document).ready ->
    new ConnectView()
    new SpaceView()
