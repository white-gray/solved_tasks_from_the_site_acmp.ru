/*
https://acmp.ru
Задание 19
    На шахматной доске 8х8 расположены три фигуры: ферзь, ладья и конь.
    Требуется определить количество пустых полей доски, которые находятся под боем.
    Например, в рассмотренной справа ситуации будем считать, что ферзь бьет D5 через ладью.

Входные данные
   В единственной строке входного файла INPUT.TXT записаны через пробел координаты расположения
   трех фигур: ферзя, ладьи и коня соответственно.
   Каждая координата состоит из одного английского символа (от A до H) и одной цифры (от 1 до 8).

Выходные данные
   В выходной файл OUTPUT.TXT нужно вывести количество пустых полей, которые бьют указанные во
   входных данных фигуры.

*/


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class acmpJava_19 {
    static String chessPoints;
    static String[] figures;
    static String[] queen;
    static String[] ladya;
    static String[] horse;
    static ArrayList chessBoard_x = new ArrayList(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
    static ArrayList chessBoard_y = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
    static Map<String, Integer> aceX = new HashMap<>();
    static Map<Integer, String> aceXover = new HashMap<>();
    static int[][] setPoints = new int[8][8];


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
        chessPoints = String.valueOf(in.readLine());
        in.close();
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        figures = chessPoints.split(" ");
        if (figures.length != 3) {
            throw new IllegalCallerException("Указать 3 клетки шахматной доски. Позиции: ферзя, ладьи, коня");
        }
        queen = figures[0].split("");
        ladya = figures[1].split("");
        horse = figures[2].split("");
        if (!chessBoard_x.contains(queen[0]) || !chessBoard_x.contains(ladya[0]) || !chessBoard_x.contains(horse[0]) || !chessBoard_y.contains(queen[1]) || !chessBoard_y.contains(ladya[1]) || !chessBoard_y.contains(horse[1])) {
            throw new IllegalArgumentException("надо указать адреса ячеек на шахматной доске");
        }
        aceX.put("A", 1);
        aceX.put("B", 2);
        aceX.put("C", 3);
        aceX.put("D", 4);
        aceX.put("E", 5);
        aceX.put("F", 6);
        aceX.put("G", 7);
        aceX.put("H", 8);
        aceXover.put(1, "A");
        aceXover.put(2, "B");
        aceXover.put(3, "C");
        aceXover.put(4, "D");
        aceXover.put(5, "E");
        aceXover.put(6, "F");
        aceXover.put(7, "G");
        aceXover.put(8, "H");
    }


    /*
     * выполнение задачи
     * */
    private static void toDo() {
        getForQueen();
        getForLadya(ladya[0], ladya[1]);
        getForHorse();
    }

    private static void getForHorse() {
        String pointX = horse[0];
        Integer pointY = Integer.valueOf(horse[1]);
//        проерка Г влево-вниз
        runOfHorse((aceX.get(pointX) - 2), pointY - 1);
//        проерка Г влево-вверх
        runOfHorse((aceX.get(pointX) - 2), pointY + 1);
//        проерка Г вверх-влево
        runOfHorse((aceX.get(pointX) - 1), pointY + 2);
//        проерка Г вверх-вправо
        runOfHorse((aceX.get(pointX) + 1), pointY + 2);
//        проерка Г вправо-вверх
        runOfHorse((aceX.get(pointX) + 2), pointY + 1);
//        проерка Г вправо-вниз
        runOfHorse((aceX.get(pointX) + 2), pointY - 1);
//        проерка Г вниз-вправо
        runOfHorse((aceX.get(pointX) + 1), pointY - 2);
//        проерка Г вниз-влево
        runOfHorse((aceX.get(pointX) - 1), pointY - 2);
    }

    private static void runOfHorse(int x, int y) {
        boolean go = true;
        if ((x < 1) || (x > 8) || (y < 1) || (y > 8)) return;
        if ((aceXover.get(x).equals(ladya[0]) && String.valueOf(y).equals(ladya[1])) || (aceXover.get(x).equals(horse[0]) && String.valueOf(y).equals(horse[1])) || (aceXover.get(x).equals(queen[0]) && String.valueOf(y).equals(queen[1]))) {
            return;
        }
        setPoints[x - 1][y - 1] = 1;
    }

    private static Integer resultat() {
        Integer resultat = 0;
        for (int sp1 = 0; sp1 < 8; sp1++) {
            for (int sp2 = 0; sp2 < 8; sp2++) {
                if (setPoints[sp1][sp2] == 1) resultat++;
            }
        }
        return resultat;
    }

    private static void getForLadya(String x, String y) {
        String pointX = x;
        Integer pointY = Integer.valueOf(y);
//        проерка вниз
        setPosition((aceX.get(pointX)), pointY - 1, 0, -1);
//        проерка лево
        setPosition((aceX.get(pointX) - 1), pointY, -1, 0);
//        проерка вверх
        setPosition((aceX.get(pointX)), pointY + 1, 0, 1);
//        проерка диагонали вниз-право
        setPosition((aceX.get(pointX) + 1), pointY, 1, 0);
    }

    private static void getForQueen() {
        String pointX = queen[0];
        Integer pointY = Integer.valueOf(queen[1]);
//        проерка диагонали вниз-лево
        setPosition((aceX.get(pointX) - 1), pointY - 1, -1, -1);
//        проерка диагонали вверх-лево
        setPosition((aceX.get(pointX) - 1), pointY + 1, -1, 1);
//        проерка диагонали вверх-право
        setPosition((aceX.get(pointX) + 1), pointY + 1, 1, 1);
//        проерка диагонали вниз-право
        setPosition((aceX.get(pointX) + 1), pointY - 1, 1, -1);
        getForLadya(queen[0], queen[1]);
    }

    private static void setPosition(Integer x, Integer y, Integer goX, Integer goY) {
        boolean go = true;
        if ((x < 1) || (x > 8) || (y < 1) || (y > 8)) go = false;
        while (go) {
            if ((aceXover.get(x).equals(ladya[0]) && String.valueOf(y).equals(ladya[1])) || (aceXover.get(x).equals(horse[0]) && String.valueOf(y).equals(horse[1])) || (aceXover.get(x).equals(queen[0]) && String.valueOf(y).equals(queen[1]))) {
                return;
            }
            setPoints[x - 1][y - 1] = 1;
            x += goX;
            y += goY;
            if ((x < 1) || (x > 8) || (y < 1) || (y > 8)) go = false;
        }
    }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile() throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(resultat()));
        fw.flush();
        fw.close();
    }
}

