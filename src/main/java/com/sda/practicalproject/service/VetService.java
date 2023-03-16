package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Vet;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.util.List;

public interface VetService {

    void addVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    ) throws EntityUpdateFailedException;

    List<Vet> getAllVets();

    void updateVet(long id,String lastName, String address, String speciality) throws EntityUpdateFailedException, EntityNotFoundException;
}
