import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddCommentGUI extends Stage {

    private Student student;

    private TextField commentDateTF, commentTimeTF;
    private TextArea commentTextTF;

    public AddCommentGUI(Student s) {

        student = s;

        // Create the top part of the GUI
        HBox topBox = new HBox();
        topBox.setSpacing(5);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.CENTER);

        Label dateLabel = new Label("Date");

        commentDateTF = new TextField();
        commentDateTF.setPrefColumnCount(12);

        Label timeLabel = new Label("Time");

        commentTimeTF = new TextField();
        commentDateTF.setPrefColumnCount(12);

        Label separatorLabel = new Label("          ");

        topBox.getChildren().addAll(dateLabel, commentDateTF, separatorLabel, timeLabel, commentTimeTF);


        // Create the middle part of the GUI
        HBox middleBox = new HBox();
        middleBox.setSpacing(5);
        middleBox.setPadding(new Insets(20));
        middleBox.setAlignment(Pos.CENTER);

        commentTextTF = new TextArea();
        commentTextTF.setPrefColumnCount(30);
        commentTextTF.setPrefRowCount(20);

        ScrollPane commentScrollPane = new ScrollPane();
        commentScrollPane.setPrefSize(420, 300);
        commentScrollPane.setContent(commentTextTF);

        middleBox.getChildren().add(commentScrollPane);


        // Create the bottom part of the GUI
        HBox bottonBox = new HBox();
        bottonBox.setSpacing(5);
        bottonBox.setPadding(new Insets(20));
        bottonBox.setAlignment(Pos.CENTER);

        Button commitCommentButton = new Button("Commit");
        commitCommentButton.setPrefSize(80,20);
        commitCommentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                commitCommentButtonActionPerformed();
            }
        });

        Button cancelCommentButton = new Button("Cancel");
        cancelCommentButton.setPrefSize(80,20);
        cancelCommentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancelCommentButtonActionPerformed();
            }
        });

        bottonBox.getChildren().addAll(commitCommentButton, cancelCommentButton);

        // create main GUI as a VBox and add the menubar and other boxes
        VBox mainGUI = new VBox(topBox, middleBox, bottonBox);

        // put the VBox into a scene and the scene into the primary stage
        Scene scene = new Scene(mainGUI, 550, 500);
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent t) {
                closeDialog();
            }
        });
    }

    private void cancelCommentButtonActionPerformed() {
        this.close();
    }

    private void commitCommentButtonActionPerformed() {
        String commentDate = commentDateTF.getText();
        String commentTime = commentTimeTF.getText();
        String commentText = commentTextTF.getText();
        String fullComment = "DATE: " + commentDate + " TIME: " + commentTime + "\n" + commentText;
        student.addMeetingNote(fullComment);
        this.close();
    }

    private void closeDialog() {
        this.close();
    }
}
