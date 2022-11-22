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
    ThingRepository ThingRepository;
    
    @Transactional
    public Thing save(Thing obj) 
    {
        return ThingRepository.save( obj );
    }

    public Optional<Thing> findByThingname(String name) 
    {
        return ThingRepository.findByName( name );
    }

    public Boolean existsByThingname(String name)
    {
        return ThingRepository.existsByName( name );
    }

    public Optional<Thing> findByThingID(UUID id) 
    {
        return ThingRepository.findById( id );
    }

    public Boolean existsByThingID(UUID  id )
    {
        return ThingRepository.existsById( id );
    }   

    @Transactional
    public void delete(Thing Thing) 
    {
        ThingRepository.delete(Thing);
    }

    public List<Thing> findAll()
    {
        return ThingRepository.findAll();
    }

    public void deleteAll(List<Thing> Thing)
    {
        List<Thing> allThing = ThingRepository.findAll();
        ThingRepository.deleteAll( allThing);
    }

    
}
