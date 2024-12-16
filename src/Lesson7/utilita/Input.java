package Lesson7.utilita;
import java.util.Scanner;

public interface Input {
    Scanner scannerForInt = new Scanner(System.in);
    Scanner scannerForStr = new Scanner(System.in);

    static int getInt(String msg){
        System.out.print(msg);
        return scannerForInt.nextInt();
    }

    static String getStr(String msg){
        System.out.print(msg);
        return scannerForStr.nextLine();
    }
}