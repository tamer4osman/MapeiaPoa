app.controller = function() {
    var incidents = app.IncidentList();
    return {
        incidents: incidents,
        rotate: function() {
            incidents().push(incidents().shift());
        }
    }
};