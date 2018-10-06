sock = new SockJS '/chap_five/stomp_spittr'
client = Stomp.over sock

client.connect 'guest', 'guest', (frame) ->
  client.subscribe '/topic/spittle_feed', handleSpittle

handleSpittle = (incoming) ->
  spittle = JSON.parse incoming.body
  date = new Date(spittle.time)
  locale = window.navigator.languages[0];
  spittle.time = "#{date.toLocaleDateString(locale)} #{date.toLocaleTimeString(locale)}"
  source = document.getElementById('spittle-template').innerHTML
  template = Handlebars.compile source
  spittleHtml = template spittle
  template = document.createElement('template')
  do spittleHtml.trim
  template.innerHTML = spittleHtml
  document.getElementById('spittle-list').appendChild(template.content.childNodes[1])