import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentContactSystemMainGUI extends Application {

    private StudentList studentList = new StudentList();

    private FileChooser chooser = new FileChooser();
    private File saveFile = null;

    private boolean changesMade = false;
    private Stage mainStage;

    private ListView<Student> allStudents;
    private Menu fileMenu, findMenu, optionsMenu;
    private Button addNewButton, deleteButton, commitButton, cancelButton;
    private TextField IDTF, firstnameTF, initsTF, lastnameTF, courseTF,
            homePhoneTF, mobilePhoneTF, homeEmailTF;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Build the menus
        MenuBar menubar = new MenuBar();
        fileMenu = new Menu("File");
        findMenu = new Menu("Find");
        optionsMenu = new Menu("Options");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem saveAsMenuItem = new MenuItem("Save As...");
        MenuItem closeMenuItem = new MenuItem("Close");
        MenuItem findByIDMenuItem = new MenuItem("Find By ID");
        MenuItem findByNameMenuItem = new MenuItem("Find By Name");
        MenuItem viewNotesMenuItem = new MenuItem("View Notes");
        MenuItem addNotesMenuItem = new MenuItem("Add Notes");
        fileMenu.getItems().addAll(openMenuItem, new SeparatorMenuItem(),
                saveMenuItem,saveAsMenuItem, new SeparatorMenuItem(), closeMenuItem);
        findMenu.getItems().addAll(findByNameMenuItem, findByIDMenuItem);
        optionsMenu.getItems().addAll(addNotesMenuItem, viewNotesMenuItem);
        menubar.getMenus().addAll(fileMenu, findMenu, optionsMenu);

        // add action listeners to the menu items
        openMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openMenuItemActionPerformed();
            }
        });

        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveMenuItemActionPerformed();
            }
        });

        saveAsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveAsMenuItemActionPerformed();
            }
        });

        closeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeMenuItemActionPerformed();
            }
        });

        findByIDMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                findByIDMenuItemActionPerformed();
            }
        });

        findByNameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                findByNameMenuItemActionPerformed();
            }
        });

        addNotesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addNotesMenuItemActionPerformed();
            }
        });

        viewNotesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                viewNotesMenuItemActionPerformed();
            }
        });


        // Build the left hand panel containing the ListView
        VBox leftHandPanel = new VBox();
        leftHandPanel.setSpacing(5);
        leftHandPanel.setPadding(new Insets(20));

        allStudents = new ListView<>();
        allStudents.setPrefSize(280,400);

        allStudents.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                allStudentsMouseClicked();
            }
        });

        allStudents.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                allStudentsKeyReleased();
            }
        });

        allStudents.setItems(studentList);
        leftHandPanel.getChildren().add(allStudents);


        // Build the right hand panel containing the labels and text boxes
        VBox rightHandPanel = new VBox();
        rightHandPanel.setSpacing(5);
        rightHandPanel.setPadding(new Insets(20));

        IDTF = new TextField();
        IDTF.setPrefColumnCount(15);
        firstnameTF = new TextField();
        firstnameTF.setPrefColumnCount(15);
        initsTF = new TextField();
        initsTF.setPrefColumnCount(15);
        lastnameTF = new TextField();
        lastnameTF.setPrefColumnCount(15);
        courseTF = new TextField();
        courseTF.setPrefColumnCount(15);
        homePhoneTF = new TextField();
        homePhoneTF.setPrefColumnCount(15);
        mobilePhoneTF = new TextField();
        mobilePhoneTF.setPrefColumnCount(15);
        homeEmailTF = new TextField();
        homeEmailTF.setPrefColumnCount(15);

        rightHandPanel.getChildren().addAll(
                new Label("Student ID"), IDTF,
                new Label("First Name"), firstnameTF,
                new Label("Middle Initials"), initsTF,
                new Label("Last Name"), lastnameTF,
                new Label("Course"), courseTF,
                new Label("Home Phone"), homePhoneTF,
                new Label("Mobile"), mobilePhoneTF,
                new Label("Home Email"), homeEmailTF);


        // create a HBox to contain the left hand and right hand panels
        HBox middleBox = new HBox(leftHandPanel, rightHandPanel);


        // create the bottom panel to contain the buttons
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(15);
        buttonBox.setPadding(new Insets(20));

        addNewButton = new Button("Add New");
        addNewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addNewButtonActionPerformed();
            }
        });

        deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteButtonActionPerformed();
            }
        });

        commitButton = new Button("Commit");
        commitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                commitButtonActionPerformed();
            }
        });

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancelButtonActionPerformed();
            }
        });

        Label spacer = new Label("               ");

        buttonBox.getChildren().addAll(addNewButton, deleteButton, spacer, commitButton, cancelButton);


        // create main GUI as a VBox and add the menubar and other boxes
        VBox mainGUI = new VBox(menubar, middleBox, buttonBox);

        // put the VBox into a scene and the scene into the primary stage
        Scene scene = new Scene(mainGUI, 580, 530);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent t) {
                exitForm();
            }
        });

        mainStage = primaryStage;

        // start the application in "view mode".
        disableAllFields();
        changeToViewMode();
    }

    private void closeMenuItemActionPerformed() {
        saveIfNecessary();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Really Quit?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }

    private void saveAsMenuItemActionPerformed() {
        saveFile = chooser.showSaveDialog(mainStage);
        if (saveFile != null) {
            try {
                FileOutputStream out = new FileOutputStream(saveFile);
                ObjectOutputStream oOut = new ObjectOutputStream(out);
                oOut.writeObject(new ArrayList<>(studentList));
                oOut.flush();
                oOut.close();
            } catch (Exception e) {
                System.out.print("Error : " + e);
            }
            changesMade=false;
        }
    }

    private void saveMenuItemActionPerformed() {
        if (saveFile == null)
            saveAsMenuItemActionPerformed();
        else{
            try {
                FileOutputStream out = new FileOutputStream(saveFile);
                ObjectOutputStream oOut = new ObjectOutputStream(out);
                oOut.writeObject(new ArrayList<>(studentList));
                oOut.flush();
                oOut.close();
            } catch (Exception e) {
                System.out.print("Error : " + e);
            }
            changesMade = false;
        }
    }

    private void openMenuItemActionPerformed() {
        saveIfNecessary();

        File selectedFile  = chooser.showOpenDialog(mainStage);
        if (selectedFile != null) {
            if (!selectedFile.exists()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: File Not Found");
                alert.showAndWait();
            } else {
                saveFile = selectedFile;
                try {
                    FileInputStream in = new FileInputStream(saveFile);
                    ObjectInputStream oIn = new ObjectInputStream(in);
                    List<Student> list = (List<Student>)oIn.readObject();
                    studentList.setAll(list);
                    oIn.close();
                } catch (Exception e) {
                    System.out.print("Error : " + e);
                }

                allStudents.setItems(studentList);
                changesMade = false;
                clearAllFields();
            }
        }

    }

    private void saveIfNecessary(){
        boolean inLoop = changesMade;
        while(inLoop){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save Changes?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                saveMenuItemActionPerformed();
                inLoop = changesMade;
            } else
                inLoop = false;
        }
    }

    private void allStudentsKeyReleased() {
        allStudentsMouseClicked();
    }

    private void allStudentsMouseClicked() {
        Student selected = allStudents.getSelectionModel().getSelectedItem();
        if (selected != null) {
            populateAllTextFields(selected);
        }
    }

    private void addNotesMenuItemActionPerformed() {
        Student selected = allStudents.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select a Student First");
            alert.showAndWait();
        } else {
            // new AddCommentGUI(this, true, selected).setVisible(true);
            AddCommentGUI addCommentGUI = new AddCommentGUI(selected);
            addCommentGUI.initOwner(mainStage);
            addCommentGUI.show();
            changesMade = true;
        }
    }

    private void viewNotesMenuItemActionPerformed() {
        Student selected = allStudents.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select a Student First");
            alert.showAndWait();
        } else {
            // new ShowCommentsGUI(this, true, selected.getMeetingNotes()).setVisible(true);
            ShowCommentsGUI showCommentsGUI = new ShowCommentsGUI(selected.getMeetingNotes());
            showCommentsGUI.initOwner(mainStage);
            showCommentsGUI.show();
        }
    }

    private void findByNameMenuItemActionPerformed() {
        Student searchedFor;
        TextInputDialog dialog = new TextInputDialog("Enter Last Name");
        Optional<String> inputValue = dialog.showAndWait();
        String findName = inputValue.orElse(null);

        if (findName != null) {
            searchedFor = studentList.findByLastName(findName);
            if (searchedFor == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Sorry.  Name not found");
                alert.showAndWait();
            } else {
                populateAllTextFields(searchedFor);
                allStudents.getSelectionModel().select(searchedFor);
            }
        }
    }

    private void findByIDMenuItemActionPerformed() {
        Student searchedFor;
        TextInputDialog dialog = new TextInputDialog("Enter Last Name");
        Optional<String> inputValue = dialog.showAndWait();
        String findID = inputValue.orElse(null);


        if (findID != null) {
            searchedFor = studentList.findByID(findID);

            if (searchedFor == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Sorry.  ID not found");
                alert.showAndWait();
            } else {
                populateAllTextFields(searchedFor);
                allStudents.getSelectionModel().select(searchedFor);
            }
        }
    }

    private void cancelButtonActionPerformed() {
        clearAllFields();
        disableAllFields();
        changeToViewMode();
    }

    private void commitButtonActionPerformed() {
        String newID = IDTF.getText();
        String newFirstname = firstnameTF.getText();
        String newInits = initsTF.getText();
        String newLastname = lastnameTF.getText();
        String newCourse = courseTF.getText();
        String newHomePhone = homePhoneTF.getText();
        String newMobilePhone = mobilePhoneTF.getText();
        String newHomeEmail = homeEmailTF.getText();

        if (newID.equals("") || newFirstname.equals("") ||
                newLastname.equals("") || newCourse.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Please Enter (at least) ID, Firstname, Lastname, and Course");
            alert.showAndWait();
        } else {
            studentList.addStudent(newID, newFirstname, newInits, newLastname,
                    newCourse, newHomeEmail, newHomePhone, newMobilePhone);
            disableAllFields();
            changeToViewMode();

            // scroll the student list to the newly added entry
            Student newStudent = studentList.findByID(newID);
            allStudents.getSelectionModel().select(newStudent);
            changesMade = true;
        }
    }

    private void deleteButtonActionPerformed() {
        int selectedIndex = allStudents.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Please select a student");
            alert.showAndWait();
        } else {
            Student toGo = studentList.get(selectedIndex);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Really delete " + toGo + "?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                studentList.removeStudent(toGo.getStudentNumber());
                clearAllFields();
                allStudents.getSelectionModel().clearSelection();
                changesMade = true;
            }
        }
    }



    private void addNewButtonActionPerformed() {
        enableAllFields();
        clearAllFields();
        changeToEditMode();
    }

    private void changeToEditMode(){
        addNewButton.setDisable(true);
        deleteButton.setDisable(true);
        fileMenu.setDisable(true);
        findMenu.setDisable(true);
        optionsMenu.setDisable(true);
        commitButton.setDisable(false);
        cancelButton.setDisable(false);
        allStudents.setDisable(true);
    }

    private void changeToViewMode(){
        addNewButton.setDisable(false);
        deleteButton.setDisable(false);
        fileMenu.setDisable(false);
        findMenu.setDisable(false);
        optionsMenu.setDisable(false);
        commitButton.setDisable(true);
        cancelButton.setDisable(true);
        allStudents.getSelectionModel().clearSelection();
        allStudents.setDisable(false);
    }

    private void enableAllFields(){
        IDTF.setDisable(false);
        firstnameTF.setDisable(false);
        initsTF.setDisable(false);
        lastnameTF.setDisable(false);
        courseTF.setDisable(false);
        homePhoneTF.setDisable(false);
        mobilePhoneTF.setDisable(false);
        homeEmailTF.setDisable(false);
    }

    private void disableAllFields(){
        IDTF.setDisable(true);
        firstnameTF.setDisable(true);
        initsTF.setDisable(true);
        lastnameTF.setDisable(true);
        courseTF.setDisable(true);
        homePhoneTF.setDisable(true);
        mobilePhoneTF.setDisable(true);
        homeEmailTF.setDisable(true);
    }

    private void clearAllFields(){
        IDTF.setText("");
        firstnameTF.setText("");
        initsTF.setText("");
        lastnameTF.setText("");
        courseTF.setText("");
        homePhoneTF.setText("");
        mobilePhoneTF.setText("");
        homeEmailTF.setText("");
    }

    private void populateAllTextFields(Student s){
        IDTF.setText(s.getStudentNumber());
        firstnameTF.setText(s.getFirstname());
        initsTF.setText(s.getInitials());
        lastnameTF.setText(s.getLastname());
        courseTF.setText(s.getPathway());
        homePhoneTF.setText(s.getTelephoneNo());
        mobilePhoneTF.setText(s.getMobileNo());
        homeEmailTF.setText(s.getHomeEmail());
    }

    private void exitForm() {
        saveIfNecessary();
        System.exit(0);
    }
}
