/*
https://acmp.ru
Задание 24
    Король Флатландии решил вырубить некоторые деревья, растущие перед его дворцом.
    Деревья перед дворцом короля посажены в ряд, всего там растет n деревьев, расстояния между соседними
    деревьями одинаковы.
    После вырубки перед дворцом должно остаться m деревьев, и расстояния между соседними деревьями должны
    быть одинаковыми. Помогите королю выяснить, сколько существует способов вырубки деревьев.
    Требуется написать программу, которая по заданным числам n и m определит, сколько существует способов
    вырубки некоторых из n деревьев так, чтобы после вырубки осталось m деревьев и соседние деревья
    находились на равном расстоянии друг от друга

Входные данные
   Входной файл INPUT.TXT содержит два целых числа n и m (0 ≤ m , n ≤ 1000).

Выходные данные
   В единственную строку выходного файла OUTPUT.TXT нужно вывести одно целое число — искомое число способов.

Пояснение к примеру
    Если обозначить условно исходное расположение деревьев перед дворцом как «TTTTT», то возможные
    результаты после вырубки следующие:
                                    «TTT..», «.TTT.», «..TTT», «T.T.T»

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_24 {
    public static int result;
    private static String dataString;
    private static String[] arrayString;
    private static Integer[] array;
    private static int now;
    private static int stay;

    public static void main(String[] args) throws IOException {
        loadDataFromFile();
        getAndCheckData();
        result = toDo();
        saveDataAtFile(result);
    }


    /*
     * чтение данных из файла
     * */
    private static void loadDataFromFile() throws IOException {
        File file = new File("fileDir/INPUT.TXT");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        dataString = String.valueOf(in.readLine());
        in.close();
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        arrayString = dataString.split(" ");
        if (arrayString.length != 2) {
            throw new IllegalArgumentException("надо прописать два значения");
        }
        array = new Integer[arrayString.length];
        for (int q = 0; q < arrayString.length; q++) {
            array[q] = Integer.parseInt(arrayString[q]);
            if (array[q] < 0 || array[q] > 1000) {
                throw new IllegalArgumentException("значение не может быть менее 0 или более 1000");
            }
        }
        if (stay > now) {
            throw new IllegalArgumentException("не получится срезать деревьев больше, чем их есть");
        }
        now = array[0];
        stay = array[1];
    }


    /*
     * выполнение задачи
     * */
    private static int toDo() {
        if (stay == 0) {
            return 1;
        }
        if (stay == 1) {
            return now;
        }
        if (stay - (now - stay) > 1) {
            return last(now-stay);
        }
        int howManyDivisions = (now-stay)/(stay-1), result=0;
        for (int q = 1; q <= howManyDivisions; q++) {
            result +=last(now-stay - (stay-1)*q);
        }
        result += last (now-stay);
        return result;
    }

    private static int last(int i) {
        return i+1;
    }


    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile(int result) throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(result));
        fw.flush();
        fw.close();
    }
}

