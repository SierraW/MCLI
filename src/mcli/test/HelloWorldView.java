package mcli.test;

import mcli.view.views.Label;
import mcli.view.views.View;

public class HelloWorldView extends View {
    @Override
    public void view() {
        Label("Hello world!");
        Label("What's next?");
        MultipleChoiceView()
                .addQuestion("1", "Page 2!", () -> redirect(new PageTwoView()))
                .addQuestion("2", "Username and password", () -> redirect(new AccountView()))
                .addQuestion("3", "TextFieldTest", () -> redirect(new TextFieldTestView()))
                .addQuestion("q", "quit", () -> System.exit(0));
    }
}
