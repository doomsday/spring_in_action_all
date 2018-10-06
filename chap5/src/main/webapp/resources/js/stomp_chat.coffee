socket = new SockJS '/chap_five/stomp_chat'

# Effectively wraps SockJS to send STOMP messages over the WebSocket connection.
client = Stomp.over socket

# Subscribes to '/topic/stomp_echo' [@SendTo("/topic/stomp_echo")]
client.connect 'guest', 'guest', (frame) ->
  console.log 'Connected: ' + frame
  client.subscribe '/topic/stomp_echo', (greeting) ->
    value = JSON.parse(greeting.body).message
    document.getElementById('msg_reply').value = value

# Send message to /app/stomp_shout [@MessageMapping("/stomp_shout")]
@sendMsg0 = ->
  console.log 'Sending message to \'/app/stomp_shout\''
  payload = JSON.stringify({'message': document.getElementById('msg_input').value})
  client.send '/app/stomp_shout', {}, payload

# Subscribe to '/app/stomp_shout'
@sendMsg1 = ->
  console.log 'Subscribing to \'/app/stomp_shout\''
  client.subscribe '/app/stomp_shout', (greeting) ->
    value = JSON.parse(greeting.body).message
    document.getElementById('msg_reply').value = value