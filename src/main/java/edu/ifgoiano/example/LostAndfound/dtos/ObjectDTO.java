package edu.ifgoiano.example.LostAndfound.dtos;

import javax.validation.constraints.NotBlank;

public class ObjectDTO 
{
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String lost;

    public ObjectDTO( String name, String description, String lost )
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
