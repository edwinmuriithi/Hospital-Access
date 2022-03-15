package models.dao;

import models.Patients;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oPatientsDao implements PatientsDao{
    private final Sql2o sql2o;
    public Sql2oPatientsDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Patients patients) {
        String sql = "INSERT INTO patients (name, national_id, date_of_birth) VALUES (:name, :national_id, :date_of_birth)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(patients)
                    .executeUpdate()
                    .getKey();
            patients.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
}
