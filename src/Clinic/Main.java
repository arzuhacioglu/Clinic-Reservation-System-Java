package Clinic;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hastane Yönetim Sistemi");
        System.out.println("1. Konsol Modu");
        System.out.println("2. GUI Modu");
        System.out.print("Lütfen seçiminizi girin: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            ConsoleMode.start();
        } else if (choice == 2) {
            SwingUtilities.invokeLater(() -> new GUIMode());
        } else {
            System.out.println("Geçersiz seçim!");
        }
    }
}


