define ['text!templates/repos.html', \
        'lib/backbone', \
        'lib/handlebars'], (repos) ->
           Backbone.View.extend
              el: $('#space')
              
              initialize: ->
                _.bindAll @, 'render', 'getRepos'
                @getRepos()

              getRepos: ->
                $.ajax {
                  url: "/user_repos",
                  success: (user_repos) =>
                    @render(user_repos)
                }
              
              render: (result) ->
                result = {"repos": result}

                compile_template = Handlebars.compile(repos)
                content = compile_template(result)
                 
                $(@el.id + ', #projects').html(content)
                @