package com.sda.practicalproject.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "consult")
public class Consult {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    private Long id;
    @Column(name = "appointment_date")

    private Date appointmentDate;
    @Column(name = "description")

    private String description;
    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    public Consult() {
    }

    public Consult(Date appointmentDate, String description) {
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    @Override
    public String toString() {
        return "Consult{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", description='" + description + '\'' +
                '}';
    }
}
