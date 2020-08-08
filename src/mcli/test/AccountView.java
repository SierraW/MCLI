package mcli.test;

import mcli.view.component.View;
import mcli.view.model.Binding;

public class AccountView extends View {

    private String username;

    public AccountView(String username) {
        this.username = username;
    }

    @Override
    public void view() {
        Label(this::getUsername);
        MultipleChoiceView()
                .addQuestion("b", "log out", this::back);
    }

    public String getUsername() {
        return "Hello! " + username;
    }
}
