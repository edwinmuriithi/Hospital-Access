package models.dao;

import models.Treatment;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTreatmentDao implements TreatmentDao{

    private final Sql2o sql2o;
    //Constructor
    public Sql2oTreatmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Treatment treatment) {
        String sql = "INSERT INTO treatment (date_of_admission, disease_name, treatment_administered) " +
                "VALUES(:date_of_admission, :disease_name, :treatment_administered)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(treatment)
                    .executeUpdate()
                    .getKey();
            treatment.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
    @Override
    public List<Treatment> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM treatment")
                    .executeAndFetch(Treatment.class);
        }
    }
    @Override
    public Treatment findById(String date_of_admission) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM treatment WHERE date_of_admission = :date_of_admission")
                    .addParameter("date_of_admission", date_of_admission)
                    .executeAndFetchFirst(Treatment.class);
        }
    }
}
