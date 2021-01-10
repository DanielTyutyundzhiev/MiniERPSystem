package Project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLoader {

    public void writeFile(int id){
        try {
            File file=new File("E:\\SAP_Project\\src\\sales_repID.txt");
            FileWriter writer=new FileWriter("E:\\SAP_Project\\src\\sales_repID.txt");
            writer.write(Integer.toString(id));
            System.out.println(id);
            writer.close();

            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            String st;
            while ((st=bufferedReader.readLine())!=null){
                System.out.println(st);
            }

        }catch (Exception e){
            e.getCause();
        }

    }

    public int readFile(){
        File file = new File("E:\\SAP_Project\\src\\sales_repID.txt");
        Path path = Path.of("E:\\SAP_Project\\src\\sales_repID.txt");
        String idd = null;
        int srID;
        try {
            idd = Files.readString(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

       return srID = Integer.parseInt(idd);
    }
}
