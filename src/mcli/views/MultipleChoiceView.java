package mcli.views;

import mcli.component.Label;
import mcli.component.SelectionField;
import mcli.model.*;

import java.util.List;
import java.util.Map;

/**
 * The class that extends View to view with multiple choice answer
 */
public class MultipleChoiceView extends View {
    private final List<String> keyList;
    private final Map<String, DescribableFunction> commandMap;
    private final StringValidator error;
    private final boolean hideCommandKey;

    /**
     * the constructor of the class
     * @param keyList List<String>
     * @param commandMap Map<String, DescribableFunction>
     * @param error StringValidator
     * @param hideCommandKey boolean
     */
    MultipleChoiceView(List<String> keyList, Map<String, DescribableFunction> commandMap, StringValidator error, boolean hideCommandKey) {
        this.keyList = keyList;
        this.commandMap = commandMap;
        this.error = error;
        this.hideCommandKey = hideCommandKey;
        view();
    }

    /**
     * The interface to build for MultipleChoiceView
     */
    public interface Builder {
        Builder setError(StringValidator error);

        Builder addQuestion(String choice, String question, Function onSubmit);

        Builder addQuestion(String choice, Binding<String> bindingString, Function onSubmit);

        Builder hideKey(boolean hide);

        MultipleChoiceView build();
    }

    /**
     * override to get the Builder
     * @return the set Builder
     */
    public static Builder getBuilder() {
        return new MultipleChoiceViewBuilder();
    }

    /**
     * override to view
     */
    @Override
    public void view() {
        component(Label.getBuilder().setText(this::getPrompt).build());
        component(SelectionField.getBuilder()
                .setDataSource(commandMap::keySet)
                .onFill(comm -> commandMap.get(comm).apply())
                .setError(this::error)
                .build()
        );
    }

    /**
     * override to set the error
     * @param comm string command
     * @return boolean
     */
    private boolean error(String comm) {
        if (error!=null) {
            error.validate(comm);
            return true;
        }
        return false;
    }

    /**
     * get StringBuilder() into string
     * @return key set as string
     */
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
                    if (!hideCommandKey) {
                        sb.append(key).append(": ");
                    }
                    sb.append(value.value());
                }
            }
        return sb.toString().equals("") ? null : sb.toString();
    }
}
