import org.eclipse.swt.graphics.RGB;
import org.mihalis.opal.systemMonitor.Sample;

/**
 * Created by bobby on 09.08.2014.
 */
public class MasSample implements Sample {

    int treshhold;

    public MasSample(int treshhold) {
        this.treshhold = treshhold;
    }

    double[] mas = new double[]{0.656, 0.641, 0.672, 0.695, 0.703, 0.703, 0.742, 0.781, 0.812, 0.812, 0.758, 0.781, 0.781, 0.773, 0.758, 0.758, 0.797, 0.805, 0.781, 0.758, 0.781, 0.797, 0.797, 0.766, 0.773, 0.805, 0.820, 0.797, 0.773, 0.781, 0.805, 0.812, 0.773, 0.758, 0.766, 0.797, 0.805, 0.758, 0.742};
    int i = 0;

    @Override
    public double getValue() {
        if (i < mas.length - 1) {
            i++;
        } else {
            i = 0;
        }

        double result = 60/mas[i];

        if (result > treshhold) {
            Bio.heartRythmGraph.setColor("heartRate", new RGB(255, 0, 0));
        } else {
            Bio.heartRythmGraph.setColor("heartRate", new RGB(0, 255, 50));
        }

        return result;
    }

    @Override
    public double getMaxValue() {
        return 200;
    }
}
