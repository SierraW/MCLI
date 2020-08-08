package mcli.view.component;

import mcli.view.model.Binding;

public class Label extends View {

    private String text;
    private Binding<String> bindingString;

    public Label(String text) {
        if (text != null)
            this.text = text;
    }

    public Label(Binding<String> stringBinding) {
        this.bindingString = stringBinding;
    }

    @Override
    public void view() {

    }

    @Override
    void show() {
        if (bindingString != null && bindingString.value() != null)
            System.out.println(bindingString.value());
        if (text != null)
            System.out.println(text);
    }
}
