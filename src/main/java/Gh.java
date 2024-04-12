import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Gh {
    public static void main(String[] args) {

        String[] strArr = {"bob", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"};
        List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(strArr, 2, strArr.length));

        String uri = "https://shgz.obs.cn-north-4.myhuaweicloud.com:443/2021-12-20/FbDIX-%E6%B5%99%E6%B1%9F%E6%B7%98%E5%AE%9D%E7%BD%91%E7%BB%9C%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8-2021%E5%B9%B412%E6%9C%889%E6%97%A5-001%281%29.xlsx?AccessKeyId=IA3X7FNYN0NOJ3GEU6NI&Expires=1650365928&Signature=HCYkWzb5KD%2Fra0tgdxzy1G3K8TQ%3D";
        try {
            URI parseUri = new URI(uri);
            String path = parseUri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(authoritiesStrings);
    }
}
