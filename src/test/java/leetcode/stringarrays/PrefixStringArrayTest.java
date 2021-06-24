package leetcode.stringarrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrefixStringArrayTest {

    @Test
    public void PrefixStringArrayTest() {
        String[] trueArray = new String[]{"java", "javascript", "javabest", "javase"};
        String[] falseArray = new String[]{"java", "javascript", "JS", "C++"};
        String[] arrayWithEmptyUnit = new String[]{"", "javascript", "JS", "C++"};
        String expectTrue = "java";
        String expectFalse = "";
        assertEquals(expectTrue, PrefixStringArray.getPrefix(trueArray));
        assertEquals(expectFalse, PrefixStringArray.getPrefix(falseArray));
        assertEquals(expectFalse, PrefixStringArray.getPrefix(arrayWithEmptyUnit));
    }

}