package mcli.test;

import mcli.view.views.Environment;
import mcli.view.views.View;

public class TextFieldTestView extends View {

    @Override
    public void view() {
        Label("Account:");
        Label("username");
        TextField(Environment.STR_REGEX, (username) -> {
           Label("password");
           TextField(Environment.STR_REGEX, (password) -> {
               System.out.printf("Username %s, Password %s\n", username, password);
               back();
           });
        });
    }
}
