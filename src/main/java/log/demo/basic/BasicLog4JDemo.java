package log.demo.basic;

import org.apache.log4j.*;
import org.apache.log4j.pattern.LogEvent;
import sun.rmi.runtime.Log;

import java.net.InetAddress;

public class BasicLog4JDemo {


    static {
        // NDC.push(InetAddress.getLocalHost().getHostAddress());
        MDC.put("user", "Guo");
        PropertyConfigurator.configure( "conf/log4j.properties" );
    }

    public static void main(String[] args){

        try {
                logHierarchy();
//            logForComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logHierarchy() throws Exception{

        /* log hierarchy */
        Logger logger1 = Logger.getLogger("com");  //LEVEL DEBUG
        Logger logger2 = Logger.getLogger("com.candy");
        Logger logger3 = Logger.getLogger("com.candy.service");


        System.out.println("logger1's parent:" + logger1.getParent().getName());
        System.out.println("logger2's parent:" + logger2.getParent().getName());
        System.out.println("logger3's parent:" + logger3.getParent().getName());


        ConsoleAppender consoleAppender1 = new ConsoleAppender(new PatternLayout("%c - %m%n"), "System.out");
        ConsoleAppender consoleAppender2 = new ConsoleAppender(new PatternLayout("%c - %m%n"), "System.out");
        ConsoleAppender consoleAppender3 = new ConsoleAppender(new PatternLayout("%c - %m%n"), "System.out");

        Priority priority1 = consoleAppender1.getThreshold();
        Priority priority2 = consoleAppender2.getThreshold();
        Priority priority3 = consoleAppender3.getThreshold();

        logger1.addAppender(consoleAppender1);
        logger2.addAppender(consoleAppender2);
        logger3.addAppender(consoleAppender3);

        logger2.setLevel(Level.WARN);
        logger3.setLevel(Level.INFO);

        System.out.println("console1's threshold:" + priority1);
        System.out.println("console2's threshold:" + priority2);
        System.out.println("console3's threshold:" + priority3);

        logger1.info("logger1");
        logger2.info("logger2");
        logger3.info("logger3");

//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            public void uncaughtException(Thread t, Throwable e) {
//                Logger.getLogger(BasicLog4JDemo.class).log(Priority.FATAL, t + " ExceptionDemo threw an exception: ", e);
//            };
//        });
//
//        logger.info("It happens. Life Happens.");
//        logger.error("NO.");
//        try {
//            int i = 5/0;
//        } catch (Exception e) {
//            logger.error("sssssssssss", e);
//        }
//
//        PatternLayout layout = new PatternLayout();
//        boolean b = layout.ignoresThrowable();
//        System.out.println(b);
//
//        Category category = logger.getParent();
//        System.out.println("name:" + category.getName());
//        throw new IllegalArgumentException("wrong arguments!!!!");
    }

    public static void logForComponents(){
        Logger rootLogger = Logger.getRootLogger();
        Logger serviceLogger = Logger.getLogger("service");
        Logger viewerLogger = Logger.getLogger("viewer");

        serviceLogger.setAdditivity(false);
        viewerLogger.setAdditivity(false);

        rootLogger.info("this is root logger info");

        serviceLogger.info("this is service log info");
        serviceLogger.warn("this is service log warning");
        serviceLogger.error("this is service log error");

        viewerLogger.info("this is viewer log info");
        viewerLogger.warn("this is viewer log warning");
        viewerLogger.error("this is viewer log error");
    }

}
