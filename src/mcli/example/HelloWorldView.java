package mcli.example;

import mcli.view.component.View;

public class HelloWorldView extends View {
    @Override
    public void view() {
        Label("Hello world!");
        Label("What's next?");
        MultipleChoiceView()
                .addQuestion("1", "View policy", () -> redirect(new PolicyView()))
                .addQuestion("2", "Login", () -> redirect(new LoginView()))
                .addQuestion("q", "quit", () -> System.exit(0));
    }
}
