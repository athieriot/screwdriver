define ['text!templates/user.html', \
        'lib/backbone', \
        'lib/handlebars',
        'lib/bootstrap.min'], (template) ->
           Backbone.View.extend
              el: $('#connect_container')

              initialize: ->
                _.bindAll @, 'render', 'getUser'
                @getUser()

              getUser: ->
                $.ajax {
                  url: "/user",
                  success: (user) =>
                    @render(user)

                  error: (jqXHR, status, error) =>
                    @render()
                  }
              
              render: (user) ->
                if user? and user.type is "User"
                  compile_template = Handlebars.compile(template)
                  content = compile_template(user)
                  @el.html(content)

                $('.dropdown-toggle').dropdown()
                @el.show()
                @