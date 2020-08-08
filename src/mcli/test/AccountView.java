package mcli.test;

import mcli.view.views.Environment;
import mcli.view.views.View;

import java.util.ArrayList;

public class AccountView extends View {


    @Override
    public void view() {
        MultipleChoiceView()
                .addQuestion("b", null, this::back);
        ShortAnswerView((input) -> {
            System.out.printf("Your input is %s, %s\n", input[0], input[1]);
            back(); })
                .addQuestion("Please enter your username (b to go back):", Environment.STR_REGEX)
                .addQuestion("Please enter your password (b to go back):", Environment.STR_REGEX);
    }
}
