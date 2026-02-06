package Clinic;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Schedule implements Serializable {
    private LinkedList<Rendezvous> sessions;
    private final int maxPatientPerDay;

    public Schedule(int maxPatientPerDay) {
        this.maxPatientPerDay = maxPatientPerDay;
        sessions = new LinkedList<>();
    }

    public LinkedList<Rendezvous> getSessions() {
        if (sessions == null) {
            sessions = new LinkedList<>();
        }
        return sessions;
    }

    public int getMaxPatientPerDay() {
        return maxPatientPerDay;
    }

    public boolean addRendezvous(Patient p, Date d, Doctor desiredDoctor) {
        int rendezvousCount = 0;

        // İstenen randevu tarihini Calendar'a çeviriyoruz
        Calendar wanted = Calendar.getInstance();
        wanted.setTime(d);

        // O gün alınan randevuları sayıyoruz
        for (Rendezvous rendezvous : sessions) {
            Calendar currentDate = Calendar.getInstance();
            currentDate.setTime(rendezvous.getDateTime()); // Randevunun tarihini alıyoruz

            // Randevunun aynı yıl, aynı ay ve aynı günde olup olmadığını kontrol ediyoruz
            if (wanted.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                    wanted.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) &&
                    wanted.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH)) {

                // Aynı günde bu hasta ve bu doktor için zaten bir randevu var mı diye kontrol et
                if (rendezvous.getPatient().equals(p) && rendezvous.getDoctor().equals(desiredDoctor)) {
                    System.out.println("Bu hasta, aynı gün için aynı doktora randevu aldığı için yeni randevu alamaz.");
                    return false;
                }

                rendezvousCount++; // Eğer aynı günse, sayacı arttırıyoruz
            }
        }

        // Eğer günlük hasta sayısı limitin altında ise, yeni randevu ekleyebiliriz
        if (rendezvousCount < maxPatientPerDay) {
            // Yeni randevu ekliyoruz
            Rendezvous r = new Rendezvous(d, desiredDoctor, p);
            sessions.add(r);

            // Hastaya ve doktora bu randevuyu ekliyoruz
            p.addRendezvous(d, p, desiredDoctor);
            desiredDoctor.addRendezvous(d, p, desiredDoctor);

            return true; // Randevu başarılı şekilde eklendi
        }

        // Eğer günlük hasta limitine ulaşıldıysa, randevu eklenemez
        return false;
    }

}

