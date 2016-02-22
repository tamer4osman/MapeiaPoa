package org.mapeiapoa.repository;

import org.mapeiapoa.domain.Incident;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncidentRepository extends MongoRepository<Incident, String> {
    Incident findByLatitude(String latitude);
    Incident findByLongitude(String longitude);
}
