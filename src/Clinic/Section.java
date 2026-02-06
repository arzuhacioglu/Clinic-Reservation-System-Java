package Clinic;

import java.io.Serializable;
import java.util.LinkedList;

public class Section implements Serializable {
    private final int id;
    private final String name;
    private LinkedList<Doctor> doctors;

    public Section(int id, String name) {
        this.id = id;
        this.name = name;
        this.doctors = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Doctor getDoctor(int diploma_id) throws IDException {
        try {
            for (Doctor doctor : doctors) {
                if (doctor.getDiploma_id() == diploma_id) {
                    return doctor;
                }
            }
            throw new IDException("Diploma ID ile eşleşen bir doktor bulunamadı: " + diploma_id);
        } catch (IDException e) {
            System.out.println("Hata: " + e.getMessage());
            throw e;
        }
    }

    public LinkedList<Doctor> getDoctors() {
        return doctors;
    }



    public void addDoctor(Doctor doctor) {
        if(doctor!=null) {
            if(!doctors.contains(doctor)) {
                doctors.add(doctor);
            }else{
                System.out.println("Doctor already exists");
            }

        }else{
            System.out.println("Doctor is null");
        }
    }

    public void listDoctors() {
        if(!doctors.isEmpty()){
            System.out.println("Doctors listed:\n");
            for(Doctor doctor:doctors){
                System.out.println(doctor);
            }
        }
    }

    public String toString() {
        return getName();
    }
}

