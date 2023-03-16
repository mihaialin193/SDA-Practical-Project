package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.VetRepositoryImpl;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;

import java.util.List;

public class VetServiceImpl implements VetService {

    private final VetRepositoryImpl vetRepository;

    public VetServiceImpl(VetRepositoryImpl vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void addVet(String firstName,
                       String lastName,
                       String address,
                       String speciality
    ) throws EntityUpdateFailedException {

        if (firstName == null || firstName.isBlank() || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name is null or blank");
        }
        if (lastName == null || lastName.isBlank() || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name is null or blank");
        }
        if (address == null || address.isBlank() || address.isEmpty()) {
            throw new IllegalArgumentException("Address is null or blank");
        }
        if (speciality == null || speciality.isBlank() || speciality.isEmpty()) {
            throw new IllegalArgumentException("Speciality is null or blank");
        }

        Vet vet = new Vet(firstName, lastName, address, speciality);
        vetRepository.save(vet);
    }

    @Override
    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }
}
