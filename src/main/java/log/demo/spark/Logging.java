package log.demo.spark;

import org.apache.log4j.Logger;

public interface Logging {

    default void logInfo(String message){
        Logger.getLogger(this.getClass().getName()).info(message);
    }
}
