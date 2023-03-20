package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.repository.PetRepository;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

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
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> findPetById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is less or equal to zero");
        }
        return petRepository.findById(id);
    }

    @Override
    public void deletePetById(long id) throws EntityUpdateFailedException, EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is less or equal to zero");
        }

        Optional<Pet> optionalPet = petRepository.findById(id);
        if(optionalPet.isPresent()){
            petRepository.delete(optionalPet.get());
        } else{
            throw new EntityNotFoundException("No pet by id " + id + " found");
        }
    }
}
