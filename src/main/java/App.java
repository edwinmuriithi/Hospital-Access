import com.google.gson.Gson;
import models.Treatment;
import models.dao.Sql2oTreatmentDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;
import java.util.List;

public class App {
    public static void main (String[] args){
        Gson gson= new Gson();
        Sql2oTreatmentDao treatmentDao;


        //Connection to local db
        String connectionString = "jdbc:postgresql://localhost:5432/hospitalaccess";
        Sql2o sql2o = new Sql2o(connectionString, "muriithi", "123456");

        treatmentDao= new Sql2oTreatmentDao(sql2o);
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

        //read treatment
        get("/treatment", "application/json", (req, res) -> {
            if(treatmentDao.getAll().size() > 0) {
                return gson.toJson(treatmentDao.getAll());
            }else {
                return "{\"message\":\"No treatment has been administered in the database.\"}";
            }
        });

    }
}
