angular.module('ElizaWebsocketsApp')

// 'basicMSDOS' service manage the basicMSDOS functionallities
    .factory('elizaWebsocket', function ($state, $http, $stomp) {

        var stompClient = null;

        $stomp.setDebug(function (args) {
            console.log(args);
        });

        return {

            connectEliza: function (setConnnected,addResponse) {
                $stomp.connect('/gs-guide-websocket', {}).then(
                    function (frame) {
                        setConnnected(true);
                        console.log('Connected: ' + frame);
                        $stomp.subscribe('/topic/greetings', function (payload, headers, res) {
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
                $stomp.send('/app/hello',request, {});
            }
        };
    });
