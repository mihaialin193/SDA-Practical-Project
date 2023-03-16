package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.VetRepositoryImpl;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void updateVet(long id, String lastName, String address, String speciality) throws EntityUpdateFailedException, EntityNotFoundException {

        if (id <= 0) {
            throw new IllegalArgumentException("Id is less or equal to zero");
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

        Optional<Vet> optionalVet = vetRepository.findById(id);
        if (optionalVet.isPresent()) {
            Vet vet = optionalVet.get();
            vet.setLastName(lastName);
            vet.setAddress(address);
            vet.setSpeciality(speciality);

            vetRepository.update(vet);
        } else{
            throw new EntityNotFoundException("Vet not found by id: " + id);
        }
    }

    @Override
    public Optional<Vet> findVetById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is less or equal to zero");
        }

        return vetRepository.findById(id);
    }
}
