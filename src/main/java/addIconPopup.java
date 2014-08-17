import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

/**
 * Created by WhiteMountiens on 17.08.2014.
 */
public class addIconPopup {

    Display display;
    Shell mainShell;
    Image image;

    public addIconPopup(Display display, Shell shell, Image image) {
        this.display = display;
        this.mainShell = shell;
        this.image = image;
        build();
    }

    public void build() {

        final Shell shell = new Shell(display);
        final Tray tray = display.getSystemTray();
        if (tray == null) {
            System.out.println("The system tray is not available");
        } else {
            final TrayItem item = new TrayItem(tray, SWT.NONE);
            item.setToolTipText("BioFeedBack");
            item.addListener(SWT.Show, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("show");
                }
            });
            item.addListener(SWT.Hide, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("hide");
                }
            });
            item.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {
                    mainShell.setVisible(true);
                    item.dispose();
                }
            });
            item.addListener(SWT.DefaultSelection, new Listener() {
                public void handleEvent(Event event) {
                    System.out.println("default selection");
                }
            });
            final Menu menu = new Menu(shell, SWT.POP_UP);
            for (int i = 0; i < 8; i++) {
                MenuItem mi = new MenuItem(menu, SWT.PUSH);
                mi.setText("Item" + i);
                mi.addListener(SWT.Selection, new Listener() {
                    public void handleEvent(Event event) {
                        System.out.println("selection " + event.widget);
                    }
                });
                if (i == 0)
                    menu.setDefaultItem(mi);
            }
            item.addListener(SWT.MenuDetect, new Listener() {
                public void handleEvent(Event event) {
                    menu.setVisible(true);
                }
            });
            item.setImage(image);
        }
        //  shell.setBounds(50, 50, 300, 200);
//    shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        image.dispose();
        display.dispose();
    }


}
