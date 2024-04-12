import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FindMaxCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (sc.hasNext() && list.size() != 10) {
            int num = sc.nextInt();
            list.add(num);
        }
        list.sort(Comparator.naturalOrder());
        sc.close();
        Map<Integer, Integer> numberCountMap = new HashMap<>();
        for (int i : list) {
            numberCountMap.merge(i, 1, Integer::sum);
        }
        List<Integer> values = new ArrayList<>(numberCountMap.values());
        Set<Integer> keySet = numberCountMap.keySet();
        int maxCountIndex = values.indexOf(Collections.max(values));
        Integer[] arr = keySet.toArray(new Integer[0]);
        Integer num = arr[maxCountIndex];

        System.out.println("the max count numeber is " + num + " the count is " + values.get(maxCountIndex));
    }

}
