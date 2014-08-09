import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.mihalis.opal.*;
import org.mihalis.opal.angles.AngleSlider;
import org.mihalis.opal.multiChoice.MultiChoice;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.notify.NotifierColorsFactory;
import org.mihalis.opal.systemMonitor.SystemMonitor;
import org.mihalis.opal.systemMonitor.SampleIdentifier;
import org.mihalis.opal.titledSeparator.TitledSeparator;
import org.mihalis.opal.utils.SimpleSelectionAdapter;

import java.util.Random;

import java.net.URL;

/**
 * Created by WhiteMountiens on 20.07.2014.
 */
public class Bio {

    private final Label status;
    private Scale scale;
    private Text value;
    public static SystemMonitor custom;

    public Bio() {

        Display display = new Display();
        final Shell shell = new Shell(display);
        URL dirURL = getClass().getClassLoader().getResource("images/icon.ico");
        shell.setImage(new Image(display, dirURL.getPath()));
        shell.setText("BioFeedBack study");
        shell.setLayout(new GridLayout(1, false));

        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);

        MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeFileMenu.setText("&File");

        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeFileMenu.setMenu(fileMenu);

        MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
        exitItem.setText("&Exit");

        MenuItem cascadeViewMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeViewMenu.setText("&View");

        Menu viewMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeViewMenu.setMenu(viewMenu);

        MenuItem statItem = new MenuItem(viewMenu, SWT.CHECK);
        statItem.setSelection(true);
        statItem.setText("&View Statusbar");

        statItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                Notifier.notify("New message", "from bobby (bobby@...)<br/><br/>Test message ...");
            }
        });

        exitItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.getDisplay().dispose();
                System.exit(0);
            }
        });

        final AngleSlider angleSlider = new AngleSlider(shell, SWT.NONE);
        angleSlider.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 2, 1));

        final TitledSeparator sep1 = new TitledSeparator(shell, SWT.NONE);
        sep1.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
        sep1.setText("Parameters");

        custom = new SystemMonitor(shell, SWT.NONE);
        final RandomSample detectedSignal = new RandomSample();
        custom.addSample("custom", detectedSignal);
        custom.setCaption("custom", "Mesured value:");
        custom.setColor("custom", new RGB(255, 255, 216));
        custom.setFormatPattern("custom", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");

        final ConstSample treshold = new ConstSample();
        custom.addSample("const", treshold);
        custom.setCaption("const", "Treshhold value:");
        custom.setColor("const", new RGB(255, 55, 105));
        custom.setFormatPattern("const", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");
        custom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        custom.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent paintEvent) {

            }
        });

        status = new Label(shell, SWT.BORDER);
        status.setText("Ready");
        status.setLayoutData(new GridData(SWT.FILL, SWT.END, false, false));

        //shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    /**
     * @return a layout data
     */
    private static GridData createLayoutData() {
        final GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
        gd.widthHint = 500;
        gd.heightHint = 400;
        return gd;
    }

}
