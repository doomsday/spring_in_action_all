// Generated by CoffeeScript 2.3.1
(function() {
  var client, handleSpittle, sock;

  sock = new SockJS('/chap_five/stomp_person_spittr');

  client = Stomp.over(sock);

  client.connect('guest', 'guest', function(frame) {
    return client.subscribe('/user/topic/spittle_person_feed', handleSpittle);
  });

  handleSpittle = function(incoming) {
    var date, locale, source, spittle, spittleHtml, template;
    spittle = JSON.parse(incoming.body);
    date = new Date(spittle.time);
    locale = window.navigator.languages[0];
    spittle.time = `${date.toLocaleDateString(locale)} ${date.toLocaleTimeString(locale)}`;
    source = document.getElementById('spittle-template').innerHTML;
    template = Handlebars.compile(source);
    spittleHtml = template(spittle);
    template = document.createElement('template');
    spittleHtml.trim();
    template.innerHTML = spittleHtml;
    return document.getElementById('spittle-pers-list').appendChild(template.content.childNodes[1]);
  };

}).call(this);

//# sourceMappingURL=stomp_spittle_person_feed.js.map
