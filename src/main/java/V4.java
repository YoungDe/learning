import java.util.Stack;

public class V4 {

    public static void main(String[] args) {
        // [a+e, a+f, a+g, b+e, b+f, b+g, c+e, c+f, c+g]
        // String keyword = "(a/b/c)+(e/f/g)";

        // [a+e, b+e, c+e]
        String keyword = "(a/b/c)+e";

        // [a+d+e+h, a+d+e+i, a+d+f+h, a+d+f+i, a+d+g+h, a+d+g+i, b+d+e+h, b+d+e+i,
        // b+d+f+h, b+d+f+i, b+d+g+h, b+d+g+i, c+d+e+h, c+d+e+i, c+d+f+h, c+d+f+i,
        // c+d+g+h, c+d+g+i]
        // String keyword = "(a/b/c)+d+(e/f/g)+(h/i)";

        // [a, b, c]
        // String keyword = "a/b/c";

        // [a+b+e, a+c+e, a+d+e]
        // String keyword = "a+(b/c/d)+e";

        // [a+b+c]
        // String keyword = "a + b +c";

        // [a+b+d, s+d, c+d]
        // String keyword = "(a+b/s/c)+d";

        // [a+b+d, s+d, c+d]
        // String keyword = "((a+b/s)/c)+d";

        // [s, a+b, c+d]
        // String keyword = "(a+b/s)/c+d";

        // [a, b, c]
        // String keyword = "(a/b/c)";

        // [c+d+e, b+d+e, a+d+e]
        // String keyword = "(d+(a/b/c))+e";

        // [a+l+e, b+j+e, c+j+e, c+k+e, a+j+e, b+k+e, c+l+e, a+k+e, b+l+e]
        // String keyword = "(a+(j/k/l)/b+(j/k/l)/c+(j/k/l))+e";

        // [a+l+e, b+j+e, c+j+e, c+k+e, a+j+e, b+k+e, c+l+e, a+k+e, b+l+e]
        // String keyword = "((a/b/c)+(j/k/l))+e";

        // [k+e, j+e, l+c+e, l+a+e, l+b+e]
        // String keyword = "(j/k/l+(a/b/c))+e";

        // String keyword = "((f/h)+l+(a/b/c))+e";

        String withoutWhiteSpace = keyword.replaceAll("\\s", "");
        for (int i = 0; i < withoutWhiteSpace.length(); i++) {
            char c = withoutWhiteSpace.charAt(i);
            if ('(' == c) {
                int begin = i + 1;
                String tmp = withoutWhiteSpace.substring(begin);
                int closeIndex = findBracketClose(tmp) + begin;
                String betweenBracket = withoutWhiteSpace.substring(begin, closeIndex);
                if (closeIndex + 1 < withoutWhiteSpace.length() && withoutWhiteSpace.charAt(closeIndex + 1) == '+') {
                    // findAddEmenent();
                }

            }
            if ('/' == c) {

            }

        }

    }

    private static int findBracketClose(String tmp) {
        Stack<Character> cStack = new Stack<>();
        for (int i = 0; i < tmp.length(); i++) {
            Character c = tmp.charAt(i);
            if ('(' == c) {
                cStack.push(c);
            }
            if (')' == c) {
                if (cStack.isEmpty()) {
                    return i;
                } else {
                    cStack.pop();
                }
            }
        }
        return -1;
    }
}
