/*
https://acmp.ru
Задание 14
    Требуется написать программу, определяющую наименьшее общее кратное (НОК) чисел a и b.

Входные данные
   В единственной строке входного файла INPUT.TXT записаны два натуральных числа А и В через
   пробел, не превышающих 46340.

Выходные данные
   В единственную строку выходного файла OUTPUT.TXT нужно вывести одно целое число — НОК чисел А и В.

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_14 {
    static String forComparison;
    static int digit1,digit2,NOC;


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
        forComparison = in.readLine();
        in.close();
    }

    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        String[] dataString = forComparison.split(" ");
        if (dataString.length != 2) {
            throw new IllegalArgumentException("Должно быть только 2 числа!");
        }
        digit1 = Integer.valueOf(dataString[0]);
        digit2 = Integer.valueOf(dataString[1]);
        if (digit1 < 1 || digit1 > 46340 || digit2 < 1 || digit2 > 46340) {
            throw new IllegalArgumentException("Задаём числа от 1 до 46340");
        }
    }

    /*
     * выполнение задачи
     * */
    private static void toDo() {
        int NOD = getGCD(digit1, digit2);
        System.out.println("NOD = " + NOD);
        NOC = getLCM(digit1, digit2);
        System.out.println("NOC = " + NOC);
    }

    private static int getGCD(int digit1, int digit2) {
        return digit2 == 0 ? digit1 : getGCD(digit2, digit1 % digit2);
    }

    private static int getLCM(int digit1, int digit2) {
        return (digit1 * digit2) / getGCD(digit1, digit2);
    }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(NOC));
        fw.flush();
        fw.close();
    }


}

