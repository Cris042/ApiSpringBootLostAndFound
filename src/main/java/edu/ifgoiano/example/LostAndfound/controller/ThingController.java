package edu.ifgoiano.example.LostAndfound.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifgoiano.example.LostAndfound.dtos.ThingDTO;
import edu.ifgoiano.example.LostAndfound.dtos.ThingFoundDTO;
import edu.ifgoiano.example.LostAndfound.exceptions.others.NotFoundException;
import edu.ifgoiano.example.LostAndfound.exceptions.others.UnsupportedException;
import edu.ifgoiano.example.LostAndfound.models.Thing;
import edu.ifgoiano.example.LostAndfound.models.ThingFound;
import edu.ifgoiano.example.LostAndfound.service.ThingFoundService;
import edu.ifgoiano.example.LostAndfound.service.ThingService;


@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/thing")
public class ThingController 
{
    @Autowired
    ThingService thingService;

    @Autowired
    ThingFoundService thingFoundService;

    @GetMapping("all")
    public ResponseEntity<List<ThingDTO>> getAll(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable)
    {
        var thingAll = thingService.findAll();
        
        List<ThingDTO> thingList = new ArrayList<>();
        
        for(Thing thing : thingAll) 
        {
            UUID id = thing.getId();
            ThingDTO thingObj = new ThingDTO(thing.getName(), thing.getDescription(), thing.getLost() );
            thingObj.add( WebMvcLinkBuilder.linkTo( WebMvcLinkBuilder.methodOn( ThingController.class ).get( id ) ).withSelfRel() );
            thingList.add(thingObj);       
        }

        return ResponseEntity.status(HttpStatus.OK).body( thingList );
    }

    @GetMapping("{id}")
    public ResponseEntity<ThingDTO> get(@PathVariable(value = "id") UUID id)
    {
        Optional<Thing> obj = thingService.findByThingID(id);

        if (!obj.isPresent()) 
        {
            throw new NotFoundException("Not found!.");
        }
       
        ThingDTO thingobj = new ThingDTO(obj.get().getName(), obj.get().getDescription(), obj.get().getLost() );
        thingobj.add( WebMvcLinkBuilder.linkTo( WebMvcLinkBuilder.methodOn( ThingController.class ).getAll(null) ).withSelfRel() );

        return ResponseEntity.status(HttpStatus.OK).body( thingobj );
    }

    @PostMapping("add")
    public ResponseEntity<Object> save(@Valid @RequestBody ThingDTO thingDTO)
    {
        if( thingService.existsByThingname( thingDTO.getName() ))
        {
            throw new UnsupportedException("Conflict: Name is already in use! !");
        }

        var thingEntities = new Thing();
        BeanUtils.copyProperties( thingDTO, thingEntities);  
        thingEntities.setCreationDate(LocalDateTime.now( ZoneId.of("America/Sao_Paulo")) );
        thingEntities.setDateUpdate(LocalDateTime.now( ZoneId.of("America/Sao_Paulo")) );
        thingEntities.setLost("true");

        return ResponseEntity.status(HttpStatus.CREATED).body( thingService.save( thingEntities ));
    }   

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id)
    {
        Optional<Thing> obj = thingService.findByThingID(id);

        if (!obj.isPresent()) 
        {
            throw new NotFoundException("Not found!.");
        }
        
        thingService.delete( obj.get() );
        return ResponseEntity.status(HttpStatus.OK).body( "Successfully Deleted!" );
    }

    @PutMapping("updatLost/{id}")
    public ResponseEntity<Object> updatLost(@PathVariable(value = "id") UUID id, @RequestBody @Valid ThingFoundDTO thingFoundDTO)
    {
        Optional<Thing> obj = thingService.findByThingID(id);

        if (!obj.isPresent()) 
        {
            throw new NotFoundException("Not found!.");
        }

        if( obj.get().getLost().equals("false") )
        {
            throw new UnsupportedException("invalid lost attribute!");
        }
        
        var thingEntities = new Thing();
        BeanUtils.copyProperties( obj.get(), thingEntities);  
        thingEntities.setDateUpdate(LocalDateTime.now( ZoneId.of("America/Sao_Paulo")) );
        thingEntities.setLost("false");

        var thingFoundEntities = new ThingFound();
        BeanUtils.copyProperties( thingFoundDTO, thingFoundEntities);  
        thingFoundEntities.setCreationDate(LocalDateTime.now( ZoneId.of("America/Sao_Paulo")));
        thingFoundEntities.setIdObject(id);

        thingFoundService.save( thingFoundEntities );
        thingService.save( thingEntities );
       
        return ResponseEntity.status(HttpStatus.OK).body( "Successfully Update!"  );
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ThingDTO thingDTO)
    {
        Optional<Thing> obj = thingService.findByThingID(id);

        if (!obj.isPresent()) 
        {
            throw new NotFoundException("Not found!.");
        }
        if( !obj.get().getName().equals( thingDTO.getName() ) )
        {
            if( thingService.existsByThingname( thingDTO.getName() ))
            {
                throw new UnsupportedException("Conflict: Name is already in use! !");
            }
        }
        if( !obj.get().getLost().equals( thingDTO.getLost() ) )
        {
            if( !thingDTO.getLost().equals("true") && !thingDTO.getLost().equals("false") )
            {
                throw new UnsupportedException("invalid lost attribute!");
            }
        }

        var thingEntities = new Thing();
        BeanUtils.copyProperties( thingDTO, thingEntities);  
        thingEntities.setId( obj.get().getId() );
        thingEntities.setCreationDate( obj.get().getCreationDate() );
        thingEntities.setDateUpdate(LocalDateTime.now( ZoneId.of("America/Sao_Paulo")) );

         
        return ResponseEntity.status(HttpStatus.OK).body( thingService.save( thingEntities ) );
    }
}
