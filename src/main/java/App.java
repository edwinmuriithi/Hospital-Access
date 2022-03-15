import models.Patients;
import com.google.gson.Gson;
import models.dao.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.post;
public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Sql2oPatientsDao patientsDao;

        String connectionString = "jdbc:postgresql://localhost:5432/hospitalaccess";
        Sql2o sql2o = new Sql2o(connectionString, "ephu17", "ephu17");

        patientsDao = new Sql2oPatientsDao(sql2o);
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
    }
}
