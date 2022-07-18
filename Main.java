import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            File myFile = new File("tlum.txt");
            File saveFile = new File("przetlumaczone.txt");

            if (saveFile.createNewFile()) {

            }
            FileWriter write = new FileWriter(saveFile);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int number = data.indexOf(",,"), bracket0, bracket1;

                if (number + 2 >= data.length()) {
                    continue;
                }
                while (number != -1) {
                    data = data.substring(number + 2, data.length());
                    number = data.indexOf(",,");
                }
                bracket0 = data.indexOf("{");
                bracket1 = data.indexOf("}");
                while (bracket0 != -1 && bracket1 != -1) {
                    data = data.substring(0, bracket0) + data.substring(bracket1 + 1, data.length());
                    bracket0 = data.indexOf("{");
                    bracket1 = data.indexOf("}");
                }
                if (data.length() == 0) {
                    continue;
                }
                if (data.indexOf("\\N") != -1) {
                    number = data.indexOf("\\N");
                    data = data.substring(0, number) + " " + data.substring(number + 2, data.length());

                }
                // System.out.println(data + " " + data.length());
                if (data.charAt(data.length() - 1) == '.' || data.charAt(data.length() - 1) == '!'
                        || data.charAt(data.length() - 1) == '?') {
                    write.write(data + "\n\n");
                } else {
                    write.write(data + ' ');
                }
            }
            myReader.close();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}