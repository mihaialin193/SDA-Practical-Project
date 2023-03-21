package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Consult;
import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.*;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ConsultServiceImpl implements ConsultService {

    private final VetRepository vetRepository;
    private final PetRepository petRepository;
    private final ConsultRepository consultRepository;

    public ConsultServiceImpl(VetRepository vetRepository, PetRepository petRepository, ConsultRepository consultRepository) {
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
        this.consultRepository = consultRepository;
    }


    @Override
    public void createConsult(long vetId, long petId, Date date, String description) throws EntityNotFoundException, EntityUpdateFailedException {
        if (vetId <= 0) {
            throw new IllegalArgumentException("Invalid vet id, must be greater than 0");
        }
        if (petId <= 0) {
            throw new IllegalArgumentException("Invalid pet id, must be greater than 0");
        }
        if (date == null) {
            throw new IllegalArgumentException("Invalid date, null value found");
        }
        if (date.before(Date.from(Instant.now().minus(Duration.ofDays(1))))) {
            throw new IllegalArgumentException("Cannot have a date in the past");
        }
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null, empty nor blank");
        }

        Optional<Vet> vetOptional = vetRepository.findById(vetId);
        Optional<Pet> petOptional = petRepository.findById(petId);
        if (vetOptional.isEmpty()) {
            throw new EntityNotFoundException("Vet at id: " + vetId + " does not exist");
        }
        if (petOptional.isEmpty()) {
            throw new EntityNotFoundException("Pet at id: " + petId + " does not exist");
        }

        Consult consult = new Consult(date,description);
        consult.setVet(vetOptional.get());
        consult.setPet(petOptional.get());

        consultRepository.save(consult);
        System.out.println("Consult was saved successfully");

    }

    @Override
    public List<Consult> getAllConsults() {
        return consultRepository.findAll();
    }
}
