/*
https://acmp.ru
Задание 27
    Известный художник решил написать новый шедевр.
    После многих дней усердной работы он захотел исследовать свое творение.
    Художник вспомнил, что картина писалась следующим образом: сначала был взят белый холст,
    имеющий форму прямоугольника шириной w и высотой h. Затем художник нарисовал на этом холсте
    n прямоугольников со сторонами, параллельными сторонам холста и вершинами, расположенными в
    целочисленных координатах.
    Помогите художнику определить площадь незакрашенной части холста.

Входные данные
   Первая строка входного файла INPUT.TXT содержит два натуральных числа w и h (1 ≤ w, h ≤ 100).
     Следующие n (0 ≤ n ≤ 5000) строк содержат информацию о всех прямоугольниках.
      Каждая строка описывает один прямоугольник в виде четырех чисел x1, y1, x2, y2 ,
       где (x1, y1) и (x2, y2) – координаты левого верхнего и правого нижнего угла
        прямоугольника соответственно.

Выходные данные
   Выведите в выходной файл OUTPUT.TXT одно целое число – площадь незакрашенной части холста


Решение:
    Площадь зарисованного считается через TreeSet (где не хранятся дубликаты)
*/


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class acmpJava_27 {
    private static String pageString;
    private static int h;
    private static int w;
    private static String squaresString;
    private static String[][] squareArrays;
    private static int squareEmpty;


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
        pageString = String.valueOf(in.readLine());
        String s;
        squaresString = in.readLine();
        while ((s = in.readLine()) != null) {
            squaresString += "\n";
            squaresString += s;
        }
        in.close();
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        String[] pageArray;
        pageArray = pageString.split(" ");
        if (pageArray.length != 2) {
            throw new IllegalArgumentException("надо прописать два значения");
        }
        w = Integer.parseInt(pageArray[0]);
        h = Integer.parseInt(pageArray[1]);
        if (w < 1 || w > 100 || h < 1 || h > 100) {
            throw new IllegalArgumentException("значение ширны и высоты квадрата долюно быть от 1 до 100");
        }
        String[] squareArray;
        squareArray = squaresString.split("\n");
        if (squareArray.length > 5000) throw new IllegalArgumentException("зон закраски должно быть не более 5000");
        squareArrays = new String[squareArray.length][];
        for (int q = 0; q < squareArray.length; q++) {
            squareArrays[q] = squareArray[q].split(" ");
            if (squareArrays[q].length != 4) {
                throw new IllegalArgumentException("каждому квадраиу прописываем по 2 координати каждого из двух углов");
            }
        }

    }


    /*
     * выполнение задачи
     * */
    private static void toDo() {
        Set squarePaint = new TreeSet();
        for (int q = 0; q < squareArrays.length; q++) {
            for (int w = 0; w < squareArrays[q].length; w++) {
                for (int e = Integer.parseInt(squareArrays[q][0]); e < Integer.parseInt(squareArrays[q][2]); e++) {
                    for (int r = Integer.parseInt(squareArrays[q][1]); r < Integer.parseInt(squareArrays[q][3]); r++) {
                        squarePaint.add(e + " " + r);
                    }
                }
            }
        }
        int squarePainted = squarePaint.size();
System.out.println("squarePainted = " + squarePainted);
        int squareFull = h*w;
System.out.println("squareFull = " + squareFull);
        squareEmpty = squareFull-squarePainted;
System.out.println("squareEmpty = " + squareEmpty);
    }


    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(squareEmpty));
        fw.flush();
        fw.close();
    }
}

