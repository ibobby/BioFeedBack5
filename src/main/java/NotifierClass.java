import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory;

/**
 * Created by WhiteMountiens on 06.08.2014.
 */
public class NotifierClass implements Runnable {
    @Override
    public void run() {
        while (true) {
            Notifier.notify("New Mail message", "Laurent CARON (lcaron@...)<br/><br/>Test message with blue theme...", NotifierColorsFactory.NotifierTheme.BLUE_THEME);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignore) {/*NOP*/}
        }

    }
}
