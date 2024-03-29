package mcli.component;

import mcli.model.Binding;

/**
 * A class implements the Builder interface of Label
 */
public class LabelBuilder implements Label.Builder {
    Label label = new Label("");

    /**
     * to set text when only one text
     * @param text string to set
     * @return label with text
     */
    @Override
    public Label.Builder setText(String text) {
        label.setText(text);
        return this;
    }

    /**
     * to set text with multiple text
     * @param text Binding<String>
     * @return label with text
     */
    @Override
    public Label.Builder setText(Binding<String> text) {
        label.setBindingString(text);
        return this;
    }

    @Override
    public Label.Builder nextLine(boolean ln) {
        label.setNextLine(ln);
        return this;
    }

    /**
     * build the Label with set parameters
     * @return the set Label
     */
    @Override
    public Label build() {
        return label;
    }
}
