/*
https://acmp.ru
Задание 16
    Лесенкой называется набор кубиков, в котором каждый более верхний слой содержит кубиков меньше,
    чем предыдущий.
    Требуется написать программу, вычисляющую число лесенок, которое можно построить из N кубиков.

Входные данные
   Во входном файле INPUT.TXT записано натуральное число N (1 ≤ N ≤ 100) – количество кубиков в лесенке.

Выходные данные
   В выходной файл OUTPUT.TXT необходимо вывести число лесенок, которые можно построить из N кубиков.

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_16 {
    static int howManyBoxes;
    static int variants;


    public static void main(String[] args) throws IOException {
        loadDataFromFile();
        variants = toDo(howManyBoxes+1, howManyBoxes);
        saveDataAtFile();
    }


    /*
     * чтение данных из файла
     * */
    private static void loadDataFromFile() throws IOException {
        File file = new File("fileDir/INPUT.TXT");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        howManyBoxes = Integer.valueOf(in.readLine());
        in.close();
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        if (howManyBoxes < 1 || howManyBoxes > 100) {
            throw new IllegalArgumentException("Задаём числа от 1 до 100");
        }
    }



    /*
     * выполнение задачи
     * */
    private static int toDo(int prev_level, int n) {
            if (n == 0)
                return 1;
            int count = 0;
            for (int level = 1; level < prev_level; ++level) {
                if ((n - level) < 0)
                    break;
                count += toDo(level, n - level);
            }
            return count;
        }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(variants));
        fw.flush();
        fw.close();
    }


}

