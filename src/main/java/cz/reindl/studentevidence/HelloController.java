package cz.reindl.studentevidence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        gradeLabel.setText(String.valueOf(students) + ": " + showGrades());
    }

    public void addStudent(ActionEvent actionEvent) {
        addStudent(textArea.getText(), Double.parseDouble(gradeArea.getText()));
        gradeLabel.setVisible(false);
        TextInputDialog td = new TextInputDialog();
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
        double aveg = avg / number;
        return aveg;
    }

    public List<Double> showGrades() {
        List<Double> afda = students.get(textArea.getText());
        return afda;
    }

    public void deleteGrade() {
        List<Double> stuzu = students.get(textArea.getText());
        stuzu.remove(students.get(textArea.getText()));
    }

}