package edu.ifgoiano.example.LostAndfound.dtos;

import javax.validation.constraints.NotBlank;

public class ThingFoundDTO 
{
    @NotBlank
    private Long idUser;

    public Long getIdUser() 
    {
        return idUser;
    }
    public void setIdUser(Long idUser) 
    {
        this.idUser = idUser;
    }
}
