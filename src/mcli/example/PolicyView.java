package mcli.example;

import mcli.view.component.View;

public class PolicyView extends View {
    @Override
    public void view() {
        Label("Policy:");
        Label("We are not responsible for .......");
        MultipleChoiceView()
                .addQuestion("b","go back", this::back);
    }
}
