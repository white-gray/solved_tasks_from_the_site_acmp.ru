/*
https://acmp.ru
Задание 11
    Всем известно, что дачники – народ странный, почти такой же, как и программисты. Строят они свои дачи
    непонятно где, да и выращивают там непонятно что и непонятно зачем. А уж как они туда добираются, это
    другая история: кто на автобусе, кто на электричке, кто на автомобиле, ну а кто-то вовсе пешком ходит
    от дома и до самого участка. Так что не стоит удивляться, если вдруг Вы узнаете, что некое садоводческое
    товарищество располагается на острове, а дачники добираются до него самолетом. Да еще и на этом острове
    может не быть посадочной полосы, так что высадиться на остров можно, только прыгая с парашютом (мы уж не
    рассматриваем то, как они возвращаются с дач домой). Рассмотрим этот уникальный случай. Пилот всегда
    старается осуществить высадку парашютистов таким образом, чтобы дачники приземлялись как можно ближе к
    своим прямоугольным участкам. Пилоту интересно знать: сколько дачников приземлится на свои участки?
    Помогите ему решить эту задачу!

Входные данные
    В первой строке входного файла INPUT.TXT записано натуральное число N (1 ≤ N ≤ 1000) – количество
    дачников, далее идут N строк, в каждой из которых описаны координаты каждого дачника и его участка:
        X Y X1 Y1 X2 Y2 X3 Y3 X4 Y4
            где
                (X,Y) – координаты приземления парашютиста
                (X1, Y1, X2, Y2, X3, Y3, X4,Y4) – координаты прямоугольного участка на плоскости,
                                                    указанные последовательно.
            Все координаты – целые числа, не превышающие 50000 по абсолютной величине

Выходные данные
    В выходной файл OUTPUT.TXT нужно вывести количество дачников, приземлившихся на свой участок.
    Попадание на границу участка считается попаданием на участок..

_______________________________________________________________________________________________

Решение:
    Ведётся расчёт площадей 4х треугольников, у каждого из которых одна сторона является стороной
    прямоугольника (используются все стороны); а третий угол, - рассматриваемой точкой приземления дачника.
    И в итоге, если сумма площадей этих треугольников совпадает с площадью прямоугольника, - значит
    дачник приземлился на свой участок! Если не совпадает, - значит промазал!

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_12 {
    static int howToSpaces;
    static String data = new String();
    static String[] pointAndRectangle = new String[howToSpaces];
    static int landedOK = 0;

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
        howToSpaces = Integer.valueOf(in.readLine());
        if (1 > howToSpaces || howToSpaces > 1000) {
            throw new IllegalArgumentException("\tЧисло учестков от 1 до 1000");
        }
        String temp;
        while ((temp = in.readLine()) != null) {
            data += temp;
            data += "\n";
        }
        in.close();
    }

    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        pointAndRectangle = data.split("\n");
    }


    private static double cutNumber(double value) {
        double scale = Math.pow(10, 2);
        value = Math.rint(value * scale) / scale;
//        System.out.println("value = " + value);
        return value;
    }

    /*
     * выполнение задачи
     * */
    private static void toDo() {
        for (int q = 0; q < howToSpaces; q++) {
//            System.out.println("\nq = " + q);
            String[] arrayLine = pointAndRectangle[q].split(" ");
            int x = Integer.valueOf(arrayLine[0]);
            testValue(x);
            int y = Integer.valueOf(arrayLine[1]);
            testValue(x);
            int x1 = Integer.valueOf(arrayLine[2]);
            testValue(x);
            int y1 = Integer.valueOf(arrayLine[3]);
            testValue(x);
            int x2 = Integer.valueOf(arrayLine[4]);
            testValue(x);
            int y2 = Integer.valueOf(arrayLine[5]);
            testValue(x);
            int x3 = Integer.valueOf(arrayLine[6]);
            testValue(x);
            int y3 = Integer.valueOf(arrayLine[7]);
            testValue(x);
            int x4 = Integer.valueOf(arrayLine[8]);
            testValue(x);
            int y4 = Integer.valueOf(arrayLine[9]);
            testValue(x);
            double squareTriangle1 = cutNumber(squareTriangle(x, y, x1, y1, x2, y2));
//            System.out.println("squareTriangle1 = " + squareTriangle1);
            double squareTriangle2 = cutNumber(squareTriangle(x, y, x2, y2, x3, y3));
//            System.out.println("squareTriangle2 = " + squareTriangle2);
            double squareTriangle3 = cutNumber(squareTriangle(x, y, x3, y3, x4, y4));
//            System.out.println("squareTriangle3 = " + squareTriangle3);
            double squareTriangle4 = cutNumber(squareTriangle(x, y, x4, y4, x1, y1));
//            System.out.println("squareTriangle4 = " + squareTriangle4);
            double squareRectangle = cutNumber(getSquareRectangle(x1, y1, x2, y2, x3, y3));
//            System.out.println("squareRectangle = " + squareRectangle);
            if ((squareTriangle1 + squareTriangle2 + squareTriangle3 + squareTriangle4) == squareRectangle) {
                landedOK +=1;
            }
        }
    }

    private static void testValue(int value) {
        if (value < -50000 || value > 50000) {
            throw new IllegalArgumentException("\tКоординаты от -50000 до 50000");
        }
    }

    private static double getSquareRectangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        double side1 = (double) Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
        double side2 = (double) Math.sqrt(Math.pow(Math.abs(x2 - x3), 2) + Math.pow(Math.abs(y2 - y3), 2));
        double squareRectangle = side1 * side2;
        return squareRectangle;
    }

    private static double squareTriangle(int x, int y, int x1, int y1, int x2, int y2) {
        double sectionLenght = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
//        System.out.println("\tsectionLenght = " + sectionLenght);
        double fromEndSectionToPoing1 = Math.sqrt(Math.pow(Math.abs(x - x1), 2) + Math.pow(Math.abs(y - y1), 2));
//        System.out.println("\tfromEndSectionToPoing1 = " + fromEndSectionToPoing1);
        double fromEndSectionToPoing2 = Math.sqrt(Math.pow(Math.abs(x - x2), 2) + Math.pow(Math.abs(y - y2), 2));
//        System.out.println("\tfromEndSectionToPoing2 = " + fromEndSectionToPoing2);
        double halfPerimeter = (sectionLenght + fromEndSectionToPoing1 + fromEndSectionToPoing2) / 2;
//        System.out.println("\thalfPerimeter = " + halfPerimeter);
        double squareTriangle = Math.sqrt(halfPerimeter * (halfPerimeter - sectionLenght) * (halfPerimeter - fromEndSectionToPoing1) * (halfPerimeter - fromEndSectionToPoing2));
//        System.out.println("squareTriangle = " + squareTriangle);
        return squareTriangle;
    }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(landedOK));
        fw.flush();
        fw.close();
    }


}

