package mcli.example;

import mcli.view.component.TextField;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.ShortAnswerView;
import mcli.view.views.View;

public class LoginView extends View {
    private final String regExForString = "^[a-zA-Z0-9]{1,32}$";
    @Override
    public void view() {
        component(
                MultipleChoiceView
                .getBuilder()
                        .addQuestion("b", "go back", this::back)
                .build(),
                ShortAnswerView
                .getBuilder()
                        .addQuestion("Please enter your username (b to go back):", (comm) -> TextField.validate(comm, regExForString))
                        .addQuestion("Please enter your password (b to go back):", regExForString)
                        .onSuccess((input) -> redirect(new AccountView(input[0])))
                        .showProgressBar(true)
                .build()
        );
    }
}
