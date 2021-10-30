package example;

import mcli.component.TextField;
import mcli.views.MultipleChoiceView;
import mcli.views.ShortAnswerView;
import mcli.views.View;

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
