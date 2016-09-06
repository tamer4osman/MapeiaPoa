var IncidentWidget = {
  view: function(ctrl) {
    return [
      m.component(IncidentForm, {onsave: ctrl.save}),
      m.component(IncidentList, {incidents: ctrl.incidents})
    ]
  }
}
