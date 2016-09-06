package org.mapeiapoa.controller;

import org.mapeiapoa.domain.Incident;
import org.mapeiapoa.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IncidentRepository repository;

    @RequestMapping(method = GET)
    public ResponseEntity findAllIncidents() {
        List<Incident> incidents = repository.findAll();
        return new ResponseEntity(incidents, OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity createIncident(@RequestBody Incident incident) {
        Incident savedIncident = repository.save(incident);
        return new ResponseEntity(savedIncident, CREATED);
    }
}
