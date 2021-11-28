/*
https://acmp.ru
Задание 7
    Петя успевает по математике лучше всех в классе, поэтому учитель задал ему сложное домашнее задание, в
    котором нужно в заданном наборе целых чисел найти сумму всех положительных элементов, затем найти где в
    заданной последовательности находятся максимальный и минимальный элемент и вычислить произведение чисел,
    расположенных в этой последовательности между ними. Так же известно, что минимальный и максимальный
    элемент встречаются в заданном множестве чисел только один раз и не являются соседними. Поскольку задач
    такого рода учитель дал Пете около ста, то Петя как сильный программист смог написать программу, которая
    по заданному набору чисел самостоятельно находит решение.
    А Вам слабо?

Входные данные
    В первой строке входного файла INPUT.TXT записано N целых чисел, представляющих заданный массив.
    Все элементы массива разделены пробелом.
    Каждое из чисел во входном файле, в том числе и N, не превышает 10^2 по абсолютной величине.

Выходные данные
    В единственную строку выходного файла OUTPUT.TXT нужно вывести два числа, разделенных пробелом:
       сумму положительных элементов
       и произведение чисел, расположенных между минимальным и максимальным элементами.
    Значения суммы и произведения не превышают по модулю 3*10^4.

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_9 {
    public static void main(String[] args) throws IOException {
        /*
         * чтение данных из файла
         * */
        File file = new File("fileDir/INPUT.TXT");
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String stringArray = in.readLine();
        in.close();

        /*
         * распозначание и проверка данных
         * */
        String[] arrayStr = stringArray.split(" ");
        if (arrayStr.length > 100) {
            throw new IllegalArgumentException("чисел должно быть не более 100!");
        }
        int[] arrayInt = new int[arrayStr.length];
        System.out.printf("Есть массив: ");
        for (int q = 0; q < arrayStr.length; q++) {
            arrayInt[q] = Integer.valueOf(arrayStr[q]);
            System.out.printf(arrayInt[q] + " ");
        }
        System.out.println();

        /*
         * сумма положительных элементов массива
         * */
        int summPlusElements = 0, maxElement = 0, minElement = 0, positionMaxElement = 0, positionMinElement = 0;

        for (int q = 0; q < arrayInt.length; q++) {
            if (Math.abs(arrayInt[q]) > 100)
                throw new IllegalArgumentException("каждое число в массиве по модулю должно быть не более 100!");
            if (arrayInt[q] > 0) {
                summPlusElements += arrayInt[q];
            }
            if (arrayInt[q] > maxElement) {
                maxElement = arrayInt[q];
                positionMaxElement = q;
            }
            if (arrayInt[q] < minElement) {
                minElement = arrayInt[q];
                positionMinElement = q;
            }
        }
        System.out.println("Сумма положительных элементов = " + summPlusElements);

        /*
         * произведение элементов массива, расположенных между минимальным и максимльным
         * */
        if (positionMinElement > positionMaxElement) {
            int temp = positionMaxElement;
            positionMaxElement = positionMinElement;
            positionMaxElement = temp;
        }
        int resultMultiplicate = 1;
        for (int q = positionMinElement; q <= positionMaxElement; q++) resultMultiplicate *= arrayInt[q];
        if (Math.abs(positionMaxElement - positionMinElement) == 1) resultMultiplicate = 0;
        System.out.println("минимальный элемент находитс на позиции " + positionMinElement + " и имеет значение " + minElement);
        System.out.println("максимальный элемент находитс на позиции " + positionMaxElement + " и имеет значение " + maxElement);
        System.out.println("Произведение чисел между минимальным и максимальным элементом = " + resultMultiplicate);


        /*
         * запись результатов в файл
         * */
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(summPlusElements));
        fw.write(" ");
        fw.write(String.valueOf(resultMultiplicate));
        fw.flush();
        fw.close();
    }

}
