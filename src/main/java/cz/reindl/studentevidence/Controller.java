package cz.reindl.studentevidence;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.*;

public class Controller {
    public TextArea textArea;
    public Button addButton;
    public MenuButton menuButton;
    public MenuItem studentsButton;
    public MenuItem gradeButton;
    public TextArea gradeArea;
    public Label gradeLabel;
    public Label averageLabel;
    public double averageGrade;
    List<Double> grades = new ArrayList<>();

    public Map<String, List<Double>> students = new HashMap<String, List<Double>>();
    public MenuButton studentMenu;
    public TreeTableColumn studentTable;
    public Button addGradeButton;
    //public ArrayList<Double> gradeList = new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void addStudentToMap(String name) {
        if (!students.containsKey(name)) {
            students.put(name, new ArrayList<>());
        }
        //gradeList.add(grade);
    }

    public void showStudents(ActionEvent actionEvent) {
        gradeLabel.setVisible(true);
        gradeLabel.setText(String.valueOf(showGrades()));
        //gradeLabel.setText(String.valueOf(students + "\n") + "\n " + showGrades());
    }

    public void addStudent(ActionEvent actionEvent) throws IOException {
        addStudentToMap(textArea.getText());
        gradeLabel.setVisible(false);

        MenuItem item = new MenuItem();

        if (checkStudentList(false)) {
            System.out.println("Already added " + textArea.getText());
        } else {
            System.out.println("New student added: " + textArea.getText());
            studentMenu.getItems().add(item);
            item.setText(textArea.getText());
            //item.setOnAction(this::showAverage);
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    textArea.setText(item.getText());
                    Controller.this.showAverage(actionEvent);
                    showStudents(actionEvent);
                }
            });
        }

        /*Parent root = FXMLLoader.load(getClass().getResource("cz/reindl/studentevidence/addStudent.fxml"));
        Scene scene = new Scene(root, 400, 400);
        Stage stage = (Stage) Stage.getWindows();
        stage.setScene(scene);*/
        /*TextInputDialog td = new TextInputDialog();
        td.show();
        td.setContentText("name");*/
    }

    public boolean checkStudentList(boolean isInList) {
        for (MenuItem mi : studentMenu.getItems()) {
            if (textArea.getText().equals(mi.getText())) {
                isInList = true;
            }
        }
        return isInList;
    }

    public void showAverage(ActionEvent actionEvent) {
        averageLabel.setVisible(true);
        String studentName = textArea.getText();
        if (calculateAverage(studentName) != 0) {
            averageLabel.setText(studentName + ": " + String.valueOf(calculateAverage(studentName)));
        } else {
            System.out.println("no grades");
        }
        //averageLabel.setText(String.valueOf(students.get(studentName).toString()));
        //averageLabel.setText(String.valueOf(students.values()));
    }

    public double calculateAverage(String name) {
        if (!students.isEmpty()) {
            int number = students.get(name).size();
            double avg = 0;
            List<Double> afda = students.get(name);
            for (int i = 0; i < number; i++) {
                avg += afda.get(i);
            }
            return avg / number;
        }
        return 0;
    }

    public List<Double> showGrades() {
        List<Double> afda = students.get(textArea.getText());
        return afda;
    }

    public void deleteGrade() {
        students.remove(textArea.getText(), gradeArea.getText());
        students.remove(students.get(textArea.getText()));


        if (students.containsKey(textArea.getText())) {
            if (!students.get("Petr").isEmpty()) {
                students.get(textArea.getText()).remove(0);
                System.out.println("removed oldest");
            }
        } else {
            System.out.println("no grades left");
        }

        gradeLabel.setText(String.valueOf(showGrades()));

        //gradeLabel.setText(String.valueOf(students) + "::" + students.size());
    }

    public void onOpenDialog(ActionEvent actionEvent) {

    }

    public void addStudentMenu(ActionEvent actionEvent) {

    }

    public void addNewGrade(ActionEvent actionEvent) {
        if (students.containsKey(textArea.getText())) {
            if (Integer.parseInt(gradeArea.getText()) <= 5 && Integer.parseInt(gradeArea.getText()) >= 1) {
                students.get(textArea.getText()).add(Double.valueOf(gradeArea.getText()));
                gradeLabel.setText(String.valueOf(showGrades()));
            } else {
                System.out.println("Incompatible number");
            }
        }
    }
}