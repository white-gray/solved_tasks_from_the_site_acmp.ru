/*
https://acmp.ru
Задание 30
    Петя очень любит наблюдать за электронными часами.
    Он целыми днями смотрел на часы и считал, сколько раз встречается каждая цифра.
    Через несколько месяцев он научился по любому промежутку времени говорить, сколько раз на
    часах за это время встретится каждая цифра, и очень гордился этим.

    Вася решил проверить Петю, но он не знает, как это сделать. Вася попросил Вас помочь ему. Напишите программу, решающую эту задачу

Входнst данные:
    Первая и вторая строки входного файла INPUT.TXT содержат начало и конец промежутка времени
    соответственно. Начальное время не превосходит конечное.
    Время задается в формате hh:mm:ss (0 ≤ hh < 24, 0 ≤ mm < 60, 0 ≤ ss < 60). hh, mm, ss
    дополнены ведущими нулями до двух символов. Эти нули также учитываются при подсчете числа
    цифр.

Выходные данные
   Выходной файл OUTPUT.TXT должен содержать 10 строк.
   В i-ой строке должно быть написано, сколько раз встречается цифра i-1.
*/


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class acmpJava_30 {
    private static String startTime;
    private static String endTime;
    private static int[] startTimeArray;
    private static int[] endTimeArray;
    private static int[] howManyDidgins = new int[10];


    public static void main(String[] args) throws IOException {
        loadDataFromFile();
        getAndCheckData();
        toDo();
        saveDataAtFile();
    }


    /*
     * чтение данных из файла
     * */
    private static void loadDataFromFile() throws IOException {
        File file = new File("fileDir/INPUT.TXT");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        startTime = String.valueOf(in.readLine());
        endTime = String.valueOf(in.readLine());
        in.close();
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        startTimeArray = Arrays.stream(startTime.split(":")).mapToInt(Integer::parseInt).toArray();
        endTimeArray = Arrays.stream(endTime.split(":")).mapToInt(Integer::parseInt).toArray();
        if ((startTimeArray.length != 3) || (endTimeArray.length != 3)) {
            throw new IllegalArgumentException("время в формате ЧЧ:ММ:СС");
        }
        if (startTimeArray[0] < 0 || startTimeArray[0] > 23
                || endTimeArray[0] < 0 || endTimeArray[0] > 23
                || startTimeArray[1] < 0 || startTimeArray[1] > 60
                || endTimeArray[1] < 0 || endTimeArray[1] > 60
                || startTimeArray[2] < 0 || startTimeArray[2] > 60
                || endTimeArray[2] < 0 || endTimeArray[2] > 60) {
            throw new IllegalArgumentException("время в формате ЧЧ:ММ:СС, с правильными числами");
        }
        if ((startTimeArray[0] > endTimeArray[0]) ||
                ((startTimeArray[0] == endTimeArray[0])
                        && (startTimeArray[1] > endTimeArray[1])) ||
                ((startTimeArray[0] == endTimeArray[0])
                        && (startTimeArray[1] == endTimeArray[1]))
                        && (startTimeArray[2] > endTimeArray[2])
        ) {
            throw new IllegalArgumentException("конечное время должно быть после стартового");
        }
    }


    /*
     * выполнение задачи
     * */
    private static void toDo() {
        while (true) {
            add(startTimeArray[0]);
            add(startTimeArray[1]);
            add(startTimeArray[2]);
            startTimeArray[2]++;
            if (startTimeArray[0] >= endTimeArray[0] && startTimeArray[1] >= endTimeArray[1]
                    && startTimeArray[2] > endTimeArray[2]) break;
            if (startTimeArray[2] > 60) {
                startTimeArray[2] = 0;
                startTimeArray[1]++;
                if (startTimeArray[1] > 60) {
                    startTimeArray[1] = 0;
                    startTimeArray[0]++;
                }
            }
        }
    }

    private static void add(int value) {
        int tenner = value / 10;
        int digitTwo = value - (tenner * 10);
        howManyDidgins[tenner]++;
        howManyDidgins[digitTwo]++;
    }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        for (int q = 0; q < 10; q++) {
            fw.write(q);
            fw.write(" встречается ");
            fw.write(String.valueOf(howManyDidgins[q]));
            fw.write("\n");
        }
        fw.flush();
        fw.close();
    }
}