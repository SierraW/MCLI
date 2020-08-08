package mcli.example;

import mcli.view.component.View;

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
