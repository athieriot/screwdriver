define ['text!templates/user.html', \
        'lib/backbone', \
        'lib/handlebars'], (template) ->
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
                  }
              
              render: (user) ->
                if user.type is "User"
                  compile_template = Handlebars.compile(template)
                  content = compile_template(user)
                  @el.html(content)

                @el.show()
                @