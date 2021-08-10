package leetcode.arrays;

public class PrefixStringArray {
    public static String getPrefix(String[] array) {
        int minSize = array[0].length();
        for (int i = 1; i < array.length; i++) {
            minSize = Math.min(minSize, array[i].length());
        }

        StringBuilder rsl = new StringBuilder("");
        for (int i = 0; i != minSize; i++) {
            char tmp = array[0].charAt(i);
            for (String s : array) {
                if (s.charAt(i) != tmp) {
                    return rsl.toString();
                }
            }
            rsl.append(array[0].charAt(i));
        }
        return rsl.toString();
    }
}
