package mcli.view.views;

import mcli.view.model.CommandMap;
import mcli.view.model.DescribableFunction;
import mcli.view.model.Function;

import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoiceView extends View {

    private final String prompt;
    private final ArrayList<String> keyList;
    private final HashMap<String, DescribableFunction> commandMap;
    private String error;

    public MultipleChoiceView(String prompt) {
        keyList = new ArrayList<>();
        commandMap = new HashMap<>();
        this.prompt = prompt;
    }

    public MultipleChoiceView addQuestion(String choice, String question, Function onSubmit) {
        commandMap.put(choice, new DescribableFunction() {
            @Override
            public String describe() {
                return question;
            }

            @Override
            public void apply() {
                onSubmit.apply();
            }
        });
        keyList.add(choice);
        return this;
    }

    public MultipleChoiceView setError(String error) {
        this.error = error;
        return this;
    }

    @Override
    boolean isDisplayOnlyView() {
        return false;
    }

    @Override
    public void view() {

    }

    @Override
    boolean show(String comm) {
        if (comm == null) {
            if (prompt != null) {
                System.out.println(prompt);
            }
            for (String key : keyList) {
                DescribableFunction value = commandMap.get(key);
                if (value.describe() != null)
                    System.out.println(key + ": " +value.describe());
            }
            return false;
        }
        if (!commandMap.containsKey(comm)) {
            if (error != null) {
                System.out.println(error);
                return true;
            }
            return false;
        }
        commandMap.get(comm).apply();
        return true;
    }
}
