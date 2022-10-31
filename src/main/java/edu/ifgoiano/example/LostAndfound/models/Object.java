package edu.ifgoiano.example.LostAndfound.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "object")
public class Object implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String lost;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    public UUID getId() 
    {
        return id;
    }

    public void setId(UUID id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getLost() 
    {
        return lost;
    }

    public void setLost(String lost) 
    {
        this.lost = lost;
    }

    public LocalDateTime getCreationDate() 
    {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) 
    {
		this.creationDate = creationDate;
	}

    public LocalDateTime getDateUpdate() 
    {
		return dateUpdate;
	}

	public void setDateUpdate(LocalDateTime dateUpdate) 
    {
		this.dateUpdate = dateUpdate;

	}

}
