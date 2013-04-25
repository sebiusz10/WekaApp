package wekaapp.data;

/**
 * Created with IntelliJ IDEA.
 * User: mnarowski
 * Date: 25.04.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class Helper {

    private static final String TAG = "Thread initialized at: %s";

    public static void threadRun(Runnable runnable){
        Thread thread = new Thread(runnable,String.format(TAG, String.valueOf(System.currentTimeMillis()) ));
        thread.start();
    }


}
