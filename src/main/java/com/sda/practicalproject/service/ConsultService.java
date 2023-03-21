package com.sda.practicalproject.service;

import com.sda.practicalproject.model.Consult;
import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.service.exception.EntityNotFoundException;

import java.util.Date;
import java.util.List;

public interface ConsultService {
    void createConsult(long vetId, long petId, Date date, String description) throws EntityNotFoundException, EntityUpdateFailedException;

    List<Consult> getAllConsults();
}
