angular.module('ElizaWebsocketsApp')

// 'basicMSDOS' service manage the basicMSDOS functionallities
    .factory('elizaWebsocket', function ($state, $http, $stomp) {

        $stomp.setDebug(function (args) {
            console.log(args);
        });

        return {

            connectEliza: function (setConnnected,addResponse) {
                $stomp.connect('/websocketEliza', {}).then(
                    function (frame) {
                        setConnnected(true);
                        console.log('Connected: ' + frame);
                        $stomp.subscribe('/topic/eliza', function (payload, headers, res) {
                            console.log(payload);
                            addResponse(payload);
                        }, {})
                    }, function(error) {
                        console.error(error);
                    }
                );
            },

            disconnectEliza: function (setConnnected) {
                $stomp.disconnect();
                setConnnected(false);
                console.log("Disconnected");
            },

            sendRequestToEliza: function (request) {
                $stomp.send('/app/doctor',request, {});
            }
        };
    });
