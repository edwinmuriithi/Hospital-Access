package models.dao;

import models.Patients;

import java.util.List;

public interface PatientsDao {
    void add(Patients patients);
    List<Patients> getAll();
    Patients findById(String patients_id);
}
