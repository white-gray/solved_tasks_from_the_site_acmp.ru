/*
https://acmp.ru
Задание 31
    Перестановкой P[1..n] размера n называется набор чисел от 1 до n, расположенных в
    определенном порядке. При этом в нем должно присутствовать ровно один раз каждое из этих
    чисел. Примером перестановок являются 1,3,4,5,2 (для n=5) и 3,2,1 (для n=3), а,
    например, 1,2,3,4,5,1 перестановкой не является, так как число 1 встречается два раза.

    Число i называется неподвижной точкой для перестановки P, если P[i] = i. Например, в
    перестановке 1,3,4,2,5 ровно две неподвижных точки: 1 и 5, а перестановка 4,3,2,1 не имеет
    неподвижных точек.

    Даны два числа: n и k. Найдите количество перестановок размера n с ровно k неподвижными
    точками.

Входные данные:
    Входной файл INPUT.TXT содержит два целых числа n (1 ≤ n ≤ 9) и k (0 ≤ k ≤ n).

Выходные данные
   В выходной файл OUTPUT.TXT выведите ответ на задачу.


Решено с помощью комбинаторики http://math.hashcode.ru/questions/69941/%D0%BA%D0%BE%D0%BC%D0%B1%D0%B8%D0%BD%D0%B0%D1%82%D0%BE%D1%80%D0%B8%D0%BA%D0%B0-%D0%BD%D0%B5%D0%BF%D0%BE%D0%B4%D0%B2%D0%B8%D0%B6%D0%BD%D1%8B%D0%B5-%D1%82%D0%BE%D1%87%D0%BA%D0%B8-%D0%B2-%D0%BF%D0%B5%D1%80%D0%B5%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0%D1%85
*/


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class acmpJava_31 {


    private static int[] values;
    private static int result;

    public static void main(String[] args) throws IOException {
        getAndCheckData(loadDataFromFile());
        toDo();
        saveDataAtFile();
    }


    /*
     * чтение данных из файла
     * */
    private static String loadDataFromFile() throws IOException {
        File file = new File("fileDir/INPUT.TXT");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String data = String.valueOf(in.readLine());
        in.close();
        return data;
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData(String data) {
        values = Arrays.stream(data.split(" ")).mapToInt(Integer::parseInt).toArray();
        if ((values.length != 2)) {
            throw new IllegalArgumentException("введите 2 значения");
        }
        if ((values[0] <= 1 || values[0] > 9)) {
            throw new IllegalArgumentException("первое значение должно быть больше 1, и меньше 10");
        }
        if ((values[1] < 0 || values[1] >= values[0])) {
            throw new IllegalArgumentException("второе знаяение должно быть от 0, и меньше первого значения");
        }
    }


    /*
     * выполнение задачи
     * */
    private static void toDo() {
        int[] a = new int[10];
        int all = 1;
        int n = values[0];
        int k = values[1];

        int c = factorial(n)/factorial(k);
        int z=1;
        for (int i = 0; i <= n - k; i++) {
            result = result+z*c/factorial((i));
            z*=-1;
        }
        System.out.println("result = " + result);
    }

    static public int factorial(int n) {
        if (n == 0) return 1;
        return (n == 1) ? 1 : n * factorial(n - 1);
    }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        String toWrite = String.valueOf(result);
        fw.write(toWrite);
        fw.flush();
        fw.close();
    }

}

