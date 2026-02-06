package Clinic;

import java.io.Serializable;
import java.util.Date;

public class Rendezvous implements Serializable {
    private Date dateTime;
    private Doctor doctor;
    private Patient patient;


    public Rendezvous(Date dateTime,Doctor doctor, Patient patient) {
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String toString() {
        return "Hasta:"+getPatient().getName()+" Doctor:"+getDoctor().getName()+" Date:"+getDateTime();
    }

}

