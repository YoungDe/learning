import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSONReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonExcel {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("kws(utf-8).json"))) {
            JSONReader jsonReader = new JSONReader(reader);
            jsonReader.startObject();
            while (jsonReader.hasNext()) {
                System.out.println((jsonReader.readString()));
                jsonReader.readString();
            }
            jsonReader.endObject();
            jsonReader.close();

        } catch (IOException e) {

        }


    }

    private static List<List<String>> head() {
        List<List<String>> list = ListUtils.newArrayList();
        List<String> head0 = ListUtils.newArrayList();
        head0.add("字符串");
        list.add(head0);
        return list;
    }
}
