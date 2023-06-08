import GetMusicData.{hive_spark, logger}
import org.apache.commons.lang.time.DateFormatUtils
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.types.StringType

import java.util.Date
// 浙江省卫生机构分析 hive数据库表生成

object zhejiang_health_institutions {
  private val hive_spark: SparkSession = config.init_hive_spark()
  hive_spark.sql("set hive.exec.dynamic.partition=true")
  hive_spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")

  def creat_healthcare_table(): Unit = {
    /**
    Total number of medical and health institutions: 医疗卫生机构总数 (TMI)
    Outpatient department: 门诊部 (OPD)
    Traditional Chinese Medicine hospital: 中医医院 (TCM hospital)
    Disease prevention and control center: 疾病预防控制中心 (DPC center)
    Village health clinic: 村卫生室 (VHC)
    Medical in-service training institution: 医学在职培训机构 (MITI)
    Total number of primary healthcare institutions: 基层医疗卫生机构总数 (PHCIs)
    Total number of specialized public health institutions: 专业公共卫生机构总数 (SPHIs)
    Administrative division: 行政区划 (AD)
    Data collection time: 数据统计时间 (DCT)
    Department type: 科室类型 (DT)
    Total number of hospitals: 医院总数 (TH)
    Street health center: 街道卫生院 (SHC)
    Township health center: 乡镇卫生院 (THC)
    Health supervision institute (center): 卫生监督所（中心） (HSI)
    Medical research institution: 医学科研机构 (MRI)
    Nursing home: 护理院 (NH)
    Community health service station: 社区卫生服务站 (CHSS)
    Integrated traditional Chinese and Western medicine hospital: 中西医结合医院 (ITCWM hospital)
    Blood collection and supply institution: 采供血机构 (BCSI)
    Specialized disease prevention and treatment institution (clinic, station): 专科疾病防治院(所、站) (SDPTI)
    Total number of other institutions: 其他机构总数 (TOI)
    Family planning technical service institution: 计划生育技术服务机构 (FPT)
    Specialized hospital: 专科医院
    Community health service center: 社区卫生服务中心
    Convalescent hospital: 疗养院
    Emergency center (station): 急救中心（站）
    General hospital: 综合医院
    Maternal and child health care institution (clinic, station): 妇幼保健院(所、站)
     */
    val database_sql = "create database if not exists healthcare"
    val table_sql: String = "CREATE TABLE IF NOT EXISTS healthcare.health_institutions" +
      "(tmi INT,opd INT,tcm_hospital INT,dpc_center INT,vhc INT,miti INT,phcis INT,sphis INT,ad STRING,dct STRING,dt STRING," +
      "th INT,shc INT,thc INT,hsi INT,mri INT,nh INT,chss INT,itcwm_hospital INT,bcsi INT,sdpti INT,toi INT,fpt INT," +
      "specialized_hospital INT,community_health_center INT,convalescent_hospital INT,emergency_center INT,general_hospital INT," +
      "maternal_child_healthcare INT)" +
      "COMMENT 'zhejiang healthcare' " +
      "ROW FORMAT DELIMITED " +
      "FIELDS TERMINATED BY '\\t' " +
      "LINES TERMINATED BY '\\n' " +
      //      "PARTITIONED BY (default_date String)" +
      "STORED AS TEXTFILE"
    hive_spark.sql(database_sql)
    hive_spark.sql(table_sql)
  }

  def data2hive(): Unit = {
    val path = "file:///E:/src/data/zhejaing.csv"
    val ranksinger = hive_spark.read.csv(path)
//    ranksinger.show()
    //      ranksinger.show(400)
    // 建立视图，创建hive数据库，数据表，插入数据
    ranksinger.createOrReplaceTempView("zhejiang_data")
    val insert_sql = "insert overwrite table healthcare.health_institutions select * from zhejiang_data"
    hive_spark.sql(insert_sql)
    //    logger.info("rankSingers inserted successfully")
  }


  def main(args: Array[String]): Unit = {
    creat_healthcare_table()
    data2hive()
//    hive_spark.sql("show databases").show()
  }
}
