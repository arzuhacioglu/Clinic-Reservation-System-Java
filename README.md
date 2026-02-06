 # 🏥 Clinic Reservation System (Java)

> **Full-Stack Desktop Application:** A robust Hospital Management System offering both a **Graphical User Interface (Swing)** and a **Console Interface**, built with Object-Oriented Design principles and Data Persistence.

## Project Overview
This project simulates a comprehensive **Clinic Registration System (CRS)**. It acts as a bridge between hospital administrators and patients, allowing for the management of hospital infrastructure (hospitals, sections, doctors) and the booking of appointments.

Unlike simple runtime applications, this system features **Data Persistence**. It serializes objects to the disk, ensuring that registered patients, doctors, and appointments are saved and reloaded upon the next startup.

## Key Features

### 1. Dual Interface Architecture 
The application provides two distinct modes of operation, selectable at launch:
* **GUI Mode:** A visual, user-friendly interface built with **Java Swing**. It features styled buttons, input dialogs, and a windowed environment for easy navigation.
* **Console Mode:** A text-based command-line interface for fast, efficient operations using standard input/output.

### 2. Advanced Data Persistence 
* The system implements the `Serializable` interface to manage data storage.
* All system data (Hospitals, Doctors, Patients, Appointments) is automatically saved to `veriDosyasi.ser` upon exit and reloaded on startup using `ObjectOutputStream` and `ObjectInputStream`.

### 3. Role-Based Functionality 
* **Admin Panel:**
    * Add Hospitals, Sections, and Doctors.
    * Register new Patients with unique National IDs.
    * Uses custom `IDException` to prevent duplicate records.
* **Patient Panel:**
    * Book appointments with specific doctors.
    * View personal appointment history.
    * Validates dates and doctor availability constraints.

### 4. Object-Oriented Design (OOP) 
* **Inheritance:** `Doctor` extends the `Person` class, promoting code reusability.
* **Encapsulation:** Data fields are protected, and access is managed through controllers (`CRS` class).
* **Exception Handling:** Custom-
