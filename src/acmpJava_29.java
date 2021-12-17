/*
https://acmp.ru
Задание 29
    Вы можете вспомнить хоть одного своего знакомого до двадцатилетнего возраста, который в
    детстве не играл в компьютерные игры?
    Если да, то может быть вы и сами не знакомы с этим развлечением? Впрочем, трудностей
    при решении этой задачи это создать не должно.

    Во многих старых играх с двумерной графикой можно столкнуться с подобной ситуацией.
    Какой-нибудь герой прыгает по платформам (или островкам), которые висят в воздухе.
    Он должен перебраться от одного края экрана до другого. При этом при прыжке с одной
    платформы на соседнюю, у героя уходит |y2-y1| единиц энергии, где y1 и y2 – высоты, на
    которых расположены эти платформы. Кроме того, у героя есть суперприем, который позволяет
    перескочить через платформу, но на это затрачивается 3*|y3-y1| единиц энергии. Конечно же,
    энергию следует расходовать максимально экономно.

    Предположим, что вам известны координаты всех платформ в порядке от левого края до правого.
    Сможете ли вы найти, какое минимальное количество энергии потребуется герою, чтобы добраться
    с первой платформы до последней?
Входнst данные:
    В первой строке входного файла INPUT.TXT записано количество платформ n (1 ≤ n ≤ 30000).
    Вторая строка содержит n натуральных чисел, не превосходящих 30000 – высоты, на которых
    располагаются платформы.
Выходные данные
   В выходной файл OUTPUT.TXT запишите единственное число – минимальное количество энергии, которую должен потратить игрок на преодоление платформ (конечно же в предположении, что cheat-коды использовать нельзя).
   число способов.
*/


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class acmpJava_29 {
    private static int howManyPlanks;
    private static String planksString;
    private static int[] planksArray;


    public static void main(String[] args) throws IOException {
        loadDataFromFile();
        getAndCheckData();
        saveDataAtFile(toDo());
    }


    /*
     * чтение данных из файла
     * */
    private static void loadDataFromFile() throws IOException {
        File file = new File("fileDir/INPUT.TXT");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        howManyPlanks = Integer.valueOf(in.readLine());
        if (howManyPlanks < 1 || howManyPlanks > 30000) {
            throw new IllegalArgumentException("платформ должно быть не более 30000");
        }
        planksString = String.valueOf(in.readLine());
        in.close();
    }


    /*
     * распозначание и проверка данных
     * */
    private static void getAndCheckData() {
        planksArray = Arrays.stream(planksString.split(" ")).mapToInt(Integer::parseInt).toArray();
        if (planksArray.length != howManyPlanks) {
            throw new IllegalArgumentException("платформ должно быть указанное количество!");
        }
    }


    /*
     * выполнение задачи
     * */
    private static int toDo() {
        int energy = 0;
        for (int jump = 0; jump < (howManyPlanks-1); jump++) {
            if (jump <= (howManyPlanks - 3)) {
                if (Math.abs(3 * (planksArray[jump + 2] - planksArray[jump]))
                        < (Math.abs(planksArray[jump + 1] - planksArray[jump])
                        + (Math.abs(planksArray[jump + 2] - planksArray[jump + 1]))))
                       {
                    energy += Math.abs(3 * (planksArray[jump + 2] - planksArray[jump]));
                    jump++;
                    continue;
                }
            }
            energy += Math.abs(planksArray[jump+1]-planksArray[jump]);
        }
        System.out.println("min energy = " + energy);
        return energy;
    }

    /*
     * запись результатов в файл
     * */
    private static void saveDataAtFile(int result) throws IOException {
        FileWriter fw = new FileWriter("fileDir/OUTPUT.TXT");
        fw.write(String.valueOf(result));
        fw.flush();
        fw.close();
    }
}