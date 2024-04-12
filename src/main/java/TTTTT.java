import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class TTTTT {

    public static void main(String[] args) {

        // String str = "「全球不平等實驗室」(World Lab)日前發布「2022年全球不平等報告」(World Report
        // 2022),全球不平等實驗室,指出貧富懸殊進一步惡化,全球人口最富有的10%,擁有近76%財富,而底層貧窮的50%人口,只擁有大約2%財富,反映貧富懸殊在新冠疫情爆發至今進一步惡化。2019年諾貝爾經濟學獎得主巴納吉(Abhijit
        // Banerjee)與杜芙洛(Esther
        // Duflo)在報告前言中指出,由於財富是未來經濟收益的主要來源,而且愈來愈成為權力和影響力的來源,這預示着不平等將進一步加劇。兩位經濟學家進一步指出,從1995年至2021年間,全球1%的人口佔全球財富增長的38%,最底層的50%人口僅佔2%;前0.1%富豪擁有的財富份額,從7%上升至11%。在這種財富懸殊不斷加劇的情況下,財富分配機制必須及時得到糾正,然而隨着股市繼續上升,情況似乎並沒有好轉迹象。寬鬆幣策
        // 出現「馬太效應」很顯然,在新冠疫情爆發後,各國政府都採取了極度寬鬆的貨幣政策,向市場注入大量資金,股市及房地產價格大幅飈升,使最高收入階層所擁有的資產價格上升,個人財富增長不斷攀升;至於底層人口,尤其在社會福利體系脆弱的貧窮國家,貧窮階層的收入佔比進一步萎縮,出現了「馬太效應」,即貧者愈貧。";
        // String subject = "諾貝爾經濟學獎得主巴納吉(AbhijitBanerjee)與杜芙洛";
        // int i = str.indexOf(subject);
        //
        // if (i == -1) {
        // String replace = str.replace(" ", "");
        // i = replace.indexOf(subject);
        // }
        // int end = subject.length() + i;
        // System.out.println("起始位置 " + i);
        // System.out.println("中止位置 " + end);

        // String regex = "https://www.facebook.com/pages/(.+)/(\\d+)";
        // Pattern pattern = Pattern.compile(regex);
        // Matcher m =
        // pattern.matcher("https://www.facebook.com/pages/%E6%9D%8E%E4%BB%B2%E5%A8%81/992502947523642");
        // boolean b = m.matches();
        // System.out.println("find " + b);

        // String pattern =
        // "(代/?\\?\\.?-?发|联/?\\?\\.?-?系|客/?\\?\\.?-?服|业/?\\?\\.?-?务|咨/?\\?\\.?-?询|点/?\\?\\.?-?击|热/?\\?\\.?-?线|预/?\\?\\.?-?约|預/?\\?\\.?-?約|服/?\\?\\.?-?务)";
        // String pattern =
        // "(\u4ee3/?\\?\\.?-?\u53d1|\u8054/?\\?\\.?-?\u7cfb|\u5ba2/?\\?\\.?-?\u670d|\u4e1a/?\\?\\.?-?\u52a1|\u54a8/?\\?\\.?-?\u8be2|\u70b9/?\\?\\.?-?\u51fb|\u70ed/?\\?\\.?-?\u7ebf|\u9884/?\\?\\.?-?\u7ea6|\u9810/?\\?\\.?-?\u7d04|\u670d/?\\?\\.?-?\u52a1)";
        // String pattern =
        // "(代(/|\\\\|.|-)发|联(/|\\\\|.|-)系|客(/|\\\\|.|-)服|业(/|\\\\|.|-)务|咨(/|\\\\.|-)询|点(/|\\\\|.|-)击|热(/|\\\\|.|-)线|预(/|\\\\|.|-)约|預(/|\\\\|.|-)約|服(/|\\\\|.|-)务)|(进.*群)";
        // String pattern =
        // "|((承接|办理).*业务)|接.*单|一对一.*教学|招.*人员|出.*号|欢迎.*对接|飞机.*号|接.*号|出.*粉|出.*卡|一手.*号|出.*ws";
        // Pattern p = Pattern.compile(pattern);
        // Matcher matcher = p.matcher("z 招：支付宝 UID 大小额一手通道 合作 搜 JCpay01\nl 来：备付金代付 联系搜
        // JCpay01");
        // if (matcher.find()) {
        // System.out.println(matcher.group(0));
        // }
        ;

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
        // String keyword = "((a/b/c)+(d))+e";

        // String keyword = "((a/b/c)+(j/k/l))+e";
        String keyword = "(a+(j/k/l)/b+(j/k/l)/c+(j/k/l))+e";

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
                        // 如果括号外面是+，要运用分配律
                        if (tmpString.charAt(closeIndex + 1) == '+') {
                            Set<String> kwSet = kwList.stream().map(w -> {
                                return w + suffix;
                            }).collect(Collectors.toSet());
                            return prefix + StringUtils.join(kwSet, "/");
                        }
                        if (tmpString.charAt(i - 1) == '+') {
                            Set<String> kwSet = kwList.stream().map(w -> {
                                return prefix + w;
                            }).collect(Collectors.toSet());
                            return StringUtils.join(kwSet, "/") + suffix;
                        }
                        return prefix + StringUtils.join(kwList, "/") + suffix;
                    }
                    return prefix + kwList.iterator().next() + suffix;
                }

            }

        }
        return tmpString;
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
