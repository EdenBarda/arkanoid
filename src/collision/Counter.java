package collision;

/**
 * the counter.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Counter {
    private int counter;

    /**
     * counter constructor.
     *
     * @param counter the counter.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * Add number to current count.
     *
     * @param number the number that need to add to the counter.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the number that need to decrease from the counter.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }

    /**
     * Return Integer type of get value.
     *
     * @return Integer type of get value.
     */
    public Integer getIntegerValue() {
        return this.counter;
    }
}