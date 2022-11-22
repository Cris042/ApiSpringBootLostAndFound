package edu.ifgoiano.example.LostAndfound.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.example.LostAndfound.repository.ThingFoundRepository;
import edu.ifgoiano.example.LostAndfound.models.ThingFound;

@Service
public class ThingFoundService 
{
    @Autowired
    ThingFoundRepository thingFoundRepository;

    @Transactional
    public ThingFound save(ThingFound obj) 
    {
        return thingFoundRepository.save( obj );
    }
    
    public List<ThingFound> findAll()
    {
        return thingFoundRepository.findAll();
    }
}
