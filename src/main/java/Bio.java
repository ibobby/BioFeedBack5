import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.mihalis.opal.angles.AngleSlider;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.systemMonitor.SystemMonitor;
import org.mihalis.opal.titledSeparator.TitledSeparator;

import java.net.URL;

/**
 * Created by WhiteMountiens on 20.07.2014.
 */
public class Bio {

    public Bio() {

        final Display display = new Display();
        final Shell shell = new Shell(display);

        new BuildInterface(display, shell);


        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
}
