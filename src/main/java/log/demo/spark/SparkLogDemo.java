package log.demo.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.executor.Executor;
import org.apache.spark.ui.SparkUI;

public class SparkLogDemo {
    public static void main(String[] args) {
        PropertyConfigurator.configure( "conf/log4j_spark.properties" );
        initSparkContext();

        Logger logger1 = Logger.getLogger("org.apache.spark");
        Logger logger2 = Logger.getLogger("org.apache.spark.ui");
        Logger logger3 = Logger.getLogger("org.apache.spark.ui.SparkUI");

        System.out.println("logger1's parent:" + logger1.getParent().getName());
        System.out.println("logger2's parent:" + logger2.getParent().getName());
        System.out.println("logger3's parent:" + logger3.getParent().getName());


//        logger1.setLevel(Level.INFO);
        logger2.setLevel(Level.WARN);
        logger3.setLevel(Level.INFO);
        System.out.println("logger1's level:" + logger1.getLevel());
        System.out.println("logger2's level:" + logger2.getLevel());
        System.out.println("logger3's level:" + logger3.getLevel());

        logger1.info("this is from logger1");
        logger2.info("this is from logger2");
        logger3.info("this is from logger3");

        while (true) {}
//        LogImp logImp = new LogImp();
    }

    public static void initSparkContext() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[2]").setAppName("SparkLogTest");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
    }
}
