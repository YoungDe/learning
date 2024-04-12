import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class sg {

    public static long printSubArr(int[] nums) {
        long sum = 0;
        int n = nums.length;
        int[] ans = new int[3];
        List<Integer> ne = new ArrayList<>();
        for (int i : nums) {
            ne.add(i * i);
        }

        Integer max = Collections.max(ne);
        Integer min = Collections.min(ne);
        ans[0] = ne.indexOf(max);
        ne.remove(max);
        max = Collections.max(ne);
        ans[1] = ne.indexOf(max);

        ans[2] = ne.indexOf(min);

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] temp = new int[j - i + 1];
                int left = i;
                int k = 0;
                while (k < temp.length) {
                    temp[k++] = nums[left++];
                }
                System.out.println(Arrays.toString(temp));
            }

        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(TimeUnit.MINUTES.toMillis(15));
    }
}
