import org.mihalis.opal.systemMonitor.Sample;

/**
 * Created by WhiteMountiens on 04.08.2014.
 */
public class ConstSample implements Sample {
    @Override
    public double getValue() {
        return 50;
    }

    @Override
    public double getMaxValue() {
        return 100;
    }
}
