public class Children extends Father {

    public String shutout() {
        return "heihei";
    }

    public static void main(String[] args) {
        Father child = new Children();


        System.out.println(child.shutout());
    }
}
