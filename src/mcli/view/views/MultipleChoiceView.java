package mcli.view.views;

import mcli.view.component.Label;
import mcli.view.component.SelectionField;
import mcli.view.model.Binding;
import mcli.view.model.DescribableFunction;
import mcli.view.model.Function;
import mcli.view.model.StringValidator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultipleChoiceView extends View {
    private final List<String> keyList;
    private final Map<String, DescribableFunction> commandMap;
    private final StringValidator error;

    MultipleChoiceView(List<String> keyList, Map<String, DescribableFunction> commandMap, StringValidator error) {
        this.keyList = keyList;
        this.commandMap = commandMap;
        this.error = error;
        view();
    }

    public interface Builder {
        Builder setError(StringValidator error);

        Builder addQuestion(String choice, String question, Function onSubmit);

        Builder addQuestion(String choice, Binding<String> bindingString, Function onSubmit);

        MultipleChoiceView build();
    }

    public static Builder getBuilder() {
        return new MultipleChoiceViewBuilder();
    }

    @Override
    public void view() {
        component(Label.getBuilder().setText(this::getPrompt).build());
        component(SelectionField.getBuilder()
                .setDataSource(this::getKeySet)
                .onFill(this::command)
                .setError(this::error)
                .build()
        );
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
                if (value.value() != null) {
                    if (!isFirst) {
                        sb.append("\n");
                    } else {
                        isFirst = false;
                    }
                    sb.append(key).append(": ").append(value.value());
                }
            }
        return sb.toString().equals("") ? null : sb.toString();
    }
}
