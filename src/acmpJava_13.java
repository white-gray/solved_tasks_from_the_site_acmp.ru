/*
https://acmp.ru
Задание 13
    Петя и Вася часто играют в различные логические игры. Недавно Петя поведал Васе о новой игре «Быки и
    коровы» и теперь они играют в эту игру сутками. Суть игры очень проста: Петя загадывает четырехзначное
    число, состоящее из различных цифр. Вася отгадывает задуманное Петей число, перебирая возможные
    варианты. Каждый раз Вася предлагает вариант своего четырёхзначного числа, состоящего из различных
    цифр, а Петя делает Васе подсказку: сообщает количество быков и коров, после чего Вася с учетом
    подсказки продолжает отгадывание числа до тех пор, пока не отгадает. Быки – это количество цифр в
    предложенном Васей числе, совпадающих по значению и стоящих в правильной позиции в задуманном Петей
    числе. Коровы – количество цифр, совпадающих по значению, но находящихся в неверной позиции. Например,
    если Петя задумал число 5671, а Вася предложил вариант 7251, то число быков равно 1 (только цифра 1 на
    своем месте), а число коров равно 2 (только цифры 7 и 5 не на своих местах). Петя силен в математике,
    но даже он может ошибаться. Помогите Пете написать программу, которая бы по загаданному Петей и
    предложенному Васей числам сообщала количество быков и коров.

Входные данные
   В единственной строке входного файла INPUT.TXT записано два четырехзначных натуральных числа A и B
   через пробел, где А – загаданное Петей число, а В – предложенный Васей вариант.

Выходные данные
   В выходной файл OUTPUT.TXT нужно вывести два целых числа через пробел — количество быков и коров.

_______________________________________________________________________________________________

Решение:
    Ведётся расчёт площадей 4х треугольников, у каждого из которых одна сторона является стороной
    прямоугольника (используются все стороны); а третий угол, - рассматриваемой точкой приземления дачника.
    И в итоге, если сумма площадей этих треугольников совпадает с площадью прямоугольника, - значит
    дачник приземлился на свой участок! Если не совпадает, - значит промазал!

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_13 {
    static String forComparison;
    static String exercise;
    static String [] exerciseArr;
    static String answer;
    static String [] answerArr;
    static int bull = 0, cow = 0;



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
        String [] dataString =  forComparison.split(" ");
        if (dataString.length != 2) {
            throw new IllegalArgumentException("Должно быть только 2 числа!");
        }
        exercise = dataString[0];
        answer = dataString[1];
        exerciseArr = exercise.split("");
        answerArr = answer.split("");
        if (exerciseArr.length != answerArr.length || exerciseArr.length !=4) {
            throw new IllegalArgumentException("Числа должны быть 4хзначными!");
        }}

    /*
     * выполнение задачи
     * */
        private static void toDo() {
            for (int q = 0; q < exerciseArr.length; q++) {
                if (exerciseArr[q].equals(answerArr[q])) {
                    bull ++;
                    exerciseArr[q] = "-2";
                    answerArr[q] = "-1";
                }
            }
            for (String q : exerciseArr) {
                for (String w : answerArr) {
                    if (q.equals(w)) {
                        cow++;
                    }
                }
            }
        }




    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(bull));
        fw.write(" ");
        fw.write(String.valueOf(cow));
        fw.flush();
        fw.close();
    }


}

