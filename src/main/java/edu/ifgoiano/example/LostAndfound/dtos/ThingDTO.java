package edu.ifgoiano.example.LostAndfound.dtos;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.Nullable;

public class ThingDTO extends RepresentationModel<ThingDTO>
{
  
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    @Nullable
    private String lost;
   
    public ThingDTO()
    {

    }

    public ThingDTO( String name, String description, String lost )
    {
        this.name = name;
        this.description = description;
        this.lost = lost;
    }

    public String getLost()
    {
        return lost;
    }
    public void setLost(String lost) 
    {
        this.lost = lost;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setDescription(String description) 
    {
        this.description = description;
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
