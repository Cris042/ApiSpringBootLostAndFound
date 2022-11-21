package edu.ifgoiano.example.LostAndfound.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifgoiano.example.LostAndfound.models.Object;

@Repository
public interface ObjectRepository extends JpaRepository<Object, UUID> 
{
   Boolean existsByName(String name);
   Optional<Object> findByName(String name);
   boolean existsById(UUID id);
   Optional<Object> findById(UUID id);
}