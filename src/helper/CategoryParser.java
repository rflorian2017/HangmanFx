package helper;

import model.Category;
import model.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public static Category parseCategoryFile(boolean cleanWords, String categoryPath) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(categoryPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Category category = new Category(Utility.extractFileNameFromPath(categoryPath));
        String line = bufferedReader.readLine();

        while (line != null) {
            try {
                Word word = WordParser.parseWord(line);
                if (cleanWords && category.wordExists(word.getName())) {
                    //do nothing
                } else {
                    category.addWordToList(word);
                }

            }
            catch (Exception ex) {

            }
            finally {
                line = bufferedReader.readLine();
            }
        }

        if (cleanWords) {
            category.reindexWordList();
        }


        return category;

    }
}
