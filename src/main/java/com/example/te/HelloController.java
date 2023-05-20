package com.example.te;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.Optional;

public class HelloController {

    @FXML
    private TextArea textArea;

    @FXML
    private Label fileLabel;

    @FXML
    private Label formatLabel;

    @FXML
    private Label editLabel;
    @FXML
    private Label viewlabel;
    private File currentFile;
    @FXML
    private Label helpLabel;
    private double defaultFontSize = 14.0;
    private double currentFontSize = defaultFontSize;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void initialize() {

        //File label menu items

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

        //Edit label menu items

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

        MenuItem searchwithbingMenuItem = new MenuItem("Search With Google");
        searchwithbingMenuItem.setOnAction(event -> searchwithbing());

        MenuItem findMenuItem = new MenuItem("Find");
        findMenuItem.setOnAction(event -> find());

        MenuItem findnextMenuItem = new MenuItem("Find Next");
        findnextMenuItem.setOnAction(event -> findnext());
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

        //View label menu items

        MenuItem zoomIn=new MenuItem("Zoom In");
        zoomIn.setOnAction(event -> zoomIn());

        MenuItem zoomOut=new MenuItem("Zoom Out");
        zoomOut.setOnAction(event -> zoomOut());

        MenuItem defaultZoom=new MenuItem("Restore Default");
        defaultZoom.setOnAction(event -> defaultZoom());

        //Format label context menu items
        MenuItem boldMenuItem=new MenuItem("Bold");
        boldMenuItem.setOnAction(event -> setBold());

        MenuItem italicMenuItem=new MenuItem("Italic");
        boldMenuItem.setOnAction(event -> setItalic());

        MenuItem colorMenuItem=new MenuItem("Text Color");
        boldMenuItem.setOnAction(event -> setTextColor());


        //File label context menu
        ContextMenu contextMenu1 = new ContextMenu();
        contextMenu1.getItems().addAll(newMenuItem, newWindowMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem,
                new SeparatorMenuItem(), pageSetupMenuItem, printMenuItem, new SeparatorMenuItem(), exitMenuItem);

        //Edit Label context menu
        ContextMenu contextMenu2 = new ContextMenu();
        contextMenu2.getItems().addAll(undoMenuItem, deleteMenuItem, pasteMenuItem, copyMenuItem, cutMenuItem,
                new SeparatorMenuItem(), replaceMenuItem, findpreveousMenuItem, findMenuItem, findnextMenuItem ,searchwithbingMenuItem,
                new SeparatorMenuItem(),gotoMenuItem , selectallMenuItem,timedateMenuItem
        );

        //view label context menu
        ContextMenu contextMenu3=new ContextMenu();
        contextMenu3.getItems().addAll(zoomOut,zoomIn,defaultZoom);

        //Format label context menu
        ContextMenu contextMenu4=new ContextMenu();
        contextMenu4.getItems().addAll(colorMenuItem,italicMenuItem,boldMenuItem);

        fileLabel.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu1.show(fileLabel, event.getScreenX(), event.getScreenY());
            }
        });
        fileLabel.setContextMenu(contextMenu1);

        editLabel.setOnMouseClicked(event ->{
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu2.show(editLabel, event.getScreenX(), event.getScreenY());
            }
        });
        editLabel.setContextMenu(contextMenu2);

        viewlabel.setOnMouseClicked(event -> {
            if(event.getButton()==MouseButton.PRIMARY){
                contextMenu3.show(viewlabel,event.getScreenX(),event.getScreenY());
            }
        });
        helpLabel.setContextMenu(contextMenu3);

        formatLabel.setOnMouseClicked(event -> {
            if(event.getButton()==MouseButton.PRIMARY){
                contextMenu4.show(formatLabel,event.getScreenX(),event.getScreenY());
            }
        });
        formatLabel.setContextMenu(contextMenu4);
    }

    private void undo() {

        textArea.undo();
    }

    private void cut() {
        String selectedText = textArea.getSelectedText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedText);
        clipboard.setContent(content);
        textArea.replaceSelection("");
    }

    private void copy() {

        String selectedText = textArea.getSelectedText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedText);
        clipboard.setContent(content);

    }
    private void paste() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (clipboard.hasString()) {
            int caretPosition = textArea.getCaretPosition();
            String clipboardContent = clipboard.getString();
            textArea.insertText(caretPosition, clipboardContent);
        }
    }
    private void delete() {

        textArea.replaceSelection("");
    }

    private void searchwithbing() {
        String searchQuery = textArea.getSelectedText();
        if (!searchQuery.isEmpty()) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + searchQuery));
            } catch (Exception e) {
                showError("Error", "Failed to open the browser.");
            }
        }
    }
    private void find() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter text to find:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(searchText -> {
            String text = textArea.getText();
            int startIndex = text.indexOf(searchText);
            if (startIndex != -1) {
                textArea.selectRange(startIndex, startIndex + searchText.length());
            } else {
                showError("Not Found", "Text not found.");
            }
        });
    }

    private void findnext() {
        String searchText = textArea.getSelectedText();
        int currentIndex = textArea.getCaretPosition();
        int nextIndex = textArea.getText().indexOf(searchText, currentIndex + 1);
        if (nextIndex != -1) {
            textArea.selectRange(nextIndex, nextIndex + searchText.length());
        } else {
            showError("Not Found", "No more occurrences found.");
        }
    }

    private void findpreveous() {
        String searchText = textArea.getSelectedText();
        int currentIndex = textArea.getCaretPosition();
        int previousIndex = textArea.getText().lastIndexOf(searchText, currentIndex - 1);
        if (previousIndex != -1) {
            textArea.selectRange(previousIndex, previousIndex + searchText.length());
        } else {
            showError("Not Found", "No previous occurrences found.");
        }
    }
    private void replace() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Replace");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter text to replace:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(searchText -> {
            String text = textArea.getText();
            String replacementText = dialog.getEditor().getText();
            text = text.replace(searchText, replacementText);
            textArea.setText(text);
        });
    }
    private void go_to() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Go To");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter line number:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(lineNumberText -> {
            try {
                int lineNumber = Integer.parseInt(lineNumberText);
                String[] lines = textArea.getText().split("\\n");
                if (lineNumber >= 1 && lineNumber <= lines.length) {
                    int position = 0;
                    for (int i = 0; i < lineNumber - 1; i++) {
                        position += lines[i].length() + 1; // +1 for the newline character
                    }
                    textArea.positionCaret(position);
                } else {
                    showError("Invalid Line Number", "Please enter a valid line number.");
                }
            } catch (NumberFormatException e) {
                showError("Invalid Input", "Please enter a valid line number.");
            }
        });
    }
    private void selectall() {

        textArea.selectAll();
    }
    private void timedate() {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        textArea.insertText(textArea.getCaretPosition(), formattedDateTime);
    }


/////////////////////////////////////////////////////////////////////

    private void newFile() {
        currentFile = null;
        textArea.clear();
    }

    private void newWindow() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setTitle("New Window");

            Scene scene = new Scene(root);
            newStage.setScene(scene);

            newStage.show();
        } catch (IOException e) {
            showError("Error", "Failed to open new window.");
        }

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

    private void zoomIn(){
        currentFontSize += 2.0;
        textArea.setStyle("-fx-font-size: " + currentFontSize + "px;");
    }

    private void zoomOut(){
        currentFontSize -= 2.0;
        textArea.setStyle("-fx-font-size: " + currentFontSize + "px;");
    }

    private void defaultZoom(){
        currentFontSize = defaultFontSize;
        textArea.setStyle("-fx-font-size: " + currentFontSize + "px;");
    }

    private void pageSetup() {
        // TODO: implement page setup functionality
    }

    private void printFile() {
        // TODO: implement print functionality
    }


    private void setBold() {
        Font font = textArea.getFont();
        FontWeight fontWeight = font.getStyle().contains("bold") ? FontWeight.NORMAL : FontWeight.BOLD;
        textArea.setFont(Font.font(font.getFamily(), fontWeight, font.getSize()));
    }

    private void setItalic() {
        Font font = textArea.getFont();
        FontPosture fontPosture = font.getStyle().contains("italic") ? FontPosture.REGULAR : FontPosture.ITALIC;
        textArea.setFont(Font.font(font.getFamily(), fontPosture, font.getSize()));
    }

    private void setTextColor() {
        Color initialColor = Color.BLACK;
        javafx.scene.control.ColorPicker colorPicker = new javafx.scene.control.ColorPicker(initialColor);
        colorPicker.setPrefWidth(200);
        javafx.scene.control.Dialog<Color> dialog = new javafx.scene.control.Dialog<>();
        dialog.getDialogPane().setContent(colorPicker);
        dialog.getDialogPane().getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);
        dialog.setResultConverter(buttonType -> buttonType == javafx.scene.control.ButtonType.OK ? colorPicker.getValue() : null);
        java.util.Optional<Color> result = dialog.showAndWait();
        result.ifPresent(color -> textArea.setStyle("-fx-text-fill: " + toHexCode(color) + ";"));
    }

    private String toHexCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }


    public void exit() {
        System.exit(0);
    }
    public void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}