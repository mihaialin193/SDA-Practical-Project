package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.repository.PetRepository;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public void addPet(String race, Date birthDate, boolean isVaccinated, String ownerName) throws EntityUpdateFailedException {

        if(race.isBlank() || race.isEmpty() || race == null){
            throw new IllegalArgumentException("Race cannot be empty, blank nor null");
        }
        if(birthDate == null){
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        if(birthDate.after(Date.from(Instant.now().plus(Duration.ofDays(1))))){
            throw new IllegalArgumentException("Please insert a date from the past");
        }
        if(ownerName.isBlank() || ownerName.isEmpty() || ownerName == null){
            throw new IllegalArgumentException("Please insert a valid owner name");
        }

        Pet pet = new Pet(race, birthDate, isVaccinated, ownerName);
        petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return null;
    }

    @Override
    public Optional<Pet> findPetById(long id) {
        return Optional.empty();
    }
}
