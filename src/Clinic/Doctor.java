package Clinic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Doctor extends Person implements Serializable {
    private final int diploma_id;
    private  Schedule schedule;
    private List<Rendezvous> rendezvous;
    private int sectionID;

    public Doctor(String name,long national_id,int diploma_id,int maxPatientPerDay,int sectionID ) {
        super(name,national_id);
        this.diploma_id=diploma_id;
        this.sectionID=sectionID;
        this.schedule= new Schedule(maxPatientPerDay);
        rendezvous = new ArrayList<Rendezvous>();
    }

    public int getDiploma_id() {
        return diploma_id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public int getSection() {
        return sectionID;
    }

    public List<Rendezvous> getRendezvous() {
        return rendezvous;
    }

    public void addRendezvous(Date d, Patient p, Doctor desiredDoctor) {
        if (p == null || d == null) {
            System.out.println("Patient or doctor cannot be null.");
            return;
        }else{
            Rendezvous r=new Rendezvous(d,desiredDoctor,p);
            rendezvous.add(r);
        }
    }

    public String toString() {
        return super.toString()+"Doktorun diploma numarası:"+getDiploma_id();
    }

}
