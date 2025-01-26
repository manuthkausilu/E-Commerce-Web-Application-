package com.mycompany.ecommerce.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public static boolean deleteFile(String path) {
        boolean f = false;

        try {
            File file = new File(path);
            f = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean saveFile(InputStream inputStream, String path) {
        boolean f = false;

        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
    // Utility method to validate if the file is an image

    /**
     *
     * @param fileType
     * @param fileExtension
     * @return
     */
    public static boolean isImage(String fileType, String fileExtension) {
        
        // List of valid image MIME types
        List<String> validMimeTypes = Arrays.asList("image/jpeg", "image/png", "image/gif", "image/bmp", "image/webp");

        // List of valid image file extensions
        List<String> validExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "webp");

        return validMimeTypes.contains(fileType) &&
                 validExtensions.contains(fileExtension);
    }
}
