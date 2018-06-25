package com.hochnt.todolist;

import com.hochnt.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;

import javax.swing.text.html.ListView;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    ListView lstTodoList;
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

    }
}
