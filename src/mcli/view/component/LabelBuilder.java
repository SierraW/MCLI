package mcli.view.component;

import mcli.view.component.Label;
import mcli.view.model.Binding;

public class LabelBuilder implements Label.Builder {
    Label label = new Label("");

    @Override
    public Label.Builder setText(String text) {
        label = new Label(text);
        return this;
    }

    @Override
    public Label.Builder setText(Binding<String> text) {
        label = new Label(text);
        return this;
    }

    @Override
    public Label build() {
        return label;
    }
}
