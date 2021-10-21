public class GenericMathods {
    public static void main(String[] args) {
        Reentrantcy flag = new Reentrantcy();
        for (int q = 0; q < 23; q = q + 1) {
            System.out.println("q=" + q);
            flag.doFirst();
            flag.doSecond();
            flag.doAll();
        }


    }

    static class Reentrantcy {
        public void doAll() {
            doFirst();
            doSecond();
        }

        public void doFirst() {
            System.out.println("First operation is successful.");
        }

        public void doSecond() {
            System.out.println("Second operation is successful.");
        }
    }
}