angular.module('ElizaWebsocketsApp')

    .controller('elizaCtrl', ['$scope', '$state', 'elizaWebsocket', function ($scope, $state, elizaWebsocket) {

        $scope.connect = false;
        $scope.requestEliza = "";
        $scope.responsesList = [];

        var setConnnected = function (connected) {
            $scope.connect = connected;
            $scope.responsesList = [];
        };

        var addResponse = function (response) {
            $scope.responsesList.push(response);
            $scope.requestEliza = "";
            $scope.$apply();
        };

        $scope.connectEliza = function () {
            elizaWebsocket.connectEliza(setConnnected,addResponse);
        };

        $scope.disconnectEliza = function () {
            elizaWebsocket.disconnectEliza(setConnnected);
        };

        $scope.sendRequest = function () {
            elizaWebsocket.sendRequestToEliza($scope.requestEliza,addResponse);
        }
    }]);
