package mcli.example;

import mcli.view.component.Label;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.View;

public class HelloWorldView extends View {
    @Override
    public void view() {
        component(
                Label.getBuilder()
                .setText("Hello world!")
                .build()
        );
        component(
                Label.getBuilder()
                .setText("What's next?")
                .build()
        );
        component(
                MultipleChoiceView.getBuilder()
                        .addQuestion("1", "View policy", () -> redirect(new PolicyView()))
                        .addQuestion("2", "Login", () -> redirect(new LoginView()))
                        .addQuestion("q", "quit", () -> System.exit(0))
                .build()
        );
    }
}
