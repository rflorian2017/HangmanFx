package helper;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryParser extends Parser {
    public List<Category> parseDirectory(String path) {
        ArrayList<Category> categories = new ArrayList<>();

        //listFilesWithoutExtensionFromPath returns a List<String> with file names without txt extension
        for (String categoryName : Utility.listFilesWithoutExtensionFromPath(path)) {
            categories.add(new Category(categoryName));
        }

        return categories;
    }
}
