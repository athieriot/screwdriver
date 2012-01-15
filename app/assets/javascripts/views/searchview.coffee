define ['models/screws', \
        'text!templates/screw.html', \
        'lib/backbone', \
        'lib/handlebars', \
        'lib/jquery.better-autocomplete'], (Screws, screw) ->
           Backbone.View.extend
              el: $('#search')
              collection: new Screws
              
              initialize: ->
                 _.bindAll @, 'render', 'initsearch'
                 @initsearch()
                 @render()
              
              render: ->
                 template = Handlebars.compile(screw)
                 content = template(@collection.models)
                 
                 $(@el.id + ', #screw-results').html(content)
                 return @
                 
              initsearch: ->
                 $(@el.id + ', #screw-search-term').betterAutocomplete('init',
                    '/screw/search', {}, {
                       constructURL: (path, query) ->
                          path + "/" + escape(query)
                          
                       select: (result, $input) =>
                          $(@el, 'screw-search-details').append("<span>" + result + "</span>")

                       themeResult: (result) ->
                          "<h4>" + result.owner + "/" + result.name + "</h4>"

                       processRemoteData: (data) ->
                          data.repositories

                       getGroup: (result) ->
                          "github"
                    }
                 )
