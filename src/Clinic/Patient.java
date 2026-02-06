package Clinic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Person implements Serializable {
    private List<Rendezvous> rendezvous;

    public Patient(String name,long national_id){
        super(name,national_id);
        rendezvous = new ArrayList<Rendezvous>();
    }

    public List<Rendezvous> getRendezvousList() {
        return rendezvous;
    }

    public void setRendezvous(List<Rendezvous> rendezvous) {
        this.rendezvous = rendezvous;
    }

    public void addRendezvous(Date d, Patient p, Doctor desiredDoctor) {
        if (p == null || d == null || desiredDoctor == null) {
            System.out.println("Patient, doctor, or date cannot be null.");
            return;
        } else {
            Rendezvous r = new Rendezvous(d, desiredDoctor, p);
            rendezvous.add(r);
        }
    }

    public void listRendezvous(){
        System.out.println("Rendezvous list for " + getName() + " :");
        if(rendezvous.isEmpty()){
            System.out.println("No rendezvous found");
        }
        for(Rendezvous rendezvous : rendezvous){
            if (rendezvous != null) {
                System.out.println(rendezvous.toString());//bunu başka yerin getinden çek sonra
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
