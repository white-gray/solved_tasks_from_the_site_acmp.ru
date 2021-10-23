/*
https://acmp.ru
Задание 267

при проверке не устраивает скорость
*/


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class acmpJava_267 {

    public static void main(String[] args) throws IOException {
//        чтение данных
        File file = new File("fileDir/INPUT.TXT");
        FileReader fis = new FileReader(file);
        int N = Integer.parseInt(String.valueOf((char) fis.read()));
        fis.read();
        int xFirst = Integer.parseInt(String.valueOf((char) fis.read()));
        fis.read();
        int yFirst = Integer.parseInt(String.valueOf((char) fis.read()));
        fis.close();


// x - меньшее время ксерокопирования, y - большее время ксерокопирования
        int x, y;
        if (xFirst < yFirst) {
            x = xFirst;
            y = yFirst;
        } else {
            x = yFirst;
            y = xFirst;
        }
//      обработка данных
        int time;
        int piece = y;
        time = x;       // первую копию делаем не ксероксе в меньшим временем работы

        int q = 1;       //количество сделанных копий
        while (q < N) {
            time += x;
            piece -= x;
            if (piece < 0) {
                q += 1;
                piece = y + piece;
            }
            q += 1;
        }
        System.out.println("time = " + time);

// запись результатов в файл
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write("" + time);
        fw.flush();
        fw.close();
    }
}
