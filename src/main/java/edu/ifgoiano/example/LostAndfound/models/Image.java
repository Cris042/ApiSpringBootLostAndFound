package edu.ifgoiano.example.LostAndfound.models;

import javax.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Entity
@Table
( 
    name = "image",
    uniqueConstraints = 
    {
       @UniqueConstraint(columnNames = "name"),
    }
)
public class Image extends RepresentationModel<Image>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;

    public UUID getId() 
    {
        return id;
    }
    public void setId(UUID idImg) 
    {
        this.id = idImg;
    }

    public String getName() 
    {
        return name;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
}
