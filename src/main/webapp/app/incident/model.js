var Incident = function(data){
  data = data || {};
  this.description = m.prop(data.description);
  this.latitude = m.prop(data.latitude);
  this.longitude = m.prop(data.longitude);
  this.type = m.prop(data.type);
}

Incident.list = function(data) {
  return m.request({method: "GET", url: "/mapeia-poa-0.1.0/incident", type: Incident})
}
Incident.save = function(data) {
  return m.request({method: "POST", url: "/mapeia-poa-0.1.0/incident", data: data})
}
