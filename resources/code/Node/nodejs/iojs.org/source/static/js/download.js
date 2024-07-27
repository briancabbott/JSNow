;
(function (d, n) {
  'use strict';
  var os = n.platform.match(/(Win|Mac|Linux)/);
  var x = n.userAgent.match(/x86_64|Win64|WOW64/) ||
    n.cpuClass === 'x64' ? 'x64' : 'x86';
  var db = d.getElementById('home-downloadbutton');
  if (!db) { return; }
  var version = db.dataset.version;
  var dlLocal = db.dataset.dlLocal;
  switch (os && os[1]) {
    case 'Mac':
      db.href += 'iojs-' + version + '.pkg';
      db.innerText = dlLocal + ' OS X (x64)';
      break;
    case 'Win':
      db.href += 'iojs-' + version + '-' + x + '.msi';
      db.innerText = dlLocal + ' Windows (' + x + ')';
      break;
    case 'Linux':
      db.href += 'iojs-' + version + '-linux-' + x + '.tar.gz';
      db.innerText = dlLocal + ' Linux (' + x + ')';
      break;
  }
})(document, navigator);
