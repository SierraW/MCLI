package mcli.view.views;

import mcli.view.model.Describable;

public class Label extends View {

    private String text;
    private Describable describable;

    public Label(String text) {
        if (text != null)
            this.text = text;
    }

    public Label(Describable stringBinding) {
        this.describable = stringBinding;
    }

    @Override
    public void view() {

    }

    @Override
    void show() {
        if (describable != null)
            System.out.println(describable.describe());
        if (text != null)
            System.out.println(text);
    }
}
