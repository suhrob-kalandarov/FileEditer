package Lesson7.Task1.fileManager;
import Lesson7.utilita.Input;
import java.io.*;

public class FileManager {
    public static void main(String[] args) {

        while (true) {
            System.out.println("""
                    
                    --------------------
                     1 - Show all files
                     2 - Report
                     3 - Exit app"""
            );
            switch (Input.getInt("-> Choose: ")) {
                case 1 -> showAllFiles();

                case 2 -> generateReport();

                case 3 -> {
                    System.out.println("\n <|> You're logging out! Bye... !.");
                    return;
                }
                default -> System.out.println("\n<<!>> Invalid choice!. Try again! <<!>>");
            }
        }
    }

    public static void showAllFiles() {
        File folder = new File("src/Lesson7/Task1/files");
        checkFolder(folder);

        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            System.out.println("\n|> Files:");
            for (int i = 0; i < files.length; i++) {
                System.out.println(" " + (i + 1) + ". " + files[i].getName());
            }
            interactWithFile(files);
        } else {
            System.out.println("No files found.");
            createFile(folder);
        }
    }

    private static void checkFolder(File folder) {
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("<<!> Directory created: " + folder.getAbsolutePath());
            } else {
                System.out.println("<<!>> Failed to create directory: " + folder.getAbsolutePath());
                return;
            }
        }
    }

    private static void createFile(File folder) {
        System.out.println("""
                ---
                 1 - Automatic file creation (default.txt)
                 2 - Manual creation
                 3 - Back"""
        );
        switch (Input.getInt("-> Choose: ")){
            case 1 -> {
                try {
                    File defaultFile = new File(folder, "default.txt");
                    if (defaultFile.createNewFile()) {
                        System.out.println("|> Default file created: " + defaultFile.getName());
                    }
                } catch (IOException e) {
                    System.out.println("<!> Failed to create default file: " + e.getMessage());
                }
            }

            case 2 -> {
                try {
                    File defaultFile = new File(folder, Input.getStr("<!> Enter file name.extension"));
                    if (defaultFile.createNewFile()) {
                        System.out.println("|> File created: " + defaultFile.getName());
                    }
                } catch (IOException e) {
                    System.out.println("<!> Failed to create file: " + e.getMessage());
                    createFile(folder);
                }
            }
            case 3 -> {
                return;
            }
            default -> System.out.println("<<!>> Invalid action! <<!>>");
        }
    }

    private static void interactWithFile(File[] files) {
        int fileChoice = Input.getInt("0 - Back\n-> Select a file by number: ");
        if (fileChoice == 0) return;
        if (fileChoice < 1 || fileChoice > files.length) {
            System.out.println("<!> ERROR! Invalid file choice! <!>");
            return;
        }

        File selectedFile = files[fileChoice - 1];
        System.out.println("\n-\n|> Selected file: " + selectedFile.getName() + "\n  1 - Delete\n  2 - Copy\n  3 - Back");
        switch (Input.getInt("-> Choose next option: ")) {
            case 1 -> {
                if (selectedFile.delete()) {
                    System.out.println("\n <|> File deleted successfully!");
                } else {
                    System.out.println("\n<<!>> Failed to delete the file! <<!>>");
                }
            }
            case 2 -> {
                int copies = Input.getInt("\n---\n-> Enter the number of file copies: ");
                System.out.println("---");
                for (int i = 1; i <= copies; i++) {

                    String parentDirectory = selectedFile.getParent();
                    String fileNameWithoutExtension = selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf('.'));
                    String extension = getFileExtension(selectedFile);
                    String newFileName = fileNameWithoutExtension + i + "." + extension;
                    File copy = new File(parentDirectory, newFileName);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        copyFile(selectedFile, copy);
                        System.out.println("|> Created copy: " + copy.getName());
                    } catch (IOException e) {
                        System.out.println("<<!> Failed to copy file: " + e.getMessage());
                    }
                }
            }
            case 3 -> {
                return;
            }
            default -> System.out.println("<<!>> Invalid action! <<!>>");
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        try (
                InputStream is = new FileInputStream(source);
                OutputStream os = new FileOutputStream(dest)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        return (lastIndex == -1) ? "" : name.substring(lastIndex + 1);
    }

    private static void generateReport() {
        File folder = new File("src/Lesson7/Task1/files");
        checkFolder(folder);

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println(" <<!>> No files found to generate report! <<!>>");
            return;
        }

        double imageSize, videoSize, audioSize;
        int imageCount = 0, videoCount = 0, audioCount = 0;

        for (File file : files) {
            String extension = getFileExtension(file).toLowerCase();

            if (extension.matches("jpg|jpeg|png")) {
                imageCount++;
            } else if (extension.matches("mp4|mkv")) {
                videoCount++;
            } else if (extension.matches("mp3|ogg")) {
                audioCount++;
            }
        }

        imageSize = imageCount * 1.54;
        videoSize = videoCount * 12.3;
        audioSize = audioCount * 4.45;

        System.out.println("\n--- Report ---");
        System.out.printf("Images: %.1f MB\n", imageSize);
        System.out.printf("Videos: %.1f MB\n", videoSize);
        System.out.printf("Audios: %.1f MB\n", audioSize);
    }
}