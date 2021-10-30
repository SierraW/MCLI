package example;

import mcli.component.Label;
import mcli.views.MultipleChoiceView;
import mcli.views.View;

public class HelloWorldView extends View {
    @Override
    public void view() {
        component(
                Label.getBuilder()
                .setText("Hello world!")
                .build(),
                Label.getBuilder()
                .setText("What's next?")
                .build(),
                MultipleChoiceView.getBuilder()
                        .addQuestion("1", "View policy", () -> redirect(new PolicyView()))
                        .addQuestion("2", "Login", () -> redirect(new LoginView()))
                        .addQuestion("q", "quit", () -> System.exit(0))
                .build()
        );
    }
}
