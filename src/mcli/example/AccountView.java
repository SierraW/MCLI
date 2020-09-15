package mcli.example;

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
