package akka.prime.message;

import java.io.Serializable;

/**
 * Created by thecodemaker on 3/3/14.
 */
public class NumberRangeMessage implements Serializable {

    private long startNumber;
    private long endNumber;

    public NumberRangeMessage(long startNumber, long endNumber) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    public long getStartNumber() {
        return startNumber;
    }

    public long getEndNumber() {
        return endNumber;
    }

    @Override
    public String toString() {
        return "NumberRangeMessage{" +
                "startNumber=" + startNumber +
                ", endNumber=" + endNumber +
                '}';
    }
}
