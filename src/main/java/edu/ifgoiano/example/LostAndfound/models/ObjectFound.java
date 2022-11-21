package edu.ifgoiano.example.LostAndfound.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "objectfound")
public class ObjectFound 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private UUID idUser;
    @Column(nullable = false)
    private UUID idObject;
    @Column(nullable = false)
    private LocalDateTime creationDate;


    public UUID getIdObject() 
    {
        return idObject;
    }

    public void setIdObject(UUID idObject) 
    {
        this.idObject = idObject;
    }

    public UUID getIdUser() 
    {
        return idUser;
    }

    public void setIdUser(UUID idUser)
    {
        this.idUser = idUser;
    }

    public LocalDateTime getCreationDate() 
    {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) 
    {
		this.creationDate = creationDate;
	}


   
   
}
