package org.mapeiapoa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class OcorrenciaController {

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity listaOcorrencias() {
        return new ResponseEntity("Test", HttpStatus.OK);
    }
}
