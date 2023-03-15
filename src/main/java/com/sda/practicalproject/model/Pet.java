package com.sda.practicalproject.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    private Long id;
    @Column(name = "race")

    private String race;
    @Column(name = "birth_date")

    private Date birthDate;
    @Column(name = "is_vaccinated")

    private boolean isVaccinated;
    @Column(name = "owner_name")

    private String ownerName;

    @OneToMany(mappedBy = "pet")
    private List<Consult> consults;

    public Pet() {
    }
    public Pet(String race, Date birthDate, boolean isVaccinated, String ownerName) {
        this.race = race;
        this.birthDate = birthDate;
        this.isVaccinated = isVaccinated;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Pet(List<Consult> consults) {
        this.consults = consults;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", race='" + race + '\'' +
                ", birthDate=" + birthDate +
                ", isVaccinated=" + isVaccinated +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
