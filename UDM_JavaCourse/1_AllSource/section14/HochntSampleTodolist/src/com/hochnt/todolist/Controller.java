package com.hochnt.todolist;

import com.hochnt.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private ListView todoListView;
    @FXML
    private TextArea txtArea;
    @FXML
    private Label lblItemDue;
    private List<TodoItem> todoItems;


    public void initialize() {
        TodoItem item1 = new TodoItem("Mail birthdayy", "Buy abc", LocalDate.of(2018, Month.APRIL, 25));
        TodoItem item2 = new TodoItem("asba", "Buy abc", LocalDate.of(2018, Month.APRIL, 25));
        TodoItem item3 = new TodoItem("abasb", "Buy abc", LocalDate.of(2014, Month.APRIL, 25));
        TodoItem item4 = new TodoItem("hochnt", "Buy abc", LocalDate.of(2015, Month.APRIL, 25));
        TodoItem item5 = new TodoItem("Mail 12312 birthdayy", "Buy alsoiialknb", LocalDate.of(2018, Month.APRIL, 25));
        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null){
                    TodoItem item = (TodoItem)todoListView.getSelectionModel().getSelectedItem();
                    txtArea.setText(item.getDetails());
                    lblItemDue.setText(item.getDeadLine().toString());
                }
            }
        });
        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

//    @FXML
//    public void handleClickListView(){
//        TodoItem item = (TodoItem)todoListView.getSelectionModel().getSelectedItem();
//        txtArea.setText(item.getDetails());
////        StringBuilder sb = new StringBuilder(item.getDetails());
////        sb.append("\n\n\n\n");
////        sb.append("Due: ");
////        sb.append(item.getDeadLine().toString());
////        txtArea.setText(sb.toString());
//        lblItemDue.setText(item.getDeadLine().toString());
//    }
}
