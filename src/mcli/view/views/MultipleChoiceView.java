package mcli.view.views;

import mcli.view.component.View;
import mcli.view.model.Describable;
import mcli.view.model.DescribableFunction;
import mcli.view.model.Function;
import mcli.view.model.InputValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MultipleChoiceView extends View {
    private final ArrayList<String> keyList = new ArrayList<>();
    private final HashMap<String, DescribableFunction> commandMap = new HashMap<>();
    private InputValidation error;

    public MultipleChoiceView setError(InputValidation error) {
        this.error = error;
        return this;
    }

    public MultipleChoiceView addQuestion(String choice, String question, Function onSubmit) {
        return addQuestion(choice, () -> question, onSubmit);
    }

    public MultipleChoiceView addQuestion(String choice, Describable describable, Function onSubmit) {
        commandMap.put(choice, new DescribableFunction() {
            @Override
            public String describe() {
                return describable.describe();
            }

            @Override
            public void apply() {
                onSubmit.apply();
            }
        });
        keyList.add(choice);
        return this;
    }

    @Override
    public void view() {
        Label(this::getPrompt);
        SelectionField()
                .setDataSource(this::getKeySet)
                .onFill(this::command)
                .setError(this::error);
    }

    void command(String comm) {
        commandMap.get(comm).apply();
    }

    private boolean error(String comm) {
        if (error!=null) {
            error.validate(comm);
            return true;
        }
        return false;
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
