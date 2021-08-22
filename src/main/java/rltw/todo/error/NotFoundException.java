package rltw.todo.error;

public class NotFoundException extends Exception {
    private String entityName;

    public NotFoundException(String entityName){
        super();

        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
