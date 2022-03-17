import models.Hospital;
import models.Patients;
import com.google.gson.Gson;
import models.dao.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;
import java.util.List;
public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Sql2oPatientsDao patientsDao;

        String connectionString = "jdbc:postgresql://localhost:5432/hospitalaccess";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "0987654321");

        patientsDao = new Sql2oPatientsDao(sql2o);
        Sql2oHospitalDao hospitalDao = new Sql2oHospitalDao();
        Connection conn;
        conn = sql2o.open();

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
            if(patientsDao.getAll().size() > 0) {
                return gson.toJson(patientsDao.getAll());
            }else {
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
        }}