package mcli.example;

import mcli.view.component.Label;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.View;

public class PolicyView extends View {
    @Override
    public void view() {
        component(
                Label.getBuilder()
                        .setText("Policy:")
                        .build(),
                Label.getBuilder()
                        .setText("We are not responsible for.......")
                        .build(),
                MultipleChoiceView.getBuilder()
                        .addQuestion("b", "go back", this::back)
                        .build()
        );
    }
}
