import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.mihalis.opal.angles.AngleSlider;
import org.mihalis.opal.notify.Notifier;
import org.mihalis.opal.systemMonitor.SystemMonitor;
import org.mihalis.opal.titledSeparator.TitledSeparator;

import java.net.URL;

/**
 * Created by WhiteMountiens on 17.08.2014.
 */
public class BuildInterface {

    Display display;
    Shell shell;
    private Label status;
    private Scale scale;
    private Text value;
    public static SystemMonitor respirationGraph;
    public static SystemMonitor heartRythmGraph;

    public BuildInterface(Display mainDisplay, Shell mainShell) {
        this.shell = mainShell;
        this.display = mainDisplay;

        URL dirURL = getClass().getClassLoader().getResource("images/icon.ico");
        final Image image = new Image(display, dirURL.getPath());
        shell.setImage(image);
        shell.setText("BioFeedBack study");
        shell.setLayout(new GridLayout(1, false));

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

        respirationGraph = new SystemMonitor(shell, SWT.NONE);
        final RandomSample detectedSignal = new RandomSample();
        respirationGraph.addSample("respirationGraph", detectedSignal);
        respirationGraph.setCaption("respirationGraph", "Mesured value:");
        respirationGraph.setColor("respirationGraph", new RGB(255, 255, 216));
        respirationGraph.setFormatPattern("respirationGraph", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");

        final ConstSample treshold = new ConstSample();
        respirationGraph.addSample("const", treshold);
        respirationGraph.setCaption("const", "Treshhold value:");
        respirationGraph.setColor("const", new RGB(255, 55, 105));
        respirationGraph.setFormatPattern("const", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");
        respirationGraph.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        heartRythmGraph = new SystemMonitor(shell, SWT.NONE);
        final RandomSample heartSignal = new RandomSample();
        heartRythmGraph.addSample("respirationGraph", heartSignal);
        heartRythmGraph.setCaption("respirationGraph", "Mesured value:");
        heartRythmGraph.setColor("respirationGraph", new RGB(255, 255, 216));
        heartRythmGraph.setFormatPattern("respirationGraph", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");

        final ConstSample hearttreshold = new ConstSample();
        heartRythmGraph.addSample("const", treshold);
        heartRythmGraph.setCaption("const", "Treshhold value:");
        heartRythmGraph.setColor("const", new RGB(255, 55, 105));
        heartRythmGraph.setFormatPattern("const", "%{value},.0f / %{maxValue},.0f / %{percentValue}.0f%%");
        heartRythmGraph.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));


        status = new Label(shell, SWT.BORDER);
        status.setText("Ready");
        status.setLayoutData(new GridData(SWT.FILL, SWT.END, false, false));

    }
}
