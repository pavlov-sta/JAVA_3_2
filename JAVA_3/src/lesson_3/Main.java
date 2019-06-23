package lesson_3;

/*
1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.Collections;
import java.util.Enumeration;


public class Main {

    private static String folder = "src/lesson_3/file/";

    public static void main(String[] args) throws IOException {
        /*File folder = new File("src/lesson_3/file/");
        folder.mkdir();*/

        file();
        sewFail();





    }

    public static void file() throws IOException {
        System.out.print("1 Задание \n");
        String fileName = "newFail1.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(folder + fileName)) {
            for (int i = 1; i < 51; i++) {
                fileOutputStream.write(i);
            }
        }
        try (FileInputStream in = new FileInputStream(folder + fileName)) {
            byte[] arr = new byte[in.available()];
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.println("Байтовый массив: " + Arrays.toString(arr));
            }
        }
    }

    public static void sewFail() throws IOException {
        System.out.print("2 Задание\n");
        String fileName = "newFail2.txt";
        ArrayList<String> alNameFail = new ArrayList<>();
        for (int i = 0; i < 5; i++) alNameFail.add("newFail2_" + i + ".txt");
        for (String e : alNameFail) {
            try (FileOutputStream newFileOutputStream = new FileOutputStream(folder + e)) {
                for (int i = 0; i < 100; i++) {
                    newFileOutputStream.write(i);
                }
            }
        }
        ArrayList<InputStream> al = new ArrayList<>();
        for (String e : alNameFail)
            al.add(new FileInputStream(folder + e));

        Enumeration<InputStream> e = Collections.enumeration(al);
        SequenceInputStream in = new SequenceInputStream(e);
        try (FileOutputStream out = new FileOutputStream(folder + fileName)) {
            int x;
            while ((x = in.read()) != -1)
                out.write(x);
        }
        File file = new File(folder + fileName);
        if (file.exists())
            System.out.printf("Размер выходного файла " + file.length() + " байт.");
    }
}
