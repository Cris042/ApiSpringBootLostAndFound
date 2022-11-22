package edu.ifgoiano.example.LostAndfound.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class ImageDTO 
{
    @NotBlank
    private UUID idObject;
}
