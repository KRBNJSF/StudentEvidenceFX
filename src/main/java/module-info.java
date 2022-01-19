module cz.reindl.studentevidence {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.reindl.studentevidence to javafx.fxml;
    exports cz.reindl.studentevidence;
}