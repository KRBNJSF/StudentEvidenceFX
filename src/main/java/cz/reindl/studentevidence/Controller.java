package cz.reindl.studentevidence;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    public TreeTableView table;
    public CheckBox checkBox;
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

    public void showAllGrades(ActionEvent actionEvent) {
        gradeLabel.setVisible(true);
        gradeLabel.setText(String.valueOf(showGrades()));
        //gradeLabel.setText(String.valueOf(students + "\n") + "\n " + showGrades());
    }

    public void addStudent(ActionEvent actionEvent) throws IOException {
        table = new TreeTableView();
        studentTable = new TreeTableColumn();
        TreeItem treeItem = new TreeItem(new Model("Ahoj"));
        //treeItem.getChildren().add();
        //studentTable.setCellValueFactory(new PropertyValueFactory<>("ano"));
        //studentTable.setCellValueFactory(new PropertyValueFactory<>("fasdljfa"));
        table.setTreeColumn(studentTable);
        table.setRoot(treeItem);
        //table.getColumns().add(studentTable);
        //studentTable.getColumns().set(1, "Petr");
        //studentTable.getColumns().add(1, "Petr");
        if (textArea.getText().isBlank()) {
            System.out.println("Insert name");
        } else {
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
                        showAllGrades(actionEvent);
                    }
                });
            }
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
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        averageLabel.setVisible(true);
        String studentName = textArea.getText();
        if (calculateAverage(studentName) != 0) {
            averageLabel.setText(studentName + ": " + String.valueOf(df.format(calculateAverage(studentName))));
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
        List<Double> grades = students.get(textArea.getText());
        return grades;
    }

    public void deleteGrade() {
        students.remove(textArea.getText(), gradeArea.getText());
        students.remove(students.get(textArea.getText()));


        if (students.containsKey(textArea.getText())) {
            if (!students.get(textArea.getText()).isEmpty()) {
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
        if (!gradeArea.getText().isBlank()) {
            if (students.containsKey(textArea.getText())) {
                try {
                    Double.parseDouble(gradeArea.getText());
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input format");
                    gradeArea.setText("1");
                }
                if (Double.parseDouble(gradeArea.getText()) <= 5 && Double.parseDouble(gradeArea.getText()) >= 1) {
                    System.out.println("Grade added: " + gradeArea.getText());
                    students.get(textArea.getText()).add(Double.valueOf(gradeArea.getText()));
                    gradeLabel.setText(String.valueOf(showGrades()));
                } else {
                    System.out.println("Incompatible number");
                }
            }
        } else {
            System.out.println("Insert grade");
        }
    }

    public void showGradeLabel(ActionEvent actionEvent) {
        gradeArea.setVisible(true);
    }

    public void showTextAreaLabel(ActionEvent actionEvent) {
        textArea.setVisible(true);
    }

    public void checkedBox(ActionEvent actionEvent) throws IOException {
        if (checkBox.isSelected()) {
            checkBox.setText("Insert areas are visible");
            System.out.println("enabled");
            textArea.setVisible(true);
            gradeArea.setVisible(true);
        } else {
            checkBox.setText("Insert areas are invisible");
            System.out.println("disabled");
            textArea.setVisible(false);
            gradeArea.setVisible(false);
        }
        File file = new File("src/main/java/cz/reindl/studentevidence/stats.txt");
        if (file.createNewFile()) {
            System.out.println(file.getName());
        } else {
            System.out.println("File already exists");
            FileWriter writer = new FileWriter("src/main/java/cz/reindl/studentevidence/stats.txt");
            for (int i = 0; i < students.size(); i++) {
                writer.write(String.valueOf(students.get(i)) + "\n");
            }
            writer.close();
            System.out.println("File rewritten");
        }
    }
}