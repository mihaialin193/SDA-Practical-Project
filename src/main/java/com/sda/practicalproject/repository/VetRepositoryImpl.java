package com.sda.practicalproject.repository;

import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.base.RepositoryImpl;

public class VetRepositoryImpl extends RepositoryImpl<Vet> implements VetRepository{

    public VetRepositoryImpl() {
        super(Vet.class);
    }

}
