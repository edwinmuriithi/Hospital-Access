package models.dao;

import models.Hospital;
import java.util.List;

public interface HospitalDao {
    void add(Hospital hospital);
    List<Hospital> getAll();
    Hospital findById(int id);
    void deleteById(int id);
}
