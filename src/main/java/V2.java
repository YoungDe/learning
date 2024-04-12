import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class V2 {

    public static void main(String[] args) {
        // [a+e, a+f, a+g, b+e, b+f, b+g, c+e, c+f, c+g]
        // String keyword = "(a/b/c)+(e/f/g)";

        // [a+e, b+e, c+e]
        // String keyword = "(a/b/c)+e";

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

        // [h+l+c+e, h+l+b+e, f+l+a+e, h+l+a+e, f+l+b+e, f+l+c+e]
        // String keyword = "((f/h)+l+(a/b/c))+e";

        // [c+d+g, c+e+g, b+e+g, c+f+g, b+f+g, a+f+g, a+e+g, b+d+g, a+d+g]
        // String keyword = "((a/b/c)+(d/e/f))+g";


        String keyword = "(a+b+c+(k+d/e/f))+g";

        Set<String> source = new HashSet<>();
        Set<String> res = new HashSet<>();
        source.add(keyword);
        do {
            for (String k : source) {
                res.addAll(getKeywordGro(k));
            }
            List<String> checkList = res.stream().filter(s -> {
                return s.contains("(") || s.contains(")") || s.contains("/");
            }).collect(Collectors.toList());
            if (checkList.size() == 0) {
                break;
            }
            source.clear();
            source.addAll(res);
            res.clear();
        } while (true);
        System.out.println("keyword groups: " + res);

    }

    private static Set<String> getKeywordGro(String keyword) {
        String withoutWhiteSpace = keyword.replaceAll("\\s", "");
        Set<String> kGroups = new HashSet<>();
        int from = 0;
        for (int i = 0; i < withoutWhiteSpace.length(); i++) {
            char c = withoutWhiteSpace.charAt(i);
            if ('(' == c) {
                int begin = i + 1;
                String tmp = withoutWhiteSpace.substring(begin);
                int closeIndex = findBracketClose(tmp) + begin;
                String betweenBracket = withoutWhiteSpace.substring(begin, closeIndex);
                if (betweenBracket.contains("(") && betweenBracket.contains(")")) {
                    String prefix = withoutWhiteSpace.substring(0, i + 1);
                    String suffix = withoutWhiteSpace.substring(closeIndex);
                    kGroups.add(prefix + simplyInnerBracket(betweenBracket) + suffix);
                    return kGroups;
                } else {
                    Set<String> kwList = getKeywordGro(betweenBracket);
                    for (String word : kwList) {
                        String prefix = withoutWhiteSpace.substring(0, i);
                        String suffix = withoutWhiteSpace.substring(closeIndex + 1);
                        kGroups.add(prefix + word + suffix);
                    }
                }
                return kGroups;
            }
            if ('/' == c) {
                kGroups.add(withoutWhiteSpace.substring(from, i));
                from = i + 1;
            }

        }
        if (from != withoutWhiteSpace.length()) {
            kGroups.add(withoutWhiteSpace.substring(from));
        }
        return kGroups;
    }

    private static String simplyInnerBracket(String tmpString) {
        // int from = 0;
        // Set<String> kGroups = new HashSet<>();
        // String res;
        for (int i = 0; i < tmpString.length(); i++) {
            char c = tmpString.charAt(i);
            if ('(' == c) {
                int begin = i + 1;
                String tmp = tmpString.substring(begin);
                int closeIndex = findBracketClose(tmp) + begin;
                String betweenBracket = tmpString.substring(begin, closeIndex);
                if (betweenBracket.contains("(") && betweenBracket.contains(")")) {
                    return simplyInnerBracket(betweenBracket);
                } else {
                    Set<String> kwList = getKeywordGro(betweenBracket);
                    String prefix = tmpString.substring(0, i);
                    String suffix = tmpString.substring(closeIndex + 1);
                    if (kwList.size() > 1) {
                        // 括号外面右边是+，要运用分配律
                        if (closeIndex + 1 < tmpString.length() && tmpString.charAt(closeIndex + 1) == '+') {
                            // trueSuffix;
                            String trueSuffix;
                            int plusIndex = findAddElement(tmpString, closeIndex + 2);
                            if (plusIndex != -1) {
                                trueSuffix = tmpString.substring(closeIndex + 1, plusIndex);
                                Set<String> kwSet = kwList.stream().map(w -> {
                                    return w + trueSuffix;
                                }).collect(Collectors.toSet());
                                return "(" + StringUtils.join(kwSet, "/") + ")" + tmp.substring(plusIndex - 1);
                            } else {
                                trueSuffix = suffix;
                                Set<String> kwSet = kwList.stream().map(w -> {
                                    return w + trueSuffix;
                                }).collect(Collectors.toSet());
                                return StringUtils.join(kwSet, "/");
                            }
                        }
                        // 括号外面左边是+，要运用分配律
                        if (i - 1 >= 0 && tmpString.charAt(i - 1) == '+') {
                            int findIndex = prefix.lastIndexOf("/");
                            String truePrefix;
                            if (findIndex == -1) {
                                truePrefix = prefix;
                                Set<String> kwSet = kwList.stream().map(w -> {
                                    return truePrefix + w;
                                }).collect(Collectors.toSet());
                                return StringUtils.join(kwSet, "/") + suffix;
                            } else {
                                truePrefix = prefix.substring(findIndex + 1, i);
                                Set<String> kwSet = kwList.stream().map(w -> {
                                    return truePrefix + w;
                                }).collect(Collectors.toSet());
                                return prefix.substring(0, findIndex + 1) + StringUtils.join(kwSet, "/") + suffix;
                            }

                        }
                        return prefix + StringUtils.join(kwList, "/") + suffix;
                    }
                    return prefix + kwList.iterator().next() + suffix;
                }

            }

        }
        return tmpString;
    }

    private static int findAddElement(String tmpString, int from) {
        for (int i = from; i < tmpString.length(); i++) {
            if (tmpString.charAt(i) == '+') {
                return i;
            }
        }
        return -1;
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
