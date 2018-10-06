// Generated by CoffeeScript 2.3.1
(function() {
  var client, socket;

  socket = new SockJS('/chap_five/stomp_chat');

  // Effectively wraps SockJS to send STOMP messages over the WebSocket connection.
  client = Stomp.over(socket);

  // Subscribes to '/topic/stomp_echo' [@SendTo("/topic/stomp_echo")]
  client.connect('guest', 'guest', function(frame) {
    console.log('Connected: ' + frame);
    return client.subscribe('/topic/stomp_echo', function(greeting) {
      var value;
      value = JSON.parse(greeting.body).message;
      return document.getElementById('msg_reply').value = value;
    });
  });

  // Send message to /app/stomp_shout [@MessageMapping("/stomp_shout")]
  this.sendMsg0 = function() {
    var payload;
    console.log('Sending message to \'/app/stomp_shout\'');
    payload = JSON.stringify({
      'message': document.getElementById('msg_input').value
    });
    return client.send('/app/stomp_shout', {}, payload);
  };

  // Subscribe to '/app/stomp_shout'
  this.sendMsg1 = function() {
    console.log('Subscribing to \'/app/stomp_shout\'');
    return client.subscribe('/app/stomp_shout', function(greeting) {
      var value;
      value = JSON.parse(greeting.body).message;
      return document.getElementById('msg_reply').value = value;
    });
  };

}).call(this);

//# sourceMappingURL=stomp_chat.js.map
