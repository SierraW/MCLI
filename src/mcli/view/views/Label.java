package mcli.view.views;

public class Label extends View {

    private String text;

    public Label(String text) {
        this.text = text;
    }

    @Override
    boolean isDisplayOnlyView() {
        return true;
    }

    @Override
    public void view() {

    }

    @Override
    boolean show(String comm) {
        System.out.println(text);
        return false;
    }
}
