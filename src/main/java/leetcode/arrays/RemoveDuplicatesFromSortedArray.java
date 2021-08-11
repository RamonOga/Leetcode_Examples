package leetcode.arrays;

import javax.crypto.spec.PSource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicatesSlow(int[] nums) {
            Set<Integer> set = new TreeSet<>();
            for (int i : nums) {
                set.add(i);

            }
        return set.size();
    }

    public int removeDuplicatesFast(int[] nums) {
        int count = 0;
        for (int i =  1; i < nums.length; i++) {
            if (nums[i] != nums[count]) {
                count++;
                nums[count] = nums[i];
            }

        }
        return count + 1;
    }
}
