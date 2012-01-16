define ['text!templates/repo.html', \
        'lib/backbone', \
        'lib/handlebars', \
        'lib/jquery.better-autocomplete'], (repo) ->
           Backbone.View.extend
              el: $('#search')
              
              initialize: ->
                 _.bindAll @, 'render', 'initsearch'
                 @initsearch(@render)
              
              render: (result) ->
                 template = Handlebars.compile(repo)
                 content = template(result)
                 
                 $(@el.id + ', #screw-search-details').html(content)
                 return @
                 
              initsearch: (render_callback) ->
                 $(@el.id + ', #screw-search-term').betterAutocomplete('init',
                    '/screw/search', {}, {
                       constructURL: (path, query) ->
                          path + "/" + escape(query)
                          
                       select: (result, $input) ->
                          render_callback(result)
                          $('.better-autocomplete').hide

                       themeResult: (result) ->
                          "<h4><b>" + result.owner + "</b>/" + result.name + "</h4>"

                       processRemoteData: (data) ->
                          data.repositories

                       getGroup: (result) ->
                          "github"
                    }
                 ).attr('placeholder', 'ready')
