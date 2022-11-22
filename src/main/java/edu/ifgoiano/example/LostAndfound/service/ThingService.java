package edu.ifgoiano.example.LostAndfound.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.example.LostAndfound.repository.ThingRepository;
import edu.ifgoiano.example.LostAndfound.models.Thing;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class ThingService
{
    @Autowired
    ThingRepository thingRepository;
    
    @Transactional
    public Thing save(Thing obj) 
    {
        return thingRepository.save( obj );
    }

    public Optional<Thing> findByThingname(String name) 
    {
        return thingRepository.findByName( name );
    }

    public Boolean existsByThingname(String name)
    {
        return thingRepository.existsByName( name );
    }

    public Optional<Thing> findByThingID(UUID id) 
    {
        return thingRepository.findById( id );
    }

    public Boolean existsByThingID(UUID  id )
    {
        return thingRepository.existsById( id );
    }   

    @Transactional
    public void delete(Thing Thing) 
    {
        thingRepository.delete(Thing);
    }

    public List<Thing> findAll()
    {
        return thingRepository.findAll();
    }

    public void deleteAll(List<Thing> Thing)
    {
        List<Thing> allThing = thingRepository.findAll();
        thingRepository.deleteAll( allThing);
    }

    
}
