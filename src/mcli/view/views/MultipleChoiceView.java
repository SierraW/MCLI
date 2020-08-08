package mcli.view.views;

import mcli.view.model.DescribableFunction;
import mcli.view.model.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MultipleChoiceView extends View {
    private final ArrayList<String> keyList;
    private final HashMap<String, DescribableFunction> commandMap;
    private String error;

    public MultipleChoiceView() {
        keyList = new ArrayList<>();
        commandMap = new HashMap<>();
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
    public void view() {
        Label(this::getPrompt);
        SelectionField(this::getKeySet, this::command);
    }

    void command(String comm) {
        commandMap.get(comm).apply();
    }

    Set<String> getKeySet() {
        return commandMap.keySet();
    }

    String getPrompt() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        if (keyList != null)
            for (String key : keyList) {
                DescribableFunction value = commandMap.get(key);
                if (value.describe() != null)
                    if (isFirst) {
                        sb.append(key).append(": ").append(value.describe());
                        isFirst = false;
                    } else
                        sb.append("\n").append(key).append(": ").append(value.describe());
        }
        return sb.toString();
    }
}
