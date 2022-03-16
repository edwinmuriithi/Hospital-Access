package models;

public class Treatment {

    private int id;
    private String date_of_admission;
    private String disease_name;
    private String treatment_administered;

    public Treatment(int id, String date_of_admission, String disease_name, String treatment_administered) {
        this.id = id;
        this.date_of_admission = date_of_admission;
        this.disease_name = disease_name;
        this.treatment_administered = treatment_administered;

    }

    public int getId() {
        return id;
    }

    public String getDate_of_admission() {
        return date_of_admission;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public String getTreatment_administered() {
        return treatment_administered;
    }
}
