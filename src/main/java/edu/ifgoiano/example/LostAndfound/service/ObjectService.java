package edu.ifgoiano.example.LostAndfound.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.example.LostAndfound.repository.ObjectRepository;
import edu.ifgoiano.example.LostAndfound.models.Object;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class ObjectService
{
    @Autowired
    ObjectRepository objectRepository;
    
    @Transactional
    public Object save(Object obj) 
    {
        return objectRepository.save( obj );
    }

    public Optional<Object> findByObjectname(String name) 
    {
        return objectRepository.findByName( name );
    }

    public Boolean existsByObjectname(String name)
    {
        return objectRepository.existsByName( name );
    }

    public Optional<Object> findByObjectID(UUID id) 
    {
        return objectRepository.findById( id );
    }

    public Boolean existsByObjectID(UUID  id )
    {
        return objectRepository.existsById( id );
    }   

    @Transactional
    public void delete(Object Object) 
    {
        objectRepository.delete(Object);
    }

    public List<Object> findAll()
    {
        return objectRepository.findAll();
    }

    public void deleteAll(List<Object> Object)
    {
        List<Object> allObject = objectRepository.findAll();
        objectRepository.deleteAll( allObject);
    }

    
}
