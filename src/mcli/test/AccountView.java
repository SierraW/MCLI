package mcli.test;

import mcli.view.component.Environment;
import mcli.view.component.ProgressBar;
import mcli.view.component.TextField;
import mcli.view.component.View;

public class AccountView extends View {

    private int count = 1;

    @Override
    public void view() {
        MultipleChoiceView()
                .addQuestion("b", "go back", this::back);
        ShortAnswerView()
                .addQuestion("Please enter your username (b to go back):", (comm) -> {
                    count++;
                    System.out.println(count);
                    return TextField.validate(comm, TextField.STR_REGEX);
                })
                .addQuestion("Please enter your password (b to go back):", TextField.STR_REGEX)
                .onSuccess((input) -> {
                    System.out.printf("Your input is %s, %s\n", input[0], input[1]);
                    back();
                })
                .showProgressBar(true);
    }
}