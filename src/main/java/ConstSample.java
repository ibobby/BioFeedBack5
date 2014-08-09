import org.mihalis.opal.systemMonitor.Sample;

/**
 * Created by WhiteMountiens on 04.08.2014.
 */
public class ConstSample implements Sample {

    public static final int treshhold = 20;

    @Override
    public double getValue() {
        return treshhold;
    }

    @Override
    public double getMaxValue() {
        return 100;
    }
}
