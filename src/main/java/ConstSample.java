import org.mihalis.opal.systemMonitor.Sample;

/**
 * Created by WhiteMountiens on 04.08.2014.
 */
public class ConstSample implements Sample {

    public int treshhold;

    public int getTreshhold() {
        return treshhold;
    }

    public ConstSample(int treshhold) {
        this.treshhold = treshhold;
    }

    @Override
    public double getValue() {
        return treshhold;
    }

    @Override
    public double getMaxValue() {
        return 120;
    }
}
