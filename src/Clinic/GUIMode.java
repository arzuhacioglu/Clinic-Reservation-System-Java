package Clinic;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static Clinic.ConsoleMode.listRendezvous;
import static Clinic.ConsoleMode.makeRendezvous;

public class GUIMode extends JFrame {

    private static CRS crs = new CRS(); // Klinik yönetim sisteminin nesnesi
    private static JFrame currentFrame = null; // Track the current open window

    // Temporary storage to simulate duplicate ID checking
    private Set<String> hospitalIds = new HashSet<>();
    private Set<String> sectionIds = new HashSet<>();
    private Set<String> doctorIds = new HashSet<>();
    private Set<String> patientIds = new HashSet<>();

    public GUIMode() {
        crs.loadTablesFromDisk("veriDosyasi.ser");

        // Ana pencere özelliklerini ayarla
        setTitle("Hastane Yönetim Sistemi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirir.

        // Ana paneli oluştur
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));  // 3 satır, 1 sütun, padding ile
        panel.setBackground(Color.DARK_GRAY);

        // Özel buton stili için bir helper fonksiyon
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JButton adminPanelButton = createStyledButton("Yönetici Paneli", buttonFont);
        adminPanelButton.addActionListener(e -> openAdminPanel());

        JButton patientPanelButton = createStyledButton("Hasta Girişi", buttonFont);
        patientPanelButton.addActionListener(e -> openPatientPanel());

        JButton exitButton = createStyledButton("Çıkış", buttonFont);
        exitButton.addActionListener(e -> {
            crs.saveTablesToDisk("veriDosyasi.ser");
            System.exit(0);
        });

        panel.add(adminPanelButton);
        panel.add(patientPanelButton);
        panel.add(exitButton);

        add(panel);

        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20))
        );
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void openAdminPanel() {
        closeCurrentWindow();

        JFrame adminFrame = new JFrame("Yönetici Paneli");
        currentFrame = adminFrame;

        adminFrame.setSize(800, 600);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(5, 1, 10, 10));
        adminPanel.setBackground(Color.DARK_GRAY);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JButton addHospitalButton = createStyledButton("Hastane Ekle", buttonFont);
        addHospitalButton.addActionListener(e -> addHospital());

        JButton addSectionButton = createStyledButton("Bölüm Ekle", buttonFont);
        addSectionButton.addActionListener(e -> addSection());

        JButton addDoctorButton = createStyledButton("Doktor Ekle", buttonFont);
        addDoctorButton.addActionListener(e -> addDoctor());

        JButton addPatientButton = createStyledButton("Hasta Ekle", buttonFont);
        addPatientButton.addActionListener(e -> addPatient());

        JButton backButton = createStyledButton("Geri Dön", buttonFont);
        backButton.addActionListener(e -> adminFrame.dispose());

        adminPanel.add(addHospitalButton);
        adminPanel.add(addSectionButton);
        adminPanel.add(addDoctorButton);
        adminPanel.add(addPatientButton);
        adminPanel.add(backButton);

        adminFrame.add(adminPanel);
        adminFrame.setVisible(true);
    }

    private void openPatientPanel() {
        closeCurrentWindow();  // Ensure the previous window is closed before opening a new one

        JFrame patientFrame = new JFrame("Hasta Paneli");
        currentFrame = patientFrame; // Update the reference to the current frame

        patientFrame.setSize(800, 600);
        patientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        patientFrame.setLocationRelativeTo(null);

        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(new GridLayout(3, 1, 10, 10));
        patientPanel.setBackground(Color.DARK_GRAY);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JButton makeRendezvousButton = createStyledButton("Randevu Al", buttonFont);
        makeRendezvousButton.addActionListener(e -> makeRendezvous());

        JButton listRendezvousButton = createStyledButton("Randevuları Görüntüle", buttonFont);
        listRendezvousButton.addActionListener(e -> listRendezvous());

        JButton backButton = createStyledButton("Geri Dön", buttonFont);
        backButton.addActionListener(e -> patientFrame.dispose());

        patientPanel.add(makeRendezvousButton);
        patientPanel.add(listRendezvousButton);
        patientPanel.add(backButton);

        patientFrame.add(patientPanel);
        patientFrame.setVisible(true);
    }

    private void closeCurrentWindow() {
        if (currentFrame != null) {
            currentFrame.setVisible(false);  // Hide the window
            currentFrame.dispose();  // Dispose of the current window
            currentFrame = null;  // Reset the reference
        }
    }

    // Methods to add hospital, section, doctor, and patient
    private void addHospital() {
        String name=JOptionPane.showInputDialog("Hastanenin ismi:");
        String id = JOptionPane.showInputDialog("Hastane ID'sini girin:");
        if (hospitalIds.contains(id)) {
            JOptionPane.showMessageDialog(this, "Bu Hastane ID'si zaten var!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            hospitalIds.add(id);
            // Add hospital logic here
            JOptionPane.showMessageDialog(this, "Hastane eklendi: " + id);
        }
    }

    private void addSection() {
        String id = JOptionPane.showInputDialog("Bölüm ID'sini girin:");
        if (sectionIds.contains(id)) {
            JOptionPane.showMessageDialog(this, "Bu Bölüm ID'si zaten var!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            sectionIds.add(id);
            // Add section logic here
            JOptionPane.showMessageDialog(this, "Bölüm eklendi: " + id);
        }
    }

    private void addDoctor() {
        String id = JOptionPane.showInputDialog("Doktor ID'sini girin:");
        if (doctorIds.contains(id)) {
            JOptionPane.showMessageDialog(this, "Bu Doktor ID'si zaten var!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            doctorIds.add(id);
            // Add doctor logic here
            JOptionPane.showMessageDialog(this, "Doktor eklendi: " + id);
        }
    }

    private void addPatient() {
        String id = JOptionPane.showInputDialog("Hasta ID'sini girin:");
        if (patientIds.contains(id)) {
            JOptionPane.showMessageDialog(this, "Bu Hasta ID'si zaten var!", "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            patientIds.add(id);
            // Add patient logic here
            JOptionPane.showMessageDialog(this, "Hasta eklendi: " + id);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIMode::new);
    }
}