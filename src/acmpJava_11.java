/*
https://acmp.ru
Задание 11
    В нашем зоопарке появился заяц.
    Его поместили в клетку, и чтобы ему не было скучно, директор зоопарка распорядился
    поставить в его клетке лесенку. Теперь наш зайчик может прыгать по лесенке вверх,
    перепрыгивая через ступеньки. Лестница имеет определенное количество ступенек N.
    Заяц может одним прыжком преодолеть не более К ступенек.
    Для разнообразия зайчик пытается каждый раз найти новый путь к вершине лестницы.
    Директору любопытно, сколько различных способов есть у зайца добраться до вершины лестницы
    при заданных значениях K и N. Помогите директору написать программу, которая поможет
    вычислить это количество. Например, если K=3 и N=4, то существуют следующие маршруты:
    1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2, 1+3, 3+1.
    Т.е. при данных значениях у зайца всего 7 различных маршрутов добраться до вершины лестницы.

Входные данные
    В единственной строке входного файла INPUT.TXT записаны два натуральных
    числа K и N (1 ≤ K ≤ N ≤ 300). К - максимальное количество ступенек,
    которое может преодолеть заяц одним прыжком, N – общее число ступенек лестницы.

Выходные данные
    В единственную строку выходного файла OUTPUT.TXT нужно вывести количество возможных
    вариантов различных маршрутов зайца на верхнюю ступеньку лестницы без ведущих нулей..

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_11 {
    static String stringArray;
    static int K = 0;
    static int N = 0;
    static int M = 0;

    public static void main(String[] args) throws IOException {
        loadDataFromFile();
        getAndCheckData();
        M = calculation();
        System.out.println("Получилось что при количестве ступенек " + N + ", и возможности сделать шаг на " + K + " ступеньки, вариантов прохождения лесницы = " + M);
        saveDataAtFile();
    }

    /*
     * чтение данных из файла
     * */
    private static void loadDataFromFile() throws IOException {

        File file = new File("fileDir/INPUT.TXT");
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        stringArray = in.readLine();
        in.close();
    }

    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        String[] arrayStr = stringArray.split(" ");
        if (arrayStr.length > 2) {
            throw new IllegalArgumentException("просим указать только 2 чмсла: K и N");
        }
        K = Integer.parseInt(arrayStr[0]);
        N = Integer.parseInt(arrayStr[1]);
        if (K > N) {
            throw new IllegalArgumentException("K должно быть меньше N ");
        }
    }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {

        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(M));
        fw.flush();
        fw.close();
    }


    /*
     * расчёт
     * */
    private static int calculation() {
        if (K == 1) return 1;                       // если можем ходить только на одну ступеньку, - вариант один
        int[] CK = new int[K];
        CK[0] = 1;                                  //Заполнение начального отрезка из K элементов
        for (int i = 1; i < K; i++)
            CK[i] = 2 * CK[i - 1];

        for (int j = 0; j < (N - K); j++) {         //Цикл для вычисления n-го обобщенного числа Фибоначчи
            M = 0;
            for (int i = 0; i < K; i++)
                M += CK[i];                         //Вычисление очередного числа
            for (int i = 0; i < K - 1; i++)         //Сдвиг отрезка
                CK[i] = CK[i + 1];
            CK[K - 1] = M;
        }
        return M;
    }
}

