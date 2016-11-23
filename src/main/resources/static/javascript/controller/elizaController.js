angular.module('ElizaWebsocketsApp')

    .controller('elizaCtrl', ['$scope', '$state', 'elizaWebsocket', function ($scope, $state, elizaWebsocket) {

        $scope.connect = false;
        $scope.requestEliza = "";
        $scope.responsesList = [];

        var setConnnected = function (connected) {
            $scope.connect = connected;
            $scope.responsesList = [];
        };

        $scope.addResponse = function (response) {
            $scope.responsesList.push(response);
            $scope.requestEliza = "";
        };

        $scope.connectEliza = function () {
            elizaWebsocket.connectEliza(setConnnected,$scope.addResponse);
        };

        $scope.disconnectEliza = function () {
            elizaWebsocket.disconnectEliza(setConnnected);
        };

        $scope.sendRequest = function () {
            elizaWebsocket.sendRequestToEliza($scope.requestEliza,$scope.addResponse);
        }
    }]);
