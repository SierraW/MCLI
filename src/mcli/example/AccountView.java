package mcli.example;

import mcli.view.component.Label;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.View;

public class AccountView extends View {

    private final String username;

    public AccountView(String username) {
        this.username = username;
    }

    @Override
    public void view() {
        component(
                Label.getBuilder()
                .setText(this::getUsername)
                .build()
        );
        component(
                MultipleChoiceView.getBuilder()
                        .addQuestion("b", "log out", this::back)
                .build()
        );
    }

    public String getUsername() {
        return "Hello! " + username;
    }
}
