package example;

import mcli.component.Label;
import mcli.views.MultipleChoiceView;
import mcli.views.View;

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
