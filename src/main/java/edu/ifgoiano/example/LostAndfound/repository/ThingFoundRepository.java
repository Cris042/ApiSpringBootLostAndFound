package edu.ifgoiano.example.LostAndfound.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.example.LostAndfound.models.ThingFound;

@Repository
public interface ThingFoundRepository extends JpaRepository<ThingFound, UUID> 
{
    
}
