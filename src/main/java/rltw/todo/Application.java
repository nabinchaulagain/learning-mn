package rltw.todo;

import io.micronaut.runtime.Micronaut;
import rltw.todo.service.TodoService;
import rltw.todo.util.FileUtility;

public class Application {

    public static void main(String[] args) {
        createDataFiles();
        Micronaut.run(Application.class, args);
    }

    public static void createDataFiles(){
        if(!FileUtility.exists(TodoService.DATA_FILE)){
            FileUtility.writeToFile(TodoService.DATA_FILE,"[]");
        }
    }


}
