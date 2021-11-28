/*
https://acmp.ru
Задание 7
    Главный вождь племени Абба не умеет считать.
    В обмен на одну из его земель вождь другого племени предложил ему выбрать одну из трех куч с золотыми
    монетами. Но вождю племени Абба хочется получить наибольшее количество золотых монет.
    Помогите вождю сделать правильный выбор!

Входные данные
    В первой строке входного файла INPUT.TXT записаны три натуральных числа через пробел.
    Каждое из чисел не превышает 10^100.
    Числа записаны без ведущих нулей.

Выходные данные
    В выходной файл OUTPUT.TXT нужно вывести одно целое число — максимальное количество монет,
    которые может взять вождь

*/


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;

public class acmpJava_7 {
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
        if (arrayStr.length != 3) {
            throw new IllegalArgumentException("должно быть 3 кучи");
        }
        Double pile1 = Double.valueOf(arrayStr[0]);
        Double pile2 = Double.valueOf(arrayStr[1]);
        Double pile3 = Double.valueOf(arrayStr[2]);

        if (pile1 > 1e101 || pile2 > 1e101 || pile3 > 1e101 || pile1 < 1 || pile2 < 1 || pile3 < 1) {
            throw new IllegalArgumentException("в куче должно быть до 10^100 монет");
        }

            String resultat = String.valueOf((Math.max(Math.max(pile1,pile2),pile3)));

       System.out.println("наибольшее количество монет: " + resultat);

        /*
         * запись результатов в файл
         * */
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(resultat);
        fw.flush();
        fw.close();
    }

}
