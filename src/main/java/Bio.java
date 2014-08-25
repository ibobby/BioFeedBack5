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
import org.mihalis.opal.obutton.DefaultButtonRenderer;
import org.mihalis.opal.obutton.OButton;
import org.mihalis.opal.obutton.RedButtonRenderer;
import org.mihalis.opal.systemMonitor.SystemMonitor;
import org.mihalis.opal.titledSeparator.TitledSeparator;

import java.net.URL;

/**
 * Created by WhiteMountiens on 20.07.2014.
 */
public class Bio {

    private Label status;
    private Scale scale;
    private Text value;
    public static SystemMonitor respirationGraph;
    public static SystemMonitor heartRythmGraph;
    private final static int RANDOM_TRESHHOLD = 60;
    private final static int HEART_RATE_TRESHHOLD = 80;

    public Bio() {

        final Display display = new Display();
        final Shell shell = new Shell(display);
        URL dirURL = getClass().getClassLoader().getResource("images/icon.ico");
        final Image image = new Image(display, dirURL.getPath());
        shell.setImage(image);
        shell.setText("BioFeedBack study");
        shell.setLayout(new GridLayout(2, true));

        shell.addListener(SWT.Close, new Listener() {
            @Override
            public void handleEvent(Event event) {
                shell.setVisible(false);
                new addIconPopup(display, shell, image);
            }
        });

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
                Notifier.notify("Зафиксировано отклонение параметров", "частота сердечных сокращений<br/><br/>выше нормы");
            }
        });

        exitItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.getDisplay().dispose();
                System.exit(0);
            }
        });

        final OButton button1 = new OButton(shell, SWT.PUSH);
        button1.setText("Normal button");
        final GridData gd = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
        gd.widthHint = 200;
        button1.setLayoutData(gd);
        button1.setButtonRenderer(DefaultButtonRenderer.getInstance());

        final OButton button2 = new OButton(shell, SWT.PUSH);
        button2.setText("Text & image");
        //button2.setImage(icon);
        button2.setLayoutData(gd);
        button2.setButtonRenderer(RedButtonRenderer.getInstance());

        final AngleSlider angleSlider = new AngleSlider(shell, SWT.NONE);
        angleSlider.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 2, 1));

        final TitledSeparator sep1 = new TitledSeparator(shell, SWT.NONE);
        sep1.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
        sep1.setText("Parameters");

        //Пример размещения нескольких SystemMonitor в строку и столбец
        //http://code.google.com/a/eclipselabs.org/p/opal/source/browse/src/test/java/org/mihalis/opal/SystemMonitor/SystemMonitorSnippet.java

        respirationGraph = new SystemMonitor(shell, SWT.NONE);
        final RandomSample detectedSignal = new RandomSample(RANDOM_TRESHHOLD);
        respirationGraph.addSample("respirationGraph", detectedSignal);
        respirationGraph.setCaption("respirationGraph", "Mesured value:");
        respirationGraph.setColor("respirationGraph", new RGB(255, 255, 216));
        respirationGraph.setFormatPattern("respirationGraph", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");

        final ConstSample treshold1 = new ConstSample(RANDOM_TRESHHOLD);
        respirationGraph.addSample("const", treshold1);
        respirationGraph.setCaption("const", "Treshhold value:");
        respirationGraph.setColor("const", new RGB(255, 55, 105));
        respirationGraph.setFormatPattern("const", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");
        respirationGraph.setLayoutData(createLayoutData());

        heartRythmGraph = new SystemMonitor(shell, SWT.NONE);

        final MasSample heartRate = new MasSample(HEART_RATE_TRESHHOLD);
        heartRythmGraph.addSample("heartRate", heartRate);
        heartRythmGraph.setCaption("heartRate", "Heart rate:");
        heartRythmGraph.setColor("heartRate", new RGB(255, 100, 216));
        heartRythmGraph.setFormatPattern("heartRate", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");

//        final ConstSample treshold2 = new ConstSample(HEART_RATE_TRESHHOLD);
//        heartRythmGraph.addSample("const", treshold2);
//        heartRythmGraph.setCaption("const", "Treshhold value:");
//        heartRythmGraph.setColor("const", new RGB(255, 10, 5));
//        heartRythmGraph.setFormatPattern("const", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");

        heartRythmGraph.setLayoutData(createLayoutData());

        status = new Label(shell, SWT.BORDER);
        status.setText("Ready");
        status.setLayoutData(new GridData(SWT.FILL, SWT.END, false, false));

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
