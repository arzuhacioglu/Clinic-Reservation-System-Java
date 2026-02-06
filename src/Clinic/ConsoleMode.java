package Clinic;

import java.util.Date;
import java.util.Scanner;

public class ConsoleMode {

    private static CRS crs = new CRS();
    private static Scanner scanner = new Scanner(System.in);

    public static void start() {

        crs.loadTablesFromDisk("veriDosyasi.ser");

        while (true) {
            System.out.println("\nHastane Yönetim Sistemi - Konsol Modu");
            System.out.println("Yönetici girişi için 1, hasta girişi için 2, çıkış için 3 girmelisiniz.Seçiminiz: ");
            int enter = scanner.nextInt();
            scanner.nextLine();
            if (enter == 1) {
                adminPanel();
            } else if (enter == 2) {
                patientPanel();
            } else if(enter == 3) {
                crs.saveTablesToDisk("veriDosyasi.ser");
                return;
            }
        }
    }

    private static void adminPanel() {
        while (true) {
            System.out.println("\nYönetici Paneli");
            System.out.println("1. Hastane Ekle");
            System.out.println("2. Bölüm Ekle");
            System.out.println("3. Doktor Ekle");
            System.out.println("4. Hasta Ekle");
            System.out.println("5. Geri Dön");
            System.out.print("Bir işlem seçin: ");

            int choice1 = scanner.nextInt();
            scanner.nextLine();

            switch (choice1) {
                case 1:
                    addHospital();
                    break;
                case 2:
                    addSection();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    addPatient();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void addHospital() {
        System.out.print("Hastanenin adı: ");
        String name = scanner.nextLine();
        System.out.print("Hastanenin ID'si: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        crs.addHospital(name, id);
    }

    private static void addSection() {
        System.out.print("Bölümün adı: ");
        String sectionName = scanner.nextLine();
        System.out.print("Bölümün ID'si: ");
        int sectionId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Hastane ID'si: ");
        int hospitalId = scanner.nextInt();
        scanner.nextLine();
        crs.addSection(sectionName, sectionId, hospitalId);
    }

    private static void addDoctor() {
        System.out.print("Doktorun adı: ");
        String doctorName = scanner.nextLine();
        System.out.print("Doktorun diploma ID'si: ");
        int diplomaId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Hastane ID'si: ");
        int hospitalIdForDoctor = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Bölüm ID'si: ");
        int sectionIdForDoctor = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Doktorun bakabileceği maksimum hasta: ");
        int maxPatientPerDay = scanner.nextInt();
        scanner.nextLine();

        crs.addDoctor(doctorName, diplomaId, hospitalIdForDoctor,maxPatientPerDay, sectionIdForDoctor);
    }

    private static void addPatient() {
        System.out.print("Hasta adı: ");
        String patientName = scanner.nextLine();
        System.out.print("Hasta ID'si: ");
        long nationalId = scanner.nextLong();
        scanner.nextLine();
        crs.addPatient(patientName, nationalId);
    }

    private static void patientPanel() {
        while (true) {
            System.out.println("\nHasta Paneli");
            System.out.println("1. Randevu Al");
            System.out.println("2. Randevuları Görüntüle");
            System.out.println("3. Geri Dön");
            System.out.print("Bir işlem seçin: ");

            int choice2 = scanner.nextInt();
            scanner.nextLine();

            switch (choice2) {
                case 1:
                    makeRendezvous();
                    break;
                case 2:
                    listRendezvous();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    static void makeRendezvous() {
        System.out.println("Randevu almak için hasta bilgilerini girmelisiniz.");

        System.out.print("Hasta ID'sini girin: ");
        long patientID = scanner.nextLong();

        System.out.print("Hastane ID'sini girin: ");
        int hospitalID = scanner.nextInt();

        System.out.print("Bölüm ID'sini girin: ");
        int sectionID = scanner.nextInt();

        System.out.print("Doktor Diploma ID'sini girin: ");
        int diplomaID = scanner.nextInt();

        System.out.print("Randevu tarihi (yyyy-mm-dd) formatında girin: ");
        String dateString = scanner.next();
        Date desiredDate = DateHelper.parseDate(dateString);

        boolean success = crs.makeRendezvous(patientID, hospitalID, sectionID, diplomaID, desiredDate);
        if (success) {
            System.out.println("Randevu başarıyla alındı!");
        }else{
            System.out.println("Randevu oluşturulamadı!");
        }
    }

    static void listRendezvous() {
        System.out.println("Randevuları görüntülemek için hasta bilgilerini girmelisiniz.");
        System.out.print("Hasta ID'si: ");
        long patientID = scanner.nextLong();
        crs.listRendezvous(patientID);
    }

}
