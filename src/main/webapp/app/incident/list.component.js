var IncidentList = {
  controller: function(){
    this.incidents = Incident.list()
  },
  view: function(ctrl) {
    return m("table", [
        ctrl.incidents().map(function(incident) {
          return m("tr", [
              m("td", incident.description()),
              m("td", incident.latitude()),
              m("td", incident.longitude()),
              m("td", incident.type())
          ])
        })
    ])
  }
}
