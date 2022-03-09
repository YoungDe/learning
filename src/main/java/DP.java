public class DP {

    public static void main(String[] args) {
        int[] prices = new int[] { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
        System.out.println(cutRod(prices, 10));
    }

    public static int cutRod(int[] price, int n) {
        int[] r = new int[n + 1];
        int[] s = new int[n + 1];
        r[0] = 0;
        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                if (q < price[j] + r[i - j]) {
                    q = price[j] + r[i - j];
                    s[i] = j;
                }
            }
            r[i] = q;
        }
        return r[n];
    }

}
