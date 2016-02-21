package org.mapeiapoa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class OcorrenciaController {

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity listaOcorrencias() {
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Test");
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
