package edu.ifgoiano.example.LostAndfound.models;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
(
    name = "thing",
    uniqueConstraints = 
    {
       @UniqueConstraint(columnNames = "name"),
    }
)
public class Thing implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String lost;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "imagens", joinColumns = @JoinColumn(name = "id_thing"),inverseJoinColumns = @JoinColumn(name = "id_img"))
    private Set<Image> imegens = new HashSet<>();

 
    public Set<Image> getImegens() 
    {
        return imegens;
    }

    public void setImegens(Set<Image> imegens) 
    {
        this.imegens = imegens;
    }

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
