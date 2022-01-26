package cz.reindl.studentevidence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class HelloController {
    public TextArea textArea;
    public Button addButton;
    public MenuButton menuButton;
    public MenuItem studentsButton;
    public MenuItem gradeButton;
    public TextArea gradeArea;
    public Label gradeLabel;
    public Label averageLabel;
    public double averageGrade;

    public Map<String, List<Double>> students = new HashMap<String, List<Double>>();
    public MenuButton studentMenu;
    //public ArrayList<Double> gradeList = new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void addStudent(String name, double grade) {
        if (students.containsKey(name)) {
            students.get(name).add(grade);
        } else {
            List<Double> grades = new ArrayList<>();
            students.put(name, grades);
            students.get(name).add(grade);
        }
        //gradeList.add(grade);
    }

    public void showStudents(ActionEvent actionEvent) {
        gradeLabel.setVisible(true);
        gradeLabel.setText(String.valueOf(students + "\n") + "\n " + showGrades());
    }

    public void addStudent(ActionEvent actionEvent) throws IOException {
        addStudent(textArea.getText(), Double.parseDouble(gradeArea.getText()));
        gradeLabel.setVisible(false);

        MenuItem item = new MenuItem();
        if (studentMenu.getItems().contains(textArea.getText())) {
            System.out.println("Already added " + textArea.getText());
        } else {
            System.out.println("New person added: " + textArea.getText());
            studentMenu.getItems().add(item);
            item.setText(textArea.getText());
        }

        /*Parent root = FXMLLoader.load(getClass().getResource("cz/reindl/studentevidence/addStudent.fxml"));
        Scene scene = new Scene(root, 400, 400);
        Stage stage = (Stage) Stage.getWindows();
        stage.setScene(scene);*/
        /*TextInputDialog td = new TextInputDialog();
        td.show();
        td.setContentText("name");*/
    }

    public void showAverage(ActionEvent actionEvent) {
        averageLabel.setVisible(true);
        String studentName = textArea.getText();
        averageLabel.setText(studentName + ": " + String.valueOf(calculateAverage(studentName)));
        //averageLabel.setText(String.valueOf(students.get(studentName).toString()));
        //averageLabel.setText(String.valueOf(students.values()));
    }

    public double calculateAverage(String name) {
        int number = students.get(name).size();
        double avg = 0;
        List<Double> afda = students.get(name);
        for (int i = 0; i < number; i++) {
            avg += afda.get(i);
        }
        return avg / number;
    }

    public List<Double> showGrades() {
        List<Double> afda = students.get(textArea.getText());
        return afda;
    }

    public void deleteGrade() {
        students.remove(textArea.getText(), gradeArea.getText());
        students.remove(students.get(textArea.getText()));


        if (students.containsKey(textArea.getText())) {
            students.get(textArea.getText()).remove(1);
            System.out.println("removed");
        } else {
            System.out.println("no");
        }

        gradeLabel.setText(String.valueOf(students));

        //gradeLabel.setText(String.valueOf(students) + "::" + students.size());
    }

    public void onOpenDialog(ActionEvent actionEvent) {

    }

    public void addStudentMenu(ActionEvent actionEvent) {

    }
}