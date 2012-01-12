define ['domReady'], (domReady) ->
   domReady () ->
      $('body').prepend 'loaded'
