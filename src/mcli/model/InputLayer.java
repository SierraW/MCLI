package mcli.model;

/**
 * the interface with method read
 */
public abstract class InputLayer implements Component {
    public abstract boolean read(String comm);
}
