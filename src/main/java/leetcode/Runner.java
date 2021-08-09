package leetcode;

public class Runner {
    public static void main(String[] args) {
        String[] s = new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(s));
    }


    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder rsl = new StringBuilder("");
        int minSize = strs[0].length();
        for (String s : strs) {
            if (minSize > s.length()) {
                minSize = s.length();
            }
        }
        if (minSize == 0) {
            return "";
        }

        for (int i = 0; i < minSize; i++) {
            char tmp = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != tmp) {
                    return rsl.toString();
                }
            }
            rsl.append(tmp);
        }
        return rsl.toString();

    }
}
