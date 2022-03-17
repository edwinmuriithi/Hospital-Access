package models.dao;

import models.Hospital;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;




public class Sql2oHospitalDao implements HospitalDao {
    private final Sql2o sql2o;
    public Sql2oHospitalDao(Sql2o sql2o) {this.sql2o = sql2o;}
    @Override
    public void add(Hospital hospital) {

        try (Connection conn = sql2o.open()) {
            String sql = "INSERT INTO hospitals (name, location, hospitallevel) VALUES (:name, :location, :hospitallevel)";
            String id = (String) conn.createQuery(sql, true)
                    .bind(hospital)
                    .executeUpdate()
                    .getKey();
            hospital.setId(id);
        } catch (Sql2oException ex) {
            System.out.println("Hospital not added " + ex);
        }
    }

    @Override
    public List<Hospital> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM hospitals")
                    .executeAndFetch(Hospital.class);
        }
    }

    @Override
    public Hospital findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = sql2o.open()){
            String sql = "DELETE FROM HOSPITALS WHERE id=:id;";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
