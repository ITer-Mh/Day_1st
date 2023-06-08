import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class GetMusic2local {
    /**热门歌手*/

    String POPULARSINGER = "http://localhost:3000/top/artists?offset=0&limit=100";

    /**歌手排行,带参数后分别表示中美韩日*/
    String SINGERS_ALL = "http://localhost:3000/toplist/artist";
    String SINGERS_ZG = "http://localhost:3000/toplist/artist?type=1";
    String SINGERS_OM = "http://localhost:3000/toplist/artist?type=2";
    String SINGERS_HG = "http://localhost:3000/toplist/artist?type=3";
    String SINGERS_RB = "http://localhost:3000/toplist/artist?type=4";
    /**
     * 热门评论
     * MV排行
     */
    String MV = "localhost:3000/top/mv?limit=50";

    /**歌曲排名
     * 飙升，新歌，原创，热歌，说唱，欧美，民谣
     */
    String MUSIC_BS = "http://localhost:3000/playlist/detail?id=19723756";
    String MUSIC_XG = "http://localhost:3000/playlist/detail?id=3779629";
    String MUSIC_YC = "http://localhost:3000/playlist/detail?id=2884035";
    String MUSIC_RG = "http://localhost:3000/playlist/detail?id=3778678";
    String MUSIC_SC = "http://localhost:3000/playlist/detail?id=991319590";
    String MUSIC_OM = "http://localhost:3000/playlist/detail?id=2809513713";
    String MUSIC_MY = "http://localhost:3000/playlist/detail?id=5059661515";

    /**
     * 歌曲详情，和歌曲排名结合使用
     */
    String MUSIC_XQ = "http://localhost:3000/song/detail?ids=347230";
    /**热门电台*/
    String DJ = "http://localhost:3000/dj/hot?limit=100";
    /**热门主播*/
    String ANCHOR = "http://localhost:3000/dj/toplist/hours?limit=50";

    /**
     * 获取网易云数据，
     * @param url api链接
     * @return 数据
     */
    public static String getDataFromApi(String url) throws Exception {;
        String content = null;
        URLConnection urlConnection = new URL(url).openConnection();
        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        connection.setRequestMethod("GET");
        //连接
        connection.connect();
        //得到响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder bs = new StringBuilder();
            String l;
            while ((l = bufferedReader.readLine()) != null) {
                bs.append(l).append("\n");
            }
            content = bs.toString();
        }
        return content;
    }

    public static  void data2local(String url,String path) throws Exception {
        String singer = getDataFromApi(url);
        try {
            //写入本地文件，覆盖之前的文件
            BufferedWriter out = new BufferedWriter(new FileWriter(path,false));
            out.write(singer);
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:3000/top/artists?offset=0&limit=100";
        String path = "E:/grad/data/singers/popularsinger.json";
        Date date = new Date();
        System.out.println(date);
//        data2local(url,path);
    }

}
