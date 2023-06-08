import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object config {
  //热门歌手
  val POPULAR_SINGER = "http://localhost:3000/top/artists?offset=0&limit=100"
  //歌手排行,带参数后分别表示中美韩日
  val SINGERS_ALL = "http://localhost:3000/toplist/artist"
  val SINGERS_ZG = "http://localhost:3000/toplist/artist?type=1"
  val SINGERS_OM = "http://localhost:3000/toplist/artist?type=2"
  val SINGERS_HG = "http://localhost:3000/toplist/artist?type=3"
  val SINGERS_RB = "http://localhost:3000/toplist/artist?type=4"
  //热门评论

  //MV排行
  val MV = "localhost:3000/top/mv?limit=50"
  //歌曲排名
  //飙升，新歌，原创，热歌，说唱，欧美，民谣
  val MUSIC_BS = "http://localhost:3000/playlist/detail?id=19723756"
  val MUSIC_XG = "http://localhost:3000/playlist/detail?id=3779629"
  val MUSIC_YC = "http://localhost:3000/playlist/detail?id=2884035"
  val MUSIC_RG = "http://localhost:3000/playlist/detail?id=3778678"
  val MUSIC_SC = "http://localhost:3000/playlist/detail?id=991319590"
  val MUSIC_OM = "http://localhost:3000/playlist/detail?id=2809513713"
  val MUSIC_MY = "http://localhost:3000/playlist/detail?id=5059661515"

  //歌曲详情，和歌曲排名结合使用
  val MUSIC_XQ = "http://localhost:3000/song/detail?ids=347230"
  //热门电台
  val DJ = "http://localhost:3000/dj/hot?limit=100"
  //热门主播
  val ANCHOR = "http://localhost:3000/dj/toplist/hours?limit=50"

  /**
   * 创建 连接hive的spark
   * @return
   */
  def init_hive_spark(): SparkSession = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("sparkwithhive")
      // 指定hive的metastore的端口  默认为9083 在hive-site.xml中查看
      .config("hive.metastore.uris", "thrift://10.24.20.251:9083")
      //指定hive的warehouse目录
      .config("spark.sql.warehouse.dir", "hdfs://10.24.20.251:9000/user/hive/warehouse")
      //直接连接hive
      .config("spark.sql.parquet.writeLegacyFormat", value = true)
      .config("hive.exec.dynamic.partition", value = true) // 支持 Hive 动态分区
      .config("hive.exec.dynamic.partition.mode", "nonstrict") // 非严格模式
      .enableHiveSupport()
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    spark
  }

  /**
   * 创建本地spark
   * @return
   */
  def init_spark(): SparkSession = {
    val spark = SparkSession
      .builder()
      .appName("spark_beidi")
      .master("local[4]")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
//    spark.conf("spark.hadoop.hive.exec.dynamic.partition","true")

    //    spark.sparkContext.hadoopConfiguration.set("mapreduce.fileoutputcommitter.marksuccessfuljobs", "false")
    spark
  }

  def main(args: Array[String]): Unit = {
    val spark = init_hive_spark()
    spark.sql("show databases").show()
    spark.sql("drop table household").show()
  }

}
