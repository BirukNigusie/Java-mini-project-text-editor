package com.example.te;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HelloController {

    @FXML
    private TextArea textArea;

    @FXML
    private Label fileLabel;

    @FXML
    private Label editLabel;
    private File currentFile;

    public void initialize() {
        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(event -> newFile());

        MenuItem newWindowMenuItem = new MenuItem("New window");
        newWindowMenuItem.setOnAction(event -> newWindow());

        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(event -> openFile());

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(event -> saveFile());

        MenuItem saveAsMenuItem = new MenuItem("Save As");
        saveAsMenuItem.setOnAction(event -> saveFileAs());

        MenuItem pageSetupMenuItem = new MenuItem("Page Setup");
        pageSetupMenuItem.setOnAction(event -> pageSetup());

        MenuItem printMenuItem = new MenuItem("Print");
        printMenuItem.setOnAction(event -> printFile());

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> exit());
        //////////////////////////////////////////////////////////
        MenuItem undoMenuItem = new MenuItem("Undo");
        undoMenuItem.setOnAction(event -> undo());

        MenuItem cutMenuItem = new MenuItem("Cut");
        cutMenuItem.setOnAction(event -> cut());

        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.setOnAction(event -> copy());

        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setOnAction(event -> paste());

        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(event -> delete());

        MenuItem searchwithbingMenuItem = new MenuItem("Search With Bing");
        searchwithbingMenuItem.setOnAction(event -> searchwithbing());

        MenuItem findMenuItem = new MenuItem("Find");
        findMenuItem.setOnAction(event -> find());

        MenuItem findnextMenuItem = new MenuItem("Find Next");
        findnextMenuItem.setOnAction(event -> findnext());
        //////////////////////////////////////////
        MenuItem findpreveousMenuItem = new MenuItem("Find Preveous");
        findpreveousMenuItem.setOnAction(event -> findpreveous());

        MenuItem replaceMenuItem = new MenuItem("Replace");
        replaceMenuItem.setOnAction(event -> replace());

        MenuItem gotoMenuItem = new MenuItem("Go To");
        gotoMenuItem.setOnAction(event -> go_to());

        MenuItem selectallMenuItem = new MenuItem("Select All");
        selectallMenuItem.setOnAction(event -> selectall());

        MenuItem timedateMenuItem = new MenuItem("Time/Date");
        timedateMenuItem.setOnAction(event -> timedate());


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(newMenuItem, newWindowMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem,
                new SeparatorMenuItem(), pageSetupMenuItem, printMenuItem, new SeparatorMenuItem(), exitMenuItem);
        ContextMenu contextMenu2 = new ContextMenu();
        contextMenu.getItems().addAll(undoMenuItem, timedateMenuItem, selectallMenuItem, gotoMenuItem,
                new SeparatorMenuItem(), replaceMenuItem,
                findpreveousMenuItem, findMenuItem, findnextMenuItem, findMenuItem, searchwithbingMenuItem,
                new SeparatorMenuItem()
                , deleteMenuItem, pasteMenuItem
                , copyMenuItem, cutMenuItem, undoMenuItem);
        fileLabel.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(fileLabel, event.getScreenX(), event.getScreenY());
            }
        });
        fileLabel.setContextMenu(contextMenu);

        editLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    contextMenu2.show(editLabel, event.getScreenX(), event.getScreenY());
                }
            }
        });

        editLabel.setContextMenu(contextMenu2);
    }

    private void undo() {
        // TODO: implement new window functionality
    }
    private void cut() {
        // TODO: implement new window functionality
    }
    private void copy() {
        // TODO: implement new window functionality
    }
    private void paste() {
        // TODO: implement new window functionality
    }
    private void delete() {
        // TODO: implement new window functionality
    }
    private void searchwithbing() {
        // TODO: implement new window functionality
    }
    private void find() {
        // TODO: implement new window functionality
    }
    private void findnext() {
        // TODO: implement new window functionality
    }
    private void findpreveous() {
        // TODO: implement new window functionality
    }
    private void replace() {
        // TODO: implement new window functionality
    }
    private void go_to() {
        // TODO: implement new window functionality
    }
    private void selectall() {
        // TODO: implement new window functionality
    }
    private void timedate() {
        // TODO: implement new window functionality
    }
/////////////////////////////////////////////////////////////////////

    private void newFile() {
        currentFile = null;
        textArea.clear();
    }

    private void newWindow() {
        // TODO: implement new window functionality
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File selectedFile = fileChooser.showOpenDialog(textArea.getScene().getWindow());

        if (selectedFile != null) {
            try {
                String content = Files.readString(selectedFile.toPath());
                textArea.setText(content);
                currentFile = selectedFile;
            } catch (IOException e) {
                showError("Error opening file", e.getMessage());
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            saveFileAs();
        } else {
            try {
                Files.writeString(currentFile.toPath(), textArea.getText());
            } catch (IOException e) {
                showError("Error saving file", e.getMessage());
            }
        }
    }

    private void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        File selectedFile = fileChooser.showSaveDialog(textArea.getScene().getWindow());

        if (selectedFile != null) {
            try {
                Files.writeString(selectedFile.toPath(), textArea.getText());
                currentFile = selectedFile;
            } catch (IOException e) {
                showError("Error saving file", e.getMessage());
            }
        }
    }

    private void pageSetup() {
        // TODO: implement page setup functionality
    }

    private void printFile() {
        // TODO: implement print functionality
    }

    private void exit() {
        System.exit(0);
    }
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}