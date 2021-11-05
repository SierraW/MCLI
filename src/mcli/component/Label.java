package mcli.component;

import mcli.model.Binding;
import mcli.model.TextLayer;

/**
 * A class that implements TextLayer sets texts
 */
public class Label extends TextLayer {
    private String text;
    private Binding<String> bindingString;
    private boolean nextLine = true;

    /**
     * constructor of the class with only one string
     * @param text string to label
     */

    public Label() {
        this.text = "";
    }

    public Label(String text) {
        if (text != null)
            this.text = text;
    }

    /**
     * constructor of the class with multiple string
     * @param stringBinding Binding<String>
     */
    public Label(Binding<String> stringBinding) {
        this.bindingString = stringBinding;
    }

    /**
     * The interface to build with text
     */
    public interface Builder {
        Builder setText(String text);
        Builder setText(Binding<String> text);
        Builder nextLine(boolean ln);
        Label build();
    }

    public Label setText(String text) {
        this.text = text;
        return this;
    }

    public Label setBindingString(Binding<String> bindingString) {
        this.bindingString = bindingString;
        return this;
    }

    public Label setNextLine(boolean nextLine) {
        this.nextLine = nextLine;
        return this;
    }

    /**
     * override to get the Builder
     * @return the set Builder
     */
    public static Builder getBuilder() {
        return new LabelBuilder();
    }

    /**
     * override the print method to bring the texts
     */
    @Override
    public void print() {
        String value;
        if (bindingString != null && bindingString.value() != null)
            value = bindingString.value();
        else if (text != null)
            value = text;
        else
            return;
        if (nextLine)
            System.out.println(value);
        else
            System.out.print(value);
    }
}
