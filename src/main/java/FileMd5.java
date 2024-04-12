import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FileMd5 {

    public static void main(String[] args) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            byte[] buffer = new byte[8192];
//            int length;
//            while ((length = fis.read(buffer)) != -1) {
//                md5.update(buffer, 0, length);
//            }
            String content = "IndiGo, India's leading carrier today commenced direct flights from Dharamshala, its 78th domestic and 104th overall destination, operating the flight between Delhi-Dharamshala. The destination marks airline's entry in Himachal Pradesh and will strengthen domestic connectivity, provide accessibility, while making travel more affordable and hassle-free for customers during the summer season.   Speaking on the occasion, Mr. Vinay Malhotra, Head of Global Sales, IndiGo said, \"We are extremely pleased to announce the launch of our operations from Dharamshala, the 78th domestic destination in the 6E network, making our way into the serene valleys of Himachal Pradesh. Direct connectivity to Dharamshala will provide a gateway for the tourists to explore the local markets, temples and monasteries, museums, churches and unwind in the picturesque waterfalls and mountains of Himachal Pradesh. This move aims to bolster passenger traffic even further as we have witnessed a strong demand for connectivity to Himachal Pradesh. The direct flights from Delhi, will also connect Himachal Pradesh to the rest of the country and international destinations in Asia, Middle East, and Europe. Our commitment to provide courteous, on-time, hassle free, and cost-effective travel experiences remains unwavering as we continue to expand our regional connectivity.\"   Located in the western Himachal region, Dharamshala is the perfect place to unwind and experience the scenic beauty of the valley and local markets. It is the winter capital of Himachal Pradesh and the residence of the spiritual leader, the Dalai Lama. A well-known location for studying Buddhism and experiencing the culture's origins is Dharamshala. The hill station is divided into two distinct parts - Lower Dharamshala which is the commercial market district, and Upper Dharamshala with places like McLeod Ganj and Forsyth Ganj, which serve as historical markers for this region. The area makes a great starting point for hikes and treks into the Dhauladhar mountain range.   The launch of direct flights to Dharamshala will enhance accessibility to nearby tourist attractions and provide more options to consumer looking for vacation in mountains. The Tsuglagkhang Complex, Dalai Lama, Namgyal Monastery, St. John in the Wilderness, The Tibet Museum, Bhagsu Naag Temple, Naddi Hill Point Mcleod Ganj, BhagsuNag waterfall, and Dal Lake are among the scenic and historic tourist attractions in Dharamshala that draw visitors from all over the country. Additionally, easy access to nearby locations like Kangra, Palampur, and Chamba will be made possible by direct flights to Dharamshala.   Shares of InterGlobe Aviation Limited was last trading in BSE at Rs. 1865.30 as compared to the previous close of Rs. 1908.70. The total number of shares traded during the day was 9311 in over 1070 trades.   The stock hit an intraday high of Rs. 1918.95 and intraday low of 1860.10. The net turnover during the day was Rs. 17648511.00.   ";

//            String test = "fsdf   fdsaf asdf sad fsadfas   fasdfsda   fasdf asd";
//            String after = test.replaceAll("\\s+", " ");
//            System.out.println(after);

            String contentAfter = content.replaceAll("\\s+", " ");

            System.out.println(contentAfter);
            byte[] digest1 = md5.digest(("104 destinations strong: IndiGo commences operations from Dharamshala" + contentAfter).getBytes(StandardCharsets.UTF_8));
            BigInteger bigInteger = new BigInteger(1, digest1);
            System.out.println(bigInteger.toString(16));
//            System.out.println("-----------------------------------------");
//            StringBuilder sb = new StringBuilder();
//            int i;
//            for (byte b : digest1) {
//                i = b & 0x00ff;
//                if (i < 16) {
//                    sb.append('0');
//                }
//                sb.append(Integer.toHexString(i));
//            }
//            System.out.println(sb);
        } catch (NoSuchAlgorithmException e) {
        }
        System.out.println(TimeUnit.MINUTES.toSeconds(20));

    }


}
