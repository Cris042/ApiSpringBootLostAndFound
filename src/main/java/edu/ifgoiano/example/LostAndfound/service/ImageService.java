package edu.ifgoiano.example.LostAndfound.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifgoiano.example.LostAndfound.models.Image;
import edu.ifgoiano.example.LostAndfound.repository.ImageRepository;

@Service
public class ImageService 
{
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageRepository thingRepository;
    
    @Transactional
    public Image save(Image obj) 
    {
        return thingRepository.save( obj );
    }

    public Optional<Image> findByImagename(String name) 
    {
        return thingRepository.findByName( name );
    }

    @Transactional
    public void delete(Image Image) 
    {
        thingRepository.delete(Image);
    }

    public List<Image> findAll()
    {
        return thingRepository.findAll();
    }

    public void deleteAll(List<Image> Image)
    {
        List<Image> allImage = thingRepository.findAll();
        thingRepository.deleteAll( allImage);
    }


}
