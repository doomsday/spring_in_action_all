url = "ws://#{window.location.host}/chap_five/marco"
sock = new WebSocket(url)

@sendMsg = ->
  console.log 'Sending message...'
  sock.send document.getElementById('msg_input').value

sock.onopen = ->
  console.log 'WebSocket opening...'

sock.onclose = ->
  console.log 'WebSocket closing...'

sock.onmessage = (e) ->
  console.log 'Received message: ', e.data
  document.getElementById('msg_reply').value = e.data