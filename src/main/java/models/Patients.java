package models;

import java.sql.Timestamp;

public class Patients {
    private int id;
    private String name;
    private  String national_id;
    private Timestamp date_of_birth;

    public Patients(String name, String national_id, Timestamp date_of_birth) {
        this.id = id;
        this.name = name;
        this.national_id = national_id;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {git add
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNational_id() {
        return national_id;
    }

    public Timestamp getDate_of_birth() {
        return date_of_birth;
    }
}
