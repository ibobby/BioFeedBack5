import org.mihalis.opal.systemMonitor.Sample;

import java.util.Random;

/**
 * Created by WhiteMountiens on 03.08.2014.
 */


    public class RandomSample implements Sample {

        @Override
        public double getValue() {
            return new Random().nextInt(100);
        }

        @Override
        public double getMaxValue() {
            return 99d;
        }

    }


