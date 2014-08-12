import org.eclipse.swt.graphics.RGB;
import org.mihalis.opal.systemMonitor.Sample;

import java.util.Random;

/**
 * Created by WhiteMountiens on 03.08.2014.
 */


    public class RandomSample implements Sample {

        @Override
        public double getValue() {
            int newValue = (int) (ConstSample.treshhold + Math.pow(-1, (int)System.nanoTime()) * new Random().nextInt(5));

            if (newValue > ConstSample.treshhold) {
                Bio.custom.setColor("const", new RGB(0, 255, 50));
            } else {
                Bio.custom.setColor("const", new RGB(255, 0, 0));
            }

            return newValue;
        }

        @Override
        public double getMaxValue() {
            return 40d;
        }

    }


