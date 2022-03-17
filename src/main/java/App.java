
import models.Hospital;


import com.google.gson.Gson;
import models.Treatment;
import models.dao.Sql2oTreatmentDao;


import models.Patients;
import com.google.gson.Gson;
import models.dao.*;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;
import java.util.List;


public class App {
    public static void main (String[] args){
        Gson gson= new Gson();
        Sql2oTreatmentDao treatmentDao;
        Sql2oPatientsDao patientsDao;
        Sql2oHospitalDao hospitalDao;


        //Connection to local db
//        String connectionString = "jdbc:postgresql://localhost:5432/hospitalaccess";
//        Sql2o sql2o = new Sql2o(connectionString, "muriithi", "123456");

        //    Heroku Db Connect
          String connectionString = "jdbc:postgresql://ec2-52-44-209-165.compute-1.amazonaws.com:5432/dcoda2pl8k4cvr"; //!
          Sql2o sql2o = new Sql2o(connectionString, "knbsqhvnmocmem", "3136b369e1c2035a08556b8b695c3da0cbb4669e3765838b94352674fafe1915"); //!


        treatmentDao= new Sql2oTreatmentDao(sql2o);
        patientsDao = new Sql2oPatientsDao(sql2o);
        hospitalDao = new Sql2oHospitalDao(sql2o);
        Connection conn;
        conn = sql2o.open();


        //Create treatment
        post("/treatment/new", "application/json", (request, response) -> {
            Treatment treatment = gson.fromJson(request.body(), Treatment.class);
            treatmentDao.add(treatment);
            response.status(201);
            response.type("application/json");
            return gson.toJson(treatment);
        });

        //Get All Treatments
        get("/treatment", "application/json", (req, res) -> {
            if(treatmentDao.getAll().size() > 0) {
                return gson.toJson(treatmentDao.getAll());
            }else {
                return "{\"message\":\"No treatment has been administered in the database.\"}";
            }
        });

        //Search Treatments
        get("/treatment/:national_id", "application/json", (req, res) -> {
            String NationalId = req.params("national_id");
            System.out.println(NationalId);
            Treatment treatment = treatmentDao.findById(NationalId);
            return gson.toJson(treatment);
        });

        //Create patients
        post("/patient/new", "application/json", (request, response) -> {
            Patients patients = gson.fromJson(request.body(), Patients.class);
            patientsDao.add(patients);
            response.status(201);
            response.type("application/json");
            return gson.toJson(patients);
        });

        //read patients
        get("/patient", "application/json", (req, res) -> {
            if (patientsDao.getAll().size() > 0) {
                return gson.toJson(patientsDao.getAll());
            } else {
                return "{\"message\":\"No patients are currently listed in the database.\"}";
            }
        });


        // create new hospital
        post("/hospitals/new", "application/json", (request, response) -> {
            Hospital hospital = gson.fromJson(request.body(), Hospital.class);
            hospitalDao.add(hospital);
            response.status(201);
            response.type("application/json");
            return gson.toJson(hospital);
        });

        //read patients by id number
        get("/patient/:patients_id", "application/json", (req, res) -> {
            String PatientsId = req.params("patients_id");
            System.out.println(PatientsId);
            Patients patients = patientsDao.findById(PatientsId);
            return gson.toJson(patients);

        });

//        //read hospital
        get("/hospital", "application/json", (req, res) -> {
            if (hospitalDao.getAll().size() > 0) {
                return gson.toJson(hospitalDao.getAll());
            } else {
                return "{\"message\":\"No hospitals are currently listed in the database.\"}";
            }
        });


        //read patients by id number
        get("/patient/:patients_id", "application/json", (req, res) -> {
            String PatientsId = req.params("patients_id");
            System.out.println(PatientsId);
            Patients patients = patientsDao.findById(PatientsId);
            return gson.toJson(patients);

        });
    }
}