package wekaapp.data;

import java.io.Console;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: mnarowski
 * Date: 25.04.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class Helper {

    private static final String TAG = "Thread initialized at: %s";
    private static PrintStream mLogWriter = System.out;
    private static PrintStream mErrWriter = System.err;
    public static void threadRun(Runnable runnable){
        Thread thread = new Thread(runnable,String.format(TAG, String.valueOf(System.currentTimeMillis()) ));
        thread.start();
    }


    public static class log{

        private static final String LOG_FORMAT = "[%s - WekaApp][%s] %s";

        public static void i(Object... info) {
            for(Object i : info){
               mLogWriter.println(String.format(LOG_FORMAT,String.valueOf(new Date()),"INFO" ,i));
            }
        }


        public static void e(Object... info) {
            for(Object i : info){
                mErrWriter.println(String.format(LOG_FORMAT,String.valueOf(new Date()),"ERROR" ,i));
            }
        }
    }
}
