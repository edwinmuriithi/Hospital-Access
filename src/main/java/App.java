import com.google.gson.Gson;
import models.dao.Sql2oTreatmentDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

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
    }
}
