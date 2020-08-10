package mcli.view.component;

import mcli.view.model.Binding;
import mcli.view.model.TextLayer;

public class Label implements TextLayer {
    private String text;
    private Binding<String> bindingString;

    Label(String text) {
        if (text != null)
            this.text = text;
    }

    Label(Binding<String> stringBinding) {
        this.bindingString = stringBinding;
    }

    public interface Builder {
        Builder setText(String text);
        Builder setText(Binding<String> text);
        Label build();
    }

    public static Builder getBuilder() {
        return new LabelBuilder();
    }

    @Override
    public void print() {
        if (bindingString != null && bindingString.value() != null)
            System.out.println(bindingString.value());
        if (text != null)
            System.out.println(text);
    }
}
