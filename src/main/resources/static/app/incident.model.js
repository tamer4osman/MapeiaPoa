var app = {};

app.IncidentList = function() {
  return m.request({ method: 'GET', url: '/' });
};