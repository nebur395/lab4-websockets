angular.module('ElizaWebsocketsApp', ['ui.router', 'ngStomp'])

    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider

        //starter screen
            .state('eliza', {
                url: "/eliza",
                templateUrl: "template/eliza.html",
                controller: "elizaCtrl"
            });
        $urlRouterProvider.otherwise('eliza');
    });