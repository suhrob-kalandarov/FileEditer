package Lesson7.Task3;

import Lesson7.utilita.Input;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    
                    --------------------
                     1 - Show all files
                     2 - Report
                     3 - Exit app"""
            );
            switch (Input.getInt("-> Choose: ")) {
                case 1 -> encryption();

                case 2 -> decryption();

                case 3 -> {
                    System.out.println("\n <|> You're logging out! Bye... !.");
                    return;
                }
                default -> System.out.println("\n<<!>> Invalid choice!. Try again! <<!>>");
            }
        }
    }

    private static void encryption() {
        showAllFiles();
    }

    private static void showAllFiles() {
        File folder = new File("src/Lesson7/Task3/files");
        //checkFolder(folder);

        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            System.out.println("\n|> Files:");
            for (int i = 0; i < files.length; i++) {
                System.out.println(" " + (i + 1) + ". " + files[i].getName());
            }
            int fileChoice = Input.getInt("0 - Back\n-> Select a file by number: ");
            if (fileChoice == 0) return;
            if (fileChoice < 1 || fileChoice > files.length) {
                System.out.println("<!> ERROR! Invalid file choice! <!>");
                return;
            }
            File selectedFile = files[fileChoice - 1];
            System.out.println("\n-\n|> Selected file: " + selectedFile.getName() + "\n  1 - Delete\n  2 - Copy\n  3 - Back");
        } else {
            System.out.println("No files found.");
            //createFile(folder);
        }
    }

    private static void decryption() {
        
    }
}
