package mcli.example;

import mcli.view.component.TextField;
import mcli.view.component.View;

public class LoginView extends View {

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
                .onSuccess((input) -> redirect(new AccountView(input[0])))
                .showProgressBar(true);
    }

//    Generic approach
//    @Override
//    public void view() {
//        View(new Label("Hello!"));
//        ((MultipleChoiceView)View(new MultipleChoiceView()))
//                .addQuestion("b", "go back", this::back);
//        ((ShortAnswerView)View(new ShortAnswerView()))
//                .addQuestion("Please enter your username (b to go back):", (comm) -> {
//                        count++;
//                        System.out.println(count);
//                        return TextField.validate(comm, TextField.STR_REGEX);
//                    })
//                .addQuestion("Please enter your password (b to go back):", TextField.STR_REGEX)
//                .onSuccess((input) -> redirect(new AccountView(input[0])))
//                .showProgressBar(true);
//    }
}
