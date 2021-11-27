/*
https://acmp.ru
Задание 6
    Совсем недавно Вася занялся программированием и решил реализовать собственную программу для игры в
    шахматы. Но у него возникла проблема определения правильности хода конем, который делает пользователь.
    Т.е. если пользователь вводит значение «C7-D5», то программа должна определить это как правильный ход,
    если же введено «E2-E4», то ход неверный. Также нужно проверить корректность записи ввода: если например,
    введено «D9-N5», то программа должна определить данную запись как ошибочную.
    Помогите ему осуществить эту проверку!

Входные данные
    В единственной строке входного файла INPUT.TXT записан текст хода (непустая строка), который указал
    пользователь.
    Пользователь не может ввести строку, длиннее 5 символов

Выходные данные
    В выходной файл OUTPUT.TXT нужно вывести «YES», если указанный ход конем верный, если же запись корректна
    (в смысле правильности записи координат), но ход невозможен, то нужно вывести «NO».
    Если же координаты не определены или заданы некорректно, то вывести сообщение «ERROR».

*/


import java.io.*;
import java.nio.charset.StandardCharsets;

public class acmpJava_6 {
    private static String resultat = "No";
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
        if (stringArray.length()!=5){
            error();
        }
        String[] arrayStr = stringArray.split("-");
        if (arrayStr.length != 2) {
            resultat = "Error";
        }
        if (!resultat.equals("Error")) {
            check(arrayStr[0]);
            check(arrayStr[1]);
        }
        System.out.println(arrayStr[0] + "   "+ arrayStr[1]);
        System.out.println("resultat = " + resultat);

        char fromLetter = arrayStr[0].charAt(0);
        char fromDigit = arrayStr[0].charAt(1);
        char toLetter = arrayStr[1].charAt(0);
        char toDigit = arrayStr[1].charAt(1);

        System.out.println("fromLetter - toLetter = " + (fromLetter - toLetter));
        System.out.println("fromDigit - toDigit = " + (fromDigit - toDigit));

        if ((Math.abs(fromLetter - toLetter) == 1 && Math.abs(fromDigit - toDigit)==2) ||
                (Math.abs(fromLetter - toLetter) == 2 && Math.abs(fromDigit - toDigit)==1) ||
                (Math.abs(fromLetter - toLetter) == 32 && Math.abs(fromDigit - toDigit)==1) ||
                (Math.abs(fromLetter - toLetter) == 31 && Math.abs(fromDigit - toDigit)==2)
        ){
            resultat = "Yes";
        }
        System.out.println("resultat = " + resultat);

        /*
         * запись результатов в файл
         * */
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(resultat);
        fw.flush();
        fw.close();
    }

    private static void error() {
        resultat = "Error";
        throw new IllegalAccessError("заданы неверные данные");
    }

    private static void check(String value) {
        String[] chessBoard = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8",
                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8",
                "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8",
                "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8",
                "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8",
                "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8"};
        for (int q = 0; q < chessBoard.length; q++) {
            if (value.equalsIgnoreCase(chessBoard[q])) {
                return;
            }
        }
        resultat = "Error";
    }
    }
