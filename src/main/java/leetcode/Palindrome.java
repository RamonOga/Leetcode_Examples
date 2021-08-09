package leetcode;

public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome2(121));

    }

    public static boolean isPalindrome1(int x) {
        String[] arr = String.valueOf(x).split("");
        if (arr.length <= 1) {
            return false;
        }
        int size = arr.length / 2;
        for (int i = 0; i < size; i++) {
            if (!arr[i].equals(arr[arr.length - i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            sb.append(x % 10);
            x /= 10;
        }
        return Integer.parseInt(sb.toString()) == x;
    }
}
