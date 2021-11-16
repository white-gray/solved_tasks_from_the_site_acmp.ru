/*
https://acmp.ru
Задание 2
    Требуется посчитать сумму целых чисел, расположенных между числами 1 и N включительно.

Входные данные
    В единственной строке входного файла INPUT.TXT записано единственное целое число N, не превышающее по абсолютной величине 104.

Выходные данные
    В единственную строку выходного файла OUTPUT.TXT нужно вывести одно целое число — сумму чисел, расположенных между 1 и N включительно.

*/


import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class acmpJava_2 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("fileDir/INPUT.TXT"));
        int result = 0;
        int n;
        int a = scanner.nextInt();
        if (a < 1) {
            n = a - (2 * a);
        } else {
            n = a;
        }
        for (int q = 1; q <= n; q++) {
            result += q;
        }
        if (a < 0) {
            result=1-result;
        }
        System.out.println("result = " + result);

        /*
         * запись результатов в файл
         * */
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write("" + result);
        fw.flush();
        fw.close();
    }
}
