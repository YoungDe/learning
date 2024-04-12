import java.util.*;

public class ss {


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

        char c = 'A';

        List<Integer> ans = new ArrayList<>();


        if (c  == 'a'){

        }

        int maxCount = 0;
        Integer number = null;

        for (Map.Entry<Integer, Integer> entry : numberCountMap.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                number = entry.getKey();
            }
        }
        System.out.println("the max count numeber is " + number + " the count is " + maxCount);
    }

}
