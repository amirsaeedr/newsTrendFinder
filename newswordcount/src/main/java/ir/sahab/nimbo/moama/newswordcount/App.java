package ir.sahab.nimbo.moama.newswordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String[] jars = {"/home/amirsaeed/newstrendfinder/newswordcount/target/newswordcount 1.0-SNAPSHOT-jar-with-dependencies.jar"};
        SparkConf sparkConf = new SparkConf().setAppName("wordcount").setMaster("spark://master-node:7077").setJars(jars);
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
//        new RankCalculator("PageRank", "spark://master-node:7077").calculate();
        JavaRDD<String> textFile = sparkContext.textFile("hdfs://input.txt");
        JavaPairRDD<String, Integer> counts = textFile
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((a, b) -> a + b);
        counts.saveAsTextFile("hdfs://output");
    }
}
