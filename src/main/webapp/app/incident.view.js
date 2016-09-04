app.view = function(ctrl) {
    return m("html", [
        m("body", [
            m("table", [
                ctrl.incidents().map(function(incident, index) {
                    return m("tr", [
                        m("td", {}, incident.id),
                        m("td", {}, incident.descricao),
                        m("td", {}, incident.longitude),
                        m("td", {}, incident.latitude),
                    ])
                })
            ])
        ])
    ]);
};

m.module(document.getElementById('incidents'), app);