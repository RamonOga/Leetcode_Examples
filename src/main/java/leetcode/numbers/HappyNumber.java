package leetcode.numbers;

public class HappyNumber {

        public static void main(String[] args) throws InterruptedException {
            int n = 203;
            System.out.println("start with " + n);
            isHappy(n);
        }


        public static boolean isHappy(int n) throws InterruptedException {
            while (n != 1) {
                n = get(n);
                if (n == -1) {
                    return false;
                }
            }
            return true;
        }

        public static int get(int number) throws InterruptedException {
            System.out.println("get with " + number);
            int rsl = 0;
            while (number != 0) {
                rsl += Math.pow((number % 10), 2);
                number /= 10;
            }
            if (rsl == 20) {
                return -1;
            }
            Thread.sleep(500);
            System.out.println(rsl);
            return rsl;
        }
    }
