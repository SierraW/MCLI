package mcli.test;

import mcli.view.views.View;

public class PageTwoView extends View {
    @Override
    public void view() {
        Label("Page Two!");
        MultipleChoiceView()
                .addQuestion("b","go back", this::back);
    }
}
