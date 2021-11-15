/*
https://acmp.ru
Задание 1
    Требуется сложить два целых числа А и В.

Входные данные
    В единственной строке входного файла INPUT.TXT записаны два натуральных числа через пробел. Значения чисел не превышают 109.

Выходные данные
    В единственную строку выходного файла OUTPUT.TXT нужно вывести одно целое число — сумму чисел А и В.
*/


import java.io.*;
import java.util.Scanner;

public class acmpJava_1 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("fileDir/INPUT.TXT"));
        String i = scanner.nextLine();
        String[] arr = i.split(" ");
        int a = Integer.valueOf(arr[0])                ;
        int b = Integer.valueOf(arr[1])                ;


        /*
         * проверка, что числа верные (не превышают 10^9)
         * */
        if (a > Math.pow(10, 9) || b > Math.pow(10, 9)) {
            throw new IllegalArgumentException("big digit");
        }
        /*
         * расчёт
         * */
        int summ = a + b;
        System.out.println("a = " + a+ "\t+\tb = " + b+"\t=\tsumm = " + summ);


        /*
         * запись результатов в файл
         * */
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write("" + summ);
        fw.flush();
        fw.close();
    }
}
