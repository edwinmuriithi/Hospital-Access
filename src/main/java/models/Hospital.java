package models;
import java.util.Objects;

public class Hospital {
    private String id;
    private String name ;
    private String location;
    private String hospitallevel;

    public Hospital(String name, String location, String hospitallevel){
    this.name= name;
    this.location= location;
    this.hospitallevel = hospitallevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHospitallevel() {
        return hospitallevel;
    }

    public void setHospitallevel(String hospitallevel) {
        this.hospitallevel = hospitallevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(getId(), hospital.getId()) && Objects.equals(getName(), hospital.getName()) && Objects.equals(getLocation(), hospital.getLocation()) && Objects.equals(getHospitallevel(), hospital.getHospitallevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLocation(), getHospitallevel());
    }
}
