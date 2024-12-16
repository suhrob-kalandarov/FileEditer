package Lesson7.Task2_Optimized_Task1.entity;
import Lesson7.utilita.Input;
import java.io.*;

public class FileCopy implements Runnable {
    private final File source;
    private final File dest;
    private final int copyAmount;

    public FileCopy(File source, File dest, int copyAmount) {
        this.source = source;
        this.dest = dest;
        this.copyAmount = copyAmount;
    }

    @Override
    public void run() {
        try {
            copyFile(source, dest);
            System.out.println("\nFayl: " + dest.getName());
            System.out.println("Successfully copied!");
            System.out.println("\n 1 - kirib ko'rish\n 2 - Orqaga");
            int choice = Input.getInt("-> Choose: ");
            switch (choice) {
                case 1 -> {
                    System.out.println("|> Opening file: " + dest.getName());
                    break;
                }
                case 2 -> {
                    return;
                }
                default -> System.out.println("<<!>> Invalid action! <<!>>");
            }
        } catch (IOException e) {
            System.out.println("<<!>> Failed to copy file: " + e.getMessage());
        }
    }

    private void copyFile(File source, File dest) throws IOException {
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
}