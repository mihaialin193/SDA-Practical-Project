package com.sda.practicalproject.repository;

import com.sda.practicalproject.model.Pet;
import com.sda.practicalproject.repository.base.RepositoryImpl;

public class PetRepositoryImpl extends RepositoryImpl<Pet> implements PetRepository {

    public PetRepositoryImpl() {
        super(Pet.class);
    }
}
