var IncidentForm = {
  controller: function() {
    this.incident = m.prop(new Incident())
      this.save = function(incident) {
        Incident.save(incident)
      }
  },
  view: function(ctrl) {
    var incident = ctrl.incident()

      return m("form", [
          m("label", "Description"),
          m("input", {oninput: m.withAttr("value", incident.description), value: incident.description()}),
          m("label", "Latitude"),
          m("input", {oninput: m.withAttr("value", incident.latitude), value: incident.latitude()}),
          m("label", "Longitude"),
          m("input", {oninput: m.withAttr("value", incident.longitude), value: incident.longitude()}),
          m("label", "Type"),
          m("input", {oninput: m.withAttr("value", incident.type), value: incident.type()}),

          m("button[type=button]", {onclick: ctrl.save.bind(this, incident)}, "Save")
      ])
  }
}
