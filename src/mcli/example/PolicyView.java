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
                        .build()
        );
        component(
                Label.getBuilder()
                        .setText("We are not responsible for.......")
                        .build()
        );
        component(
                MultipleChoiceView.getBuilder()
                        .addQuestion("b", "go back", this::back)
                        .build()
        );
    }
}
