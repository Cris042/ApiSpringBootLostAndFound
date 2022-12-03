package edu.ifgoiano.example.LostAndfound.dtos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.Nullable;

import edu.ifgoiano.example.LostAndfound.models.Image;

public class ThingDTO extends RepresentationModel<ThingDTO>
{
  
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    @Nullable
    private String lost;

    private Set<Image> imagens = new HashSet<>();

    public ThingDTO( String name, String description, String lost, Set<Image> imagens )
    {
        this.name = name;
        this.description = description;
        this.lost = lost;
        this.imagens = imagens;
    }

    
    public Set<Image> getImegens() 
    {
        return imagens;
    }

    public void setImegens(Set<Image> imagens) 
    {
        this.imagens = imagens;
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
