angular.module('ElizaWebsocketsApp', ['ui.router'])

    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider

        //starter screen
            .state('eliza', {
                url: "/eliza",
                templateUrl: "templates/eliza.html",
                controller: "elizaCtrl"
            });

        $urlRouterProvider.otherwise('eliza');
    });
