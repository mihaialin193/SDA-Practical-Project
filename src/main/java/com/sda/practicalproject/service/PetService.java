package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PetService {

    void addPet(
            String race,
            Date birthDate,
            boolean isVaccinated,
            String ownerName
    ) throws EntityUpdateFailedException;

    List<Pet> getAllPets();

    Optional<Pet> findPetById(long id);

}
