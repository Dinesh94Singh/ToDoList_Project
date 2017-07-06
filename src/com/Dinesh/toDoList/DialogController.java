package com.Dinesh.toDoList;

import com.Dinesh.toDoList.DataModel.ToDoData;
import com.Dinesh.toDoList.DataModel.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Created by saibaba on 7/4/17.
 */
public class DialogController {
    @FXML
    private TextField Descriptor;

    @FXML
    private DatePicker DeadLinePicker;

    @FXML
    private TextArea DetailsArea;


    public ToDoItem processResults() {
        String shortDescription = Descriptor.getText().trim();
        String details = DetailsArea.getText().trim();
        LocalDate deadLineValue = DeadLinePicker.getValue();
        ToDoItem newItem = new ToDoItem(shortDescription, details, deadLineValue);
        ToDoData.getInstance().addToDoItem(newItem);
        return newItem;
    }

}
