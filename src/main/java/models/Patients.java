package models;

import java.sql.Timestamp;

public class Patients {
    private int id;
    private String name;
    private  String national_id;
    private String date_of_birth;

    public Patients(String name, String national_id, String date_of_birth) {
        this.id = id;
        this.name = name;
        this.national_id = national_id;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNational_id() {
        return national_id;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }
}
