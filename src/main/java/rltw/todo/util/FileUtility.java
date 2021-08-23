package rltw.todo.util;


import io.micronaut.core.io.IOUtils;

import java.io.*;

public class FileUtility {
    public static boolean exists(String filename) {
        File file = new File(filename);

        return file.exists();
    }

    public static void writeToFile(String filename, String contents) {
        try (FileWriter fileWriter = new FileWriter(filename);) {
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(contents);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static String readFile(String filename)  {
        try(FileReader fileReader = new FileReader(filename)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = IOUtils.readText(bufferedReader);
            bufferedReader.close();
            return data;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        return "";
    }
}
