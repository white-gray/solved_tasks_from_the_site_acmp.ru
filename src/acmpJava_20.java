/*
https://acmp.ru
Задание 20
    Последовательность a1, a2, a3, … , an-1, an называется пилообразной, если она удовлетворяет одному из
    следующих условий:
        1) a1 < a2 > a3 < … > an-1 < an
        2) a1 < a2 > a3 < … < an-1 > an
        3) a1 > a2 < a3 > … < an-1 > an
        4) a1 > a2 < a3 > … > an-1 < an
    Дана числовая последовательность. Требуется определить длину самой длинной ее пилообразной непрерывной
    подпоследовательности.

Входные данные
   В строке входного файла INPUT.TXT через пробел записаны элементs целочисленной последовательности {ai}.
   Ограничения: N<106, |ai| < 32000

Выходные данные
   В единственную строку выходного файла OUTPUT.TXT нужно вывести одно целое число – длину самой длинной
   непрерывной пилообразной подпоследовательности.

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_20 {
    static String dataString;
    static String [] arrayString;
    static Integer[] array;
    static boolean prevStepUp;
    static int sawNow = 0;
    static int saw = 0;


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
        dataString = String.valueOf(in.readLine());
        in.close();
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        arrayString = dataString.split(" ");
        if (arrayString.length > 1000000) {
            throw new IllegalArgumentException("значений в массиве должно быть меньше, чем 10^6");
        }
        array = new Integer[arrayString.length];
        for (int q=0; q<arrayString.length;q++) {
            array[q]=Integer.parseInt(arrayString[q]);
            if (Math.abs(array[q])>32000) {
                throw new IllegalArgumentException("значение не может быть менее -32000 или более 32000");
            }
        }
    }


    /*
     * выполнение задачи
     * */
    private static void toDo() {
        for (int q=1;q< array.length; q++) {
            if (array[q]>array[q-1] && sawNow==0) {
                sawNow++;
                prevStepUp=true;
                continue;
            }
            else if (array[q]<array[q-1] && sawNow==0) {
                sawNow++;
                prevStepUp=false;
                continue;
            }
            else if (array[q]>array[q-1] && !prevStepUp) {
                sawNow++;
                prevStepUp=true;
                continue;
            }
            else if (array[q]<array[q-1] && prevStepUp) {
                sawNow++;
                prevStepUp=false;
                continue;
            }
            if (sawNow>saw) saw=sawNow;
            sawNow=0;
            q--;
        }
        if (sawNow>saw) saw=sawNow;
        saw++;
        System.out.println("\n\nsaw = " + (saw));
    }


    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(saw));
        fw.flush();
        fw.close();
    }
}

