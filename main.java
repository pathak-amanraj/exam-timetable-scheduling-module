package com.example.exam_timetable_management;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Exam_TimeTable_Module extends Application {

//
    private Scene homeScene, timetableScene, loginScene, adminScene, createTimeTableScene, updateTimeTableScene, deleteTimeTableScene;

    public static class User {
        private SimpleStringProperty course_id;
        private SimpleStringProperty date;
        private SimpleStringProperty start_time;
        private SimpleStringProperty end_time;
        private SimpleStringProperty classroom_id;


        public User(String course_id, String date, String start_time, String end_time, String classroom_id) {
            this.course_id = new SimpleStringProperty(course_id);
            this.date = new SimpleStringProperty(date);
            this.start_time = new SimpleStringProperty(start_time);
            this.end_time = new SimpleStringProperty(end_time);
            this.classroom_id = new SimpleStringProperty(classroom_id);

        }

        public String getCourse_id() {
            return course_id.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getStart_time() {
            return start_time.get();
        }
        public String getEnd_time() {
            return end_time.get();
        }
        public String getClassroom_id() {
            return classroom_id.get();
        }
        public void setCourse_id(String course_id) {
            this.course_id = new SimpleStringProperty(course_id);
        }
        public void setDate(String date) {
            this.date = new SimpleStringProperty(date);
        }
        public void setStart_time(String start_time) {
            this.start_time = new SimpleStringProperty(start_time);
        }
        public void setEnd_time(String end_time) {
            this.end_time = new SimpleStringProperty(end_time);
        }
        public void setClassroom_id(String classroom_id) {
            this.classroom_id = new SimpleStringProperty(classroom_id);
        }
    }
    private TableView<User> tableView;
    private ObservableList<User> fetchUsersFromDatabase() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String url = "jdbc:mysql://localhost:3306/exam_module";
      //Uncomment the below lines and add your username and password before running the code.
      //  String user = username;
      //  String password = password;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select distinct course_id, date, start_time, end_time, classroom_id from exams ORDER by course_id;")) {

            while (rs.next()) {
                String course_id = rs.getString("course_id");
                String date = rs.getString("date");
                String start_time = rs.getString("start_time");
                String end_time = rs.getString("end_time");
                String classroom_id = rs.getString("classroom_id");
                users.add(new User(course_id, date, start_time, end_time, classroom_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    private TextField courseIdField, dateField, startTimeField, endTimeField, classroomIdField, u_courseIdField, u_dateField, u_startTimeField, u_endTimeField, u_classroomIdField, d_courseIdField;
    private CheckBox updateDateCheckBox;
    private CheckBox updateStartTimeCheckBox;
    private CheckBox updateEndTimeCheckBox;
    private CheckBox updateClassroomIdCheckBox;
    private CheckBox deleteDateCheckBox;
    private CheckBox deleteStartTimeCheckBox;
    private CheckBox deleteEndTimeCheckBox;
    private CheckBox deleteClassroomIdCheckBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exam Timetable Management System");
        int l = 600;
        int b = 500;
        Button backbutton2 = new Button("Back");
        Button backbutton3 = new Button("Back");
        Button backbutton4 = new Button("Back");

        // Home Scene
        Button viewTimetableButton = new Button("View Timetable");
        Button loginAdminButton = new Button("Login as admin");
        Button backButton = new Button("Back");
        GridPane homeLayout = new GridPane();
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setHgap(10);
        homeLayout.setVgap(10);
        homeLayout.add(viewTimetableButton, 0, 0);
        homeLayout.add(loginAdminButton, 0, 1);
        homeScene = new Scene(homeLayout, l, b);


        tableView = new TableView<>();

        // Define table columns
        TableColumn<User, String> course_idColumn = new TableColumn<>("course_id");
        course_idColumn.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        TableColumn<User, String> dateColumn = new TableColumn<>("date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<User, String> start_timeColumn = new TableColumn<>("start_time");
        start_timeColumn.setCellValueFactory(new PropertyValueFactory<>("start_time"));

        TableColumn<User, String> end_timeColumn = new TableColumn<>("end_time");
        end_timeColumn.setCellValueFactory(new PropertyValueFactory<>("end_time"));

        TableColumn<User, String> classroom_idColumn = new TableColumn<>("classroom_id");
        classroom_idColumn.setCellValueFactory(new PropertyValueFactory<>("classroom_id"));

        tableView.getColumns().add(course_idColumn);
        tableView.getColumns().add(dateColumn);
        tableView.getColumns().add(start_timeColumn);
        tableView.getColumns().add(end_timeColumn);
        tableView.getColumns().add(classroom_idColumn);

        // Fetch data from the database
        ObservableList<User> users = fetchUsersFromDatabase();
        tableView.setItems(users);

        VBox vbox1 = new VBox(tableView);
        Button refreshButton = new Button("Refresh");
        Button backbutton5 = new Button("Back");

        VBox vbox = new VBox(vbox1, refreshButton, backbutton5);
        Scene viewTimeTablescene = new Scene(vbox, l, b);
        vbox.setSpacing(10);

//        VBox vbox2 = new VBox(tableView);
//        Button backbutton6 = new Button("Back");
//        VBox vbox3 = new VBox(vbox2, backbutton6);
//        Scene midscene = new Scene(vbox3, l, b);

        // Timetable Scene (Blank)
        GridPane timetableLayout = new GridPane();

//        timetableScene = new Scene(ascene, l, b);
//        timetableLayout.add(layout);
        //timetableLayout.add(backbutton2, 0, 0);




        // CreateTimeTable Scene
       // GridPane createTimeTableLayout = new GridPane();
        //createTimeTableScene = new Scene(createTimeTableLayout, l, b);
        Label courseIdLabel = new Label("Course ID:");
        courseIdField = new TextField();

        Label dateLabel = new Label("Date:");
        dateField = new TextField();

        Label startTimeLabel = new Label("Start Time:");
        startTimeField = new TextField();

        Label endTimeLabel = new Label("End Time:");
        endTimeField = new TextField();

        Label classroomIdLabel = new Label("Classroom ID:");
        classroomIdField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> createExamSchedule());

        Button viewScheduleButton = new Button("View Schedule");
        viewScheduleButton.setOnAction(event -> primaryStage.setScene(viewTimeTablescene));
        Button backbutton6 = new Button("Back");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        gridPane.add(courseIdLabel, 0, 0);
        gridPane.add(courseIdField, 1, 0);
        gridPane.add(dateLabel, 0, 1);
        gridPane.add(dateField, 1, 1);
        gridPane.add(startTimeLabel, 0, 2);
        gridPane.add(startTimeField, 1, 2);
        gridPane.add(endTimeLabel, 0, 3);
        gridPane.add(endTimeField, 1, 3);
        gridPane.add(classroomIdLabel, 0, 4);
        gridPane.add(classroomIdField, 1, 4);

        VBox buttonBox = new VBox(10, submitButton, viewScheduleButton, backbutton6);
        buttonBox.setPadding(new Insets(20));
        VBox createTimeTableRoot = new VBox(gridPane, buttonBox);

        Scene createTimeTableScene = new Scene(createTimeTableRoot, l, b);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Exam Schedule");
//        primaryStage.show();


        //createTimeTableLayout.add(backbutton2, 2, 2);


        //updateTimeTable Scene
        Label u_courseIdLabel = new Label("Course ID:");
        u_courseIdField = new TextField();
        Label u_dateLabel = new Label("Date:");
        u_dateField = new TextField();
        Label u_startTimeLabel = new Label("Start Time:");
        u_startTimeField = new TextField();
        Label u_endTimeLabel = new Label("End Time:");
        u_endTimeField = new TextField();
        Label u_classroomIDLabel = new Label("Classroom ID:");
        u_classroomIdField = new TextField();
        updateDateCheckBox = new CheckBox("Update Date");
        updateStartTimeCheckBox = new CheckBox("Update Start Time");
        updateEndTimeCheckBox = new CheckBox("Update End Time");
        updateClassroomIdCheckBox = new CheckBox("Update Classroom ID");

        Button updateButton = new Button("Update");
        updateButton.setOnAction(event -> updateExamSchedule());

        Button backbutton7 = new Button("Back");

        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        gridPane2.setPadding(new Insets(20));

        gridPane2.add(u_courseIdLabel, 0, 0);
        gridPane2.add(u_courseIdField, 1, 0);
        gridPane2.add(u_dateLabel, 0, 1);
        gridPane2.add(u_dateField, 1, 1);
        gridPane2.add(u_startTimeLabel, 0, 2);
        gridPane2.add(u_startTimeField, 1, 2);
        gridPane2.add(u_endTimeLabel, 0, 3);
        gridPane2.add(u_endTimeField, 1, 3);
        gridPane2.add(u_classroomIDLabel, 0, 4);
        gridPane2.add(u_classroomIdField, 1, 4);

        HBox checkboxBox = new HBox(10, updateDateCheckBox, updateStartTimeCheckBox, updateEndTimeCheckBox, updateClassroomIdCheckBox);
        VBox buttonBox2 = new VBox(10, updateButton, backbutton7);

        VBox updatett = new VBox(gridPane2, checkboxBox, buttonBox2);
        updatett.setSpacing(10);

        Scene updateTimeTableScene = new Scene(updatett, l, b);


//        GridPane updateTimeTableLayout = new GridPane();
//        updateTimeTableScene = new Scene(updateTimeTableLayout, l, b);
//        updateTimeTableLayout.add(backbutton3, 2, 2);







        //DeleteTimeTable Scene

        Label d_courseIdLabel = new Label("Course ID:");
        d_courseIdField = new TextField();
        deleteDateCheckBox = new CheckBox("Delete Date");
        deleteStartTimeCheckBox = new CheckBox("Delete Start Time");
        deleteEndTimeCheckBox = new CheckBox("Delete End Time");
        deleteClassroomIdCheckBox = new CheckBox("Delete Classroom ID");
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> deleteTimetableFields());
        Button backbutton8 = new Button("Back");
        GridPane gridPane3 = new GridPane();
        gridPane3.setHgap(10);
        gridPane3.setVgap(10);
        gridPane3.setPadding(new Insets(20));

        gridPane3.add(d_courseIdLabel, 0, 0);
        gridPane3.add(d_courseIdField, 1, 0);

        HBox checkboxBox3 = new HBox(10, deleteDateCheckBox, deleteStartTimeCheckBox, deleteEndTimeCheckBox, deleteClassroomIdCheckBox);
        VBox buttonBox3 = new VBox(10, deleteButton, backbutton8);

        VBox dtt = new VBox(gridPane3, checkboxBox3, buttonBox3);
        dtt.setSpacing(10);

        Scene dscene = new Scene(dtt, l, b);


//        deleteTimeTableScene = new Scene(deleteTimeTableLayout, l, b);
//        deleteTimeTableLayout.add(backbutton4, 2, 2);


        // Login Scene
        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER); // Align the grid content to center
        loginLayout.setHgap(10); // Horizontal gap between columns
        loginLayout.setVgap(10); // Vertical gap between rows
        Label userLabel = new Label("User ID:");
        TextField userField = new TextField();
        userField.setPrefWidth(200);
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        Button backButton1 = new Button("Back");
        loginLayout.add(userLabel, 0, 0);
        loginLayout.add(userField, 0, 1);
        loginLayout.add(passLabel, 1, 0);
        loginLayout.add(passField, 1, 1);
        loginLayout.add(loginButton, 2, 1);
        loginLayout.add(backButton1, 3, 1);
//        vbox.getChildren().addAll(userLabel, userField, passLabel, passField, loginButton, backButton1);
//        vbox.setAlignment(Pos.CENTER);
        loginScene = new Scene(loginLayout, l, b);

        // Admin Scene
        Button createTimetableButton = new Button("Create Timetable");
        Button updateTimetableButton = new Button("Update Timetable");
        Button deleteTimetableButton = new Button("Delete Timetable");
        GridPane adminLayout = new GridPane();
        adminLayout.setAlignment(Pos.CENTER);
        adminLayout.setVgap(10);
        adminLayout.add(createTimetableButton, 0, 0);
        adminLayout.add(updateTimetableButton, 0, 1);
        adminLayout.add(deleteTimetableButton, 0, 2);
        adminLayout.add(backButton, 0, 3);
        adminScene = new Scene(adminLayout, l, b);

        // Button actions
        viewTimetableButton.setOnAction(e -> primaryStage.setScene(viewTimeTablescene));
        loginAdminButton.setOnAction(e -> primaryStage.setScene(loginScene));
        loginButton.setOnAction(e -> {
            if (authenticate(userField.getText(), passField.getText())) {
                primaryStage.setScene(adminScene);
                userField.setText("");
                passField.setText("");

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Wrong credentials! Try again!").showAndWait();
                userField.setText("");
                passField.setText("");
            }
        });
        createTimetableButton.setOnAction(e -> primaryStage.setScene(createTimeTableScene));
        updateTimetableButton.setOnAction(e -> primaryStage.setScene(updateTimeTableScene));
        deleteTimetableButton.setOnAction(e -> primaryStage.setScene(dscene));
        refreshButton.setOnAction(event -> loadTableData());
        backButton.setOnAction(e -> primaryStage.setScene(homeScene));
        backButton1.setOnAction(e -> primaryStage.setScene(homeScene));
        backbutton2.setOnAction(e -> primaryStage.setScene(adminScene));
        backbutton3.setOnAction(e -> primaryStage.setScene(adminScene));
        backbutton4.setOnAction(e -> primaryStage.setScene(adminScene));
        backbutton5.setOnAction(e -> primaryStage.setScene(homeScene));
        backbutton6.setOnAction(e -> primaryStage.setScene(adminScene));
        backbutton7.setOnAction(e -> primaryStage.setScene(adminScene));
        backbutton8.setOnAction(e -> primaryStage.setScene(adminScene));
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    private boolean authenticate(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM admin_details WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void createExamSchedule() {
        String courseId = courseIdField.getText();
        String date = dateField.getText();
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String classroomId = classroomIdField.getText();

        Clash c = new Clash();

        // Create the exam schedule in the database
        if (c.scheduleExam(courseId, date, startTime, endTime, classroomId)){
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "UPDATE exams SET date = ?, start_time = ?, end_time = ?, classroom_id = ? WHERE course_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, date);
                statement.setString(2, startTime);
                statement.setString(3, endTime);
                statement.setString(4, classroomId);
                statement.setString(5, courseId);
                statement.executeUpdate();
                System.out.println("Exam schedule created successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error updating exam schedule: " + e.getMessage());
            }
        }
        else{
            new Alert(Alert.AlertType.INFORMATION, "Unsuccessful! Try again!").showAndWait();
        }
    }


    private void updateExamSchedule() {
        String courseId = u_courseIdField.getText();
        String date = updateDateCheckBox.isSelected() ? u_dateField.getText() : null;
        String startTime = updateStartTimeCheckBox.isSelected() ? u_startTimeField.getText() : null;
        String endTime = updateEndTimeCheckBox.isSelected() ? u_endTimeField.getText() : null;
        String classroomId = updateClassroomIdCheckBox.isSelected() ? u_classroomIdField.getText() : null;


        // Update the exam schedule in the database

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "UPDATE exams SET ";
                boolean first = true;
                if (updateDateCheckBox.isSelected()) {
                    sql += "date = ?";
                    first = false;
                }
                if (updateStartTimeCheckBox.isSelected()) {
                    if (!first) sql += ", ";
                    sql += "start_time = ?";
                    first = false;
                }
                if (updateEndTimeCheckBox.isSelected()) {
                    if (!first) sql += ", ";
                    sql += "end_time = ?";
                    first = false;
                }
                if (updateClassroomIdCheckBox.isSelected()) {
                    if (!first) sql += ", ";
                    sql += "classroom_id = ?";
                }
                sql += " WHERE course_id = ?";

                PreparedStatement statement = conn.prepareStatement(sql);
                int paramIndex = 1;
                if (updateDateCheckBox.isSelected()) {
                    statement.setString(paramIndex++, date);
                }
                if (updateStartTimeCheckBox.isSelected()) {
                    statement.setString(paramIndex++, startTime);
                }
                if (updateEndTimeCheckBox.isSelected()) {
                    statement.setString(paramIndex++, endTime);
                }
                if (updateClassroomIdCheckBox.isSelected()) {
                    statement.setString(paramIndex++, classroomId);
                }
                statement.setString(paramIndex, courseId);
                statement.executeUpdate();
                System.out.println("Exam schedule updated successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error updating exam schedule: " + e.getMessage());
            }
    }

    private void deleteTimetableFields() {
        String courseId = d_courseIdField.getText();

        // Delete the selected fields from the exam schedule in the database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE exams SET ";
            boolean first = true;
            if (deleteDateCheckBox.isSelected()) {
                sql += "date = NULL";
                first = false;
            }
            if (deleteStartTimeCheckBox.isSelected()) {
                if (!first) sql += ", ";
                sql += "start_time = NULL";
                first = false;
            }
            if (deleteEndTimeCheckBox.isSelected()) {
                if (!first) sql += ", ";
                sql += "end_time = NULL";
                first = false;
            }
            if (deleteClassroomIdCheckBox.isSelected()) {
                if (!first) sql += ", ";
                sql += "classroom_id = NULL";
            }
            sql += " WHERE course_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, courseId);
            statement.executeUpdate();
            System.out.println("Exam schedule fields deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting exam schedule fields: " + e.getMessage());
        }
    }
    private void loadTableData() {
        // Fetch data from the database
        ObservableList<User> data = fetchUsersFromDatabase();

        // Clear the existing table
        tableView.getItems().clear();

        // Populate the table with the new data
        tableView.getItems().addAll(data);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
