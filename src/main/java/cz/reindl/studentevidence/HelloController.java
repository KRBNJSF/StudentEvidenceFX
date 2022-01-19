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
    @FXML
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
            students.put(name, Arrays.asList(grade));
        }
        //gradeList.add(grade);
    }

    public void showStudents(ActionEvent actionEvent) {
        gradeLabel.setVisible(true);
        gradeLabel.setText(String.valueOf(students));
    }

    public void addStudent(ActionEvent actionEvent) {
        addStudent(textArea.getText(), Double.parseDouble(gradeArea.getText()));
        gradeLabel.setVisible(false);
    }

    public void showAverage(ActionEvent actionEvent) {
        averageLabel.setVisible(true);
        String studentName = textArea.getText();
        averageLabel.setText(String.valueOf(students.get(studentName).toString()));
        //averageLabel.setText(String.valueOf(students.values()));
    }
}