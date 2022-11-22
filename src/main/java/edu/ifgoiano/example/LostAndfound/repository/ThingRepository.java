package edu.ifgoiano.example.LostAndfound.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.example.LostAndfound.models.Thing;

@Repository
public interface ThingRepository extends JpaRepository<Thing, UUID> 
{
   Boolean existsByName(String name);
   Optional<Thing> findByName(String name);
   boolean existsById(UUID id);
   Optional<Thing> findById(UUID id);
}