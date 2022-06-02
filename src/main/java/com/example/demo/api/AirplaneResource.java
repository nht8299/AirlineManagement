package com.example.demo.api;

import com.example.demo.entity.Airplane;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AirplaneResource.PATH)
@RequiredArgsConstructor
public class AirplaneResource {

    public static final String PATH = "/api/airplane";

    AirplaneService airplaneService;

    @GetMapping
    public ResponseEntity<List<Airplane>> getAll(){return ResponseEntity.ok(airplaneService.getAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Airplane> getAirplaneById (@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        Airplane airplane = airplaneService.findAirplaneById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not Found!"));
        return ResponseEntity.ok(airplane);
    }

    @PostMapping
    public ResponseEntity<Airplane> createAirplane (@RequestBody Airplane airplane){
        Airplane createAirplane = airplaneService.saveAirplane(airplane);
        return ResponseEntity.created(URI.create(AirplaneResource.PATH +"/"+ createAirplane.getAirplaneId())).body(createAirplane);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirplaneById(@PathVariable(value = "id")Integer id) throws ResourceNotFoundException {
        Airplane airplane = airplaneService.findAirplaneById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not Found!"));
        airplaneService.deleteAirplaneById(id);
        return ResponseEntity.noContent().build();
    }
}
