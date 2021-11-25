/*
https://acmp.ru
Задание 5
    Вася не любит английский язык, но каждый раз старается получить хотя бы четверку за четверть, чтобы
    оставаться ударником. В текущей четверти Вася заметил следующую закономерность: по нечетным дням месяца он
    получал тройки, а по четным – четверки. Так же он помнит, в какие дни он получал эти оценки. Поэтому он
    выписал на бумажке все эти дни для того, чтобы оценить, сколько у него троек и сколько четверок.
    Помогите Васе это сделать, расположив четные и нечетные числа в разных строчках.
    Вася может рассчитывать на оценку 4, если четверок не меньше, чем троек.

Входные данные
    В первой строке входного файла INPUT.TXT записано единственное число N – количество элементов
    целочисленного массива (1 ≤ N ≤ 100). Вторая строка содержит N чисел, представляющих заданный массив.
    Каждый элемент массива – натуральное число от 1 до 31.
    Все элементы массива разделены пробелом.

Выходные данные
    В первую строку выходного файла OUTPUT.TXT нужно вывести числа, которые соответствуют дням месяцев, в
    которые Вася получил тройки, а во второй строке соответственно расположить числа месяца, в которые Вася
    получил четверки. В третьей строке нужно вывести «YES», если Вася может рассчитывать на четверку и «NO» в
    противном случае. В каждой строчке числа следует выводить в том же порядке, в котором они идут во входных
    данных.
    При выводе числа отделяются пробелом.

*/


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

public class acmpJava_5 {
    public static void main(String[] args) throws IOException {
        /*
         * чтение данных из файла
         * */
        File file = new File("fileDir/INPUT.TXT");
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String stringSizeArrea = in.readLine();
        String stringArray = in.readLine();
        in.close();

        /*
         * распозначание данных
         * */
        int sizeArray =  Integer.parseInt (stringSizeArrea);
        String[] arrayStr = stringArray.split(" ");
        int[] array = new int[sizeArray];
        for (int q = 0; q < sizeArray; q++) {
            array[q] = Integer.parseInt (arrayStr[q]);
            if (array[q] < 1 || array[q]>31) { throw new IllegalArgumentException("неверная дата!");}
        }
        System.out.println("Количество учебных дней "+ sizeArray);
        System.out.printf("\tдаты учебных дней: ");
        for (int q : array) {
            System.out.printf(q + " ");
        }
        System.out.println();

/*
* вычисления
* */
        String evenData = "";
        String oddData = "";
        for (int q = 0; q < sizeArray; q++) {
            if ( array[q] % 2 == 0 ) {
                evenData += array[q] + " ";
            }
            else {
                oddData += array[q] + " ";
            }
        }
        String result = "Yes";
        String[] numberEvenData = evenData.split(" ");
        String[] numberOddData = oddData.split(" ");

        if (numberEvenData.length < numberOddData.length) {
            result = "No";
        }

        /*
         * запись результатов в файл
         * */
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(evenData+"\n");
        fw.write(oddData+"\n");
        fw.write(result);
        fw.flush();
        fw.close();
    }
}
