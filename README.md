# MCLI
Modularized Command Line Interface

Provide a quick and modern way to deploy a CLI.

Example
//setting up an navigation controller (you can call it router).

```java
import mcli.view.controller.NavigationViewController;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        NavigationViewController navigationViewController = new NavigationViewController();
        navigationViewController.setRootView(new YourRootView());
        navigationViewController.run(bufferedReader);
    }
}
```
//only one view will display each time. view can take multiple output/ input by assigning components.
```java
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
                        .onSuccess((input) -> {
                            if (true) { // validate info here, input is a string array, based on your question order.
                              redirect(new AccountView(input[0]); // if success, redirect.(example shows with username, however this is your choice.)
                            } else {
                              // after this, input array will clear and user will needs to start over.
                            }
                        }))
                        .showProgressBar(true)
                .build()
        );
    }
}
```
// more components are avaliable at views folder.
```java
import mcli.view.component.Label;
import mcli.view.views.ItemListView;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.View;

import java.util.ArrayList;

public class AccountView extends View {
    private final String username;
    private final ArrayList<Integer> integers;

    public AccountView(String username) {
        this.username = username;

        ArrayList<Integer> itemListView = new ArrayList<>();
        for (int i = 0 ; i < 20; i ++) {
            itemListView.add(i);
        }
        integers = itemListView;

    }

    public void view() {
        component(
                Label.getBuilder()
                .setText(username)
                .build(),
                MultipleChoiceView.getBuilder()
                        .addQuestion("b", "log out", this::back)
                        .addQuestion("+", "add one number", this::addNumber)
                .build(),
                new ItemListView<>(integers, true)
        );
    }

    public void addNumber() {
        integers.add(7);
    }

}
```
