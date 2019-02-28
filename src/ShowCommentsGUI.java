import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class ShowCommentsGUI extends Stage {

    public ShowCommentsGUI(ArrayList<String> comments) {
        // Build the GUI
        VBox fullGUI = new VBox();
      //  fullGUI.setSpacing(5);
      //  fullGUI.setPadding(new Insets(20));
        fullGUI.setAlignment(Pos.CENTER);

        HBox commentBox = new HBox();
        commentBox.setSpacing(5);
        commentBox.setPadding(new Insets(20));
        commentBox.setAlignment(Pos.CENTER);

        TextArea commentTextTF = new TextArea();
        commentTextTF.setPrefColumnCount(30);
        commentTextTF.setPrefRowCount(20);

        ScrollPane commentScrollPane = new ScrollPane();
        commentScrollPane.setPrefSize(420, 300);
        commentScrollPane.setContent(commentTextTF);

        commentBox.getChildren().add(commentScrollPane);

        Button doneButton = new Button("Done");
        doneButton.setPrefSize(80,20);
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                doneButtonActionPerformed();
            }
        });

        fullGUI.getChildren().addAll(commentBox, doneButton);

        // put the VBox into a scene and the scene into the primary stage
        Scene scene = new Scene(fullGUI, 550, 500);
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent t) {
                closeDialog();
            }
        });


        // show the comments
        for (String current : comments) {
            commentTextTF.appendText(current + "\n\n\n");
        }
    }

    private void doneButtonActionPerformed() {
        this.close();
    }

    private void closeDialog() {
        this.close();
    }
}
