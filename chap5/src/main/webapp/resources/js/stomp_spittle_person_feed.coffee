sock = new SockJS '/chap_five/stomp_person_spittr'
client = Stomp.over sock

client.connect 'guest', 'guest', (frame) ->
  client.subscribe '/user/topic/spittle_person_feed', handleSpittle

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
  document.getElementById('spittle-pers-list').appendChild(template.content.childNodes[1])