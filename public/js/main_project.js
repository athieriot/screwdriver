define(['lib/underscore', 'lib/ember'], function() {

    $(document).ready(function() {

        var ProjectApp = Ember.Application.create({
            rootElement: '#project-container'
        });

        ProjectApp.Project = Ember.Object.extend({
            name: null,
            sources: null
        });

        var screwdriver = ProjectApp.Project.create({
            name: "My awesome and useless project"
        });

        ProjectApp.ProjectConroller = Ember.ArrayController.create({
            content: [screwdriver]
        });

        ProjectApp.ProjectDetailsView = Ember.CollectionView.create({
            content: ProjectApp.ProjectConroller,
            itemViewClass: Ember.View.extend({
                templateName: 'project-details-template'
            })
        });
        ProjectApp.ProjectDetailsView.appendTo('#project-details');

/*
        ProjectApp.Router = Ember.Router.extend({
            root: Ember.Route.extend({

                index: Ember.Route.extend({
                    route: '/',
                    connectOutlets: function( router ) {
                        var controller = router.get( 'applicationController' );
                        var context = controller.namespace.entriesController;
                        context.set( 'filterBy', '' );

                        // This require was left here exclusively for design purposes
                        // Loads decoupled controller/view based on current route
                        require([ 'app/controllers/todos', 'app/views/items' ],
                            function( TodosController, ItemsView ) {
                                controller.connectOutlet({
                                    viewClass: ItemsView,
                                    controller: TodosController.create(),
                                    context: context
                                });
                            }
                        );

                    }
                })
            })
        });
*/
        ProjectApp.initialize();
        return window.ProjectApp = ProjectApp;
    });
})
