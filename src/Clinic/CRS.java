package Clinic;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class CRS implements Serializable {
    private HashMap<Long, Patient> patients;
    private LinkedList<Rendezvous> rendezvous;
    private HashMap<Integer, Hospital> hospitals;
    private HashMap<Integer, Doctor> doctors;
    private String fullPath;

    public CRS() {
        this.patients = new HashMap<>();
        this.rendezvous = new LinkedList<>();
        this.hospitals = new HashMap<>();
        this.doctors = new HashMap<>();
    }

    public HashMap<Long, Patient> getPatients() {
        return patients;
    }

    public LinkedList<Rendezvous> getRendezvous() {
        return rendezvous;
    }

    public HashMap<Integer, Hospital> getHospitals() {
        return hospitals;
    }

    public HashMap<Integer, Doctor> getDoctors() {
        return doctors;
    }

    // Yönetici işlemleri
    public void addHospital(String name, int id) {
        try{
            if (hospitals.containsKey(id)) {
                throw new IDException("Bu ID'ye sahip bir hastane mevcut.");
            } else {
                Hospital hospital = new Hospital(id, name);
                hospitals.put(hospital.getId(), hospital);
                System.out.println("Hastane başarıyla eklendi!");
            }
        } catch (IDException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    public void addSection(String sectionName, int sectionId, int hospitalId) {
        Hospital hospital = hospitals.get(hospitalId);
        if (hospital != null) {
            try {
                if (hospital.getSections(sectionId) != null) {
                    throw new IDException("Bu ID'ye sahip bir bölüm mevcut.");
                } else {
                    Section section = new Section(sectionId, sectionName);
                    hospital.addSection(section);
                    System.out.println("Bölüm başarıyla eklendi!");
                }
            } catch (IDException e) {
                System.out.println("Hata: " + e.getMessage());
            }
        } else {
            System.out.println("Hastane bulunamadı!");
        }
    }

    public void addDoctor(String doctorName, int diplomaId, int hospitalId,int maxPatientPerDay, int sectionId) {
        Hospital hospitalForDoctor = hospitals.get(hospitalId);
        if (hospitalForDoctor != null) {
            Section sectionForDoctor = hospitalForDoctor.getSections(sectionId);
            if (sectionForDoctor != null) {
                try {
                    if (doctors.containsKey(diplomaId)) {
                        throw new IDException("Bu diploma ID'sine sahip bir doktor mevcut.");
                    } else {
                        Doctor doctor = new Doctor(doctorName, 0, diplomaId,maxPatientPerDay ,sectionForDoctor.getId()); // 0 yerine bir `national_id` eklenebilir
                        sectionForDoctor.addDoctor(doctor);
                        doctors.put(diplomaId, doctor);
                        System.out.println("Doktor başarıyla eklendi!");
                    }
                } catch (IDException e) {
                    System.out.println("Hata: " + e.getMessage());
                }
            } else {
                System.out.println("Bölüm bulunamadı.");
            }
        } else {
            System.out.println("Hastane bulunamadı.");
        }
    }

    public void addPatient(String patientName, long nationalId) {
        try {
            if (patients.containsKey(nationalId)) {
                throw new IDException("Bu ID'ye sahip bir hasta zaten var.");
            } else {
                Patient patient = new Patient(patientName, nationalId);
                patients.put(patient.getNationalId(), patient);
                System.out.println("Hasta başarıyla eklendi.");
            }
        } catch (IDException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    // Hasta işlemleri
    public boolean makeRendezvous(long patientID, int hospitalID, int sectionID, int diplomaID, Date desiredDate) {
        try {
            Patient patient = patients.get(patientID);
            if (patient == null) {
                throw new IDException("ID ile eşleşen bir hasta bulunamadı: " + patientID);
            }

            Hospital hospital = hospitals.get(hospitalID);
            if (hospital == null) {
                throw new IDException("ID ile eşleşen bir hastane bulunamadı: " + hospitalID);
            }

            Section section = hospital.getSections(sectionID); // Hastaneye ait bölüm
            if (section == null) {
                throw new IDException("ID ile eşleşen bir bölüm bulunamadı: " + sectionID);
            }

            Doctor doctor = section.getDoctor(diplomaID); // Bölümdeki doktora erişim
            if (doctor == null) {
                throw new IDException("ID ile eşleşen bir doktor bulunamadı: " + diplomaID);
            }

            // Randevu oluşturma
            boolean result = doctor.getSchedule().addRendezvous(patient, desiredDate, doctor);
            if (result) {
                Rendezvous rendezvous1 = new Rendezvous(desiredDate, doctor, patient); // Yeni randevu oluşturuluyor
                rendezvous.add(rendezvous1); // Randevular listesine ekleniyor
                return true;
            } else {
                return false;
            }
        } catch (IDException e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }


    // Randevuları listeleme
    public void listRendezvous(long patientID) {
        Patient patient = patients.get(patientID);
        if (patient != null) {
            patient.listRendezvous();
        } else {
            System.out.println("Hasta bulunamadı.");
        }
    }

    public void clearData() {
        patients.clear();
        rendezvous.clear();
        hospitals.clear();
        doctors.clear();
        System.out.println("Veriler sıfırlandı.");
    }

    public void saveTablesToDisk(String fullPath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            oos.writeObject(patients);
            oos.writeObject(rendezvous);
            oos.writeObject(hospitals);
            oos.writeObject(doctors);
            System.out.println("Veriler başarıyla kaydedildi.");
        } catch (IOException e) {
            System.out.println("Veri kaydetme hatası: " + e.getMessage());
        }
    }

    public void loadTablesFromDisk(String fullPath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            patients = (HashMap<Long, Patient>) ois.readObject();
            rendezvous = (LinkedList<Rendezvous>) ois.readObject();
            hospitals = (HashMap<Integer, Hospital>) ois.readObject();
            doctors = (HashMap<Integer, Doctor>) ois.readObject();
            System.out.println("Veriler başarıyla yüklendi.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Veri yükleme hatası: " + e.getMessage());
        }
    }

}
