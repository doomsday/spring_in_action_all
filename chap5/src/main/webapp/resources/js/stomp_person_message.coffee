socket = new SockJS '/chap_five/stomp_person_message'

# Effectively wraps SockJS to send STOMP messages over the WebSocket connection.
client = Stomp.over socket

# Subscribes to '/user/topic/stomp_pers_echo' [@SendToUser("/topic/stomp_pers_echo")]
client.connect 'guest', 'guest', (frame) ->
  console.log 'Connected: ' + frame
  client.subscribe '/user/topic/stomp_pers_echo', (greeting) ->
    value = JSON.parse(greeting.body).message
    document.getElementById('msg_reply').value = value

@sendMsg2 = ->
  console.log 'Sending message to \'/app/stomp_pers_shout\''
  payload = JSON.stringify({'message': document.getElementById('msg_input').value})
  client.send '/app/stomp_pers_shout', {}, payload