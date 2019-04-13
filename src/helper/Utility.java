package helper;

import constants.ApplicationConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static void createDirectory(String path) {
        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void createCategoryFile(String categoryName) {
        createDirectory(ApplicationConstants.APP_FOLDER_DATA_PATH
                + "\\" +
                ApplicationConstants.CATEGORIES_FOLDER_NAME);

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

    public static List<String> listFilesWithoutExtensionFromPath(String path) {
        ArrayList<String> fileNamesWithoutExtension = new ArrayList<>();
        File directory = new File(path);

        for(File file : directory.listFiles()) {
            try {
                fileNamesWithoutExtension.add(
                        file.getName().substring(0, file.getName().indexOf(".")));
            }
            catch (Exception e) {
                //do nothing, go on to next file
            }
        }

        return fileNamesWithoutExtension;
    }
}
