package helper;

import constants.ApplicationConstants;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static void createAppDirectory(String path) {
        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void createCategoryFile(String categoryName) {
        File categoryFile = new File(ApplicationConstants.APP_FOLDER_DATA_PATH
                + "\\" +
                ApplicationConstants.CATEGORIES_FOLDER_NAME +
                "\\" +
                categoryName + ".txt");
        try {
            categoryFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}