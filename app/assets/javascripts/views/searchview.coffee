define ['text!templates/repo.html', \
        'text!templates/driver.html', \
        'lib/backbone', \
        'lib/handlebars', \
        'lib/jquery.better-autocomplete'], (repo, user) ->
           Backbone.View.extend
              screw_search_url: '/project/search'
              driver_search_url: '/driver/search'

              el: $('#search')
              
              initialize: ->
                 _.bindAll @, 'render', 'init_screw_search', 'init_driver_search'
                 @init_screw_search(@render)
                 @init_driver_search(@render)
              
              render: (result) ->
                 template = if result.type is 'repo' then repo else user
                 result_element = if result.type is 'repo' then '#screw-search-details' else '#driver-search-details'

                 compile_template = Handlebars.compile(template)
                 content = compile_template(result)
                 
                 $(@el.id + ', ' + result_element).html(content)
                 @

              init_screw_search: (render_callback) ->
                 $(@el.id + ', #screw-search-term').betterAutocomplete(
                    'init',
                    @screw_search_url,
                    {}, {
                       constructURL: (path, query) ->
                          path + "/" + escape(query)
                          
                       select: (result, $input) ->
                          render_callback(result)

                       themeResult: (result) ->
                          "<h4><b>" + result.owner + "</b>/" + result.name + "</h4>"

                       processRemoteData: (data) ->
                          data.repositories

                       getGroup: (result) ->
                          "github"
                    }
                 ).attr('placeholder', 'ready')

              init_driver_search: (render_callback) ->
                $(@el.id + ', #driver-search-term').betterAutocomplete(
                  'init',
                  @driver_search_url,
                  {}, {
                  constructURL: (path, query) ->
                    path + "/" + escape(query)

                  select: (result, $input) ->
                    render_callback(result)

                  themeResult: (result) ->
                    "<h4><b>" + result.login + "</b>/" + result.name + "</h4>"

                  processRemoteData: (data) ->
                    data.users

                  getGroup: (result) ->
                    "github"
                  }
                ).attr('placeholder', 'ready')

