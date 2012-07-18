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

            ProjectApp.ProjectDetailsView = Ember.View.create({
            //entriesBinding: 'controller.namespace.entriesController',
            templateName: 'project-details-template',
            project: screwdriver
        });
        ProjectApp.ProjectDetailsView.appendTo('#project-details');

        ProjectApp.initialize();
        return window.ProjectApp = ProjectApp;
    });
})
