define ['text!templates/user_repos.html', \
        'lib/backbone', \
        'lib/handlebars'], (user_repos) ->
           Backbone.View.extend
              el: $('#space')
              
              initialize: ->
                _.bindAll @, 'render', 'getRepos'
                @getRepos()

              getRepos: ->
                $.ajax {
                  url: "/user_repos",
                  success: (repos) =>
                    @render(repos)
                }
              
              render: (result) ->
                #TODO: Extract this way of HandleBar in a utilty function
                compile_template = Handlebars.compile(user_repos)
                content = compile_template({"user_repos": result})
                 
                $(@el.id + ', #user_repos').html(content)
                @