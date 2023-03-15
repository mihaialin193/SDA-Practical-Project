package com.sda.practicalproject.repository;

import com.sda.practicalproject.model.Consult;
import com.sda.practicalproject.repository.base.RepositoryImpl;

public class ConsultRepositoryImpl extends RepositoryImpl<Consult> implements ConsultRepository {
    public ConsultRepositoryImpl() {
        super(Consult.class);
    }
}
