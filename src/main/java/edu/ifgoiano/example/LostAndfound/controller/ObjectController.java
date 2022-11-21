package edu.ifgoiano.example.LostAndfound.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifgoiano.example.LostAndfound.service.ObjectService;
import edu.ifgoiano.example.LostAndfound.models.Object;
import edu.ifgoiano.example.LostAndfound.dtos.ObjectDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/object")
public class ObjectController 
{
    @Autowired
    ObjectService objectService;

    @Autowired
    ObjectDTO IObject;

    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body( objectService.findAll() );
    }
}
