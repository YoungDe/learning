import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {

    static class Node {
        private Integer id;
        private String name;
        private Integer parentId;
        private List<Node> children;
        private Integer count;

        public Node(Integer id, String name, Integer parentId, Integer count) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
            this.count = count;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    public void findChildren(Node node, List<Node> allNodes) {
        List<Node> children = allNodes.stream().filter(i -> i.getParentId().equals(node.getId())).collect(Collectors.toList());
        if (!children.isEmpty()) {
            for (Node child : children) {
                findChildren(child, allNodes);
            }
        }
        node.setCount(node.count + children.stream().mapToInt(Node::getCount).sum());
        node.setChildren(children);
    }

    public static void main(String[] args) {
//        Node root = new Node(1, "all", 0, 0);
//        Node node1 = new Node(2, "未分组", 1, 20);
//        Node node2 = new Node(3, "分组1", 1, 0);
//        Node node3 = new Node(4, "分组2", 3, 0);
//        Node node4 = new Node(5, "分组3", 3, 0);
//        Node node5 = new Node(6, "分组4", 2, 0);
//        Node node6 = new Node(7, "分组5", 4, 0);
//        Node node7 = new Node(8, "分组6", 5, 2);
//        Node node8 = new Node(9, "分组7", 5, 4);
//        Node node9 = new Node(10, "分组8", 5, 5);
//
//        List<Node> nodes = Arrays.asList(root, node1, node2, node3, node4, node5, node6, node7, node8, node9);
//
//        Test test = new Test();
//
//        test.findChildren(root, nodes);

//        LocalDate now = LocalDate.now();
//        LocalDate beginDate = now.with(DayOfWeek.MONDAY);
//        LocalDate endDate = beginDate.plusWeeks(1);
//        System.out.println(beginDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
//        System.out.println(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

//        String date = "非法移民/私渡/偷潜/潜逃/蛇头/Illegal immigration/smuggling/sneaking/absconding";
//        System.out.println(generateKeywordQuery(date));

        String label = "1233";
        String digit = "\\D";
        Pattern compile = Pattern.compile(digit);
        Matcher matcher = compile.matcher(label);
        String trim = matcher.replaceAll("").trim();
        System.out.println(trim);


    }

    public static String generateKeywordQuery(String keywordParse) {

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < keywordParse.length()) {
            while (i < keywordParse.length() && (keywordParse.charAt(i) == '(' || keywordParse.charAt(i) == ')' || keywordParse.charAt(i) == '+' || keywordParse.charAt(i) == '-' || keywordParse.charAt(i) == '/')) {
                stringBuilder.append(keywordParse.charAt(i));
                i++;
            }
            if (i >= keywordParse.length()) {
                break;
            }
            stringBuilder.append("\"");
            while (i < keywordParse.length() && (keywordParse.charAt(i) != '(' && keywordParse.charAt(i) != ')' && keywordParse.charAt(i) != '+' && keywordParse.charAt(i) != '-' && keywordParse.charAt(i) != '/')) {
                stringBuilder.append(keywordParse.charAt(i));
                i++;
            }
            stringBuilder.append("\"");
        }
        String keyword = stringBuilder.toString();
        keyword = keyword.replace("\\+", " AND ");
        keyword = keyword.replace("/", " OR ");
        keyword = keyword.replace("+", " AND ");

        return keyword;
    }
}
