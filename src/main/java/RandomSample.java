import org.eclipse.swt.graphics.RGB;
import org.mihalis.opal.systemMonitor.Sample;

import java.util.Random;

/**
 * Created by WhiteMountiens on 03.08.2014.
 */


    public class RandomSample implements Sample {

    int treshhold;

    public RandomSample(int treshhold) {
        this.treshhold = treshhold;
    }

    public RandomSample() {

    }

    @Override
        public double getValue() {
            int newValue = (int) (treshhold + Math.pow(-1, (int)System.nanoTime()) * new Random().nextInt(5));

            if (newValue > treshhold) {
                Bio.respirationGraph.setColor("const", new RGB(0, 255, 50));
            } else {
                Bio.respirationGraph.setColor("const", new RGB(255, 0, 0));
            }

            return newValue;
        }

        @Override
        public double getMaxValue() {
            return 120d;
        }

    }


