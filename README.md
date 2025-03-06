# Exam Timetable Management System

## Overview
The **Exam Timetable Management System** is a JavaFX-based application designed to help administrators efficiently schedule and manage exam timetables. It provides a user-friendly interface for adding, editing, and viewing exam schedules while ensuring that no two exams are scheduled to clash.

## Features
- **User-Friendly Interface** – A simple and intuitive UI built using JavaFX.
- **Exam Scheduling** – Allows admins to add, edit, and delete exam schedules.
- **Clash Prevention** – Ensures that no two exams are scheduled at the same time for the same batch of students.
- **Timetable Viewing** – Displays scheduled exams in an organized manner.
- **Search and Filter** – Enables quick lookup of exams by course, date, or time.
- **Data Persistence** – Stores exam data securely using a database or local file storage.

## Technologies Used
- **JavaFX** – UI development
- **SQLite/MySQL** – Database for storing exam schedules
  
## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/pathak-amanraj/exam-timetable-scheduling-module.git
   ```
2. Open the project in an IDE (such as IntelliJ IDEA or Eclipse).
3. Ensure JavaFX is properly configured in your IDE.
4. Set up the database (if using MySQL, configure connection settings in the application).
5. Run the main application file to start the system.

## Usage
1. Launch the application.
2. Navigate to the **Add Exam** section to schedule new exams.
3. View the timetable to check scheduled exams.
4. Edit or delete exams as needed.
5. Ensure that there are no scheduling conflicts with the built-in clash detection system.

