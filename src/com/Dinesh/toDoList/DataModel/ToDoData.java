package com.Dinesh.toDoList.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * Created by saibaba on 7/3/17.
 * The Class will Load the Data, to create a ListItem
 */
public class ToDoData {
    // The only object created for this class is this.
    private static ToDoData instance = new ToDoData();

    //Instance Variables
    private static String fileName = "ToDoListItems.txt";

    private ObservableList<ToDoItem> toDoItems;
    private DateTimeFormatter formatter;

    // very important because, we are using this only
    public static ToDoData getInstance() {
        return instance;
    }

    // prevents instantiating from other
    // Singleton
    private ToDoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void addToDoItem(ToDoItem item){
        toDoItems.add(item);
    }

    public void setToDoItems(ObservableList<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }

    public void deleteToDoItem(ToDoItem item){
        toDoItems.remove(item);
    }
    public ObservableList<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public void loadToDoItems() throws IOException {
        toDoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString);

                ToDoItem toDoItem = new ToDoItem(shortDescription, details, date);
                toDoItems.add(toDoItem);

            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeToDoItems() throws IOException{
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try{
            Iterator<ToDoItem> itr = toDoItems.iterator();
            while(itr.hasNext()){
                ToDoItem item = itr.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline()
                        ));
                bw.newLine();
            }
        }finally {
            if(bw!=null){
                bw.close();
            }
        }
    }

}
