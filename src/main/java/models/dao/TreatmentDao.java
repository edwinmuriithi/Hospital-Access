package models.dao;

import models.Treatment;

import java.util.List;

public interface TreatmentDao {
    void add(Treatment treatment);

    List<Treatment> getAll();

    Treatment findById(String national_id);
}
