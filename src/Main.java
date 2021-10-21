import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        чтение данных
        File file = new File("fileDir/INPUT.TXT");
        FileReader fis = new FileReader(file);
        int charCode;
        String line = "";
        while ((charCode = fis.read()) != -1) {
            line += (char) charCode;
        }
            fis.close();
        System.out.println("line = " + line);
        String[] mean = line.split(" ");

//      обработка данных

        }
    }
