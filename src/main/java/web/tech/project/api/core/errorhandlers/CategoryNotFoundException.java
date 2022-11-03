package web.tech.project.api.core.errorhandlers;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(int id) {
        super("There are no items with categoryId = " + id);
    }
}
