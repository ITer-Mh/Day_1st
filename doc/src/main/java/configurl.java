public class configurl {
    /** 热门歌手*/
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

}
