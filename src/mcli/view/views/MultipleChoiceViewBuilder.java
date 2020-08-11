package mcli.view.views;

import mcli.view.model.Binding;
import mcli.view.model.DescribableFunction;
import mcli.view.model.Function;
import mcli.view.model.StringValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleChoiceViewBuilder implements MultipleChoiceView.Builder {
    private final List<String> keyList = new ArrayList<>();
    private final Map<String, DescribableFunction> commandMap = new HashMap<>();
    private StringValidator error;
    private boolean hideKey;

    @Override
    public MultipleChoiceViewBuilder setError(StringValidator error) {
        this.error = error;
        return this;
    }

    @Override
    public MultipleChoiceViewBuilder addQuestion(String choice, String question, Function onSubmit) {
        return addQuestion(choice, () -> question, onSubmit);
    }

    @Override
    public MultipleChoiceViewBuilder addQuestion(String choice, Binding<String> bindingString, Function onSubmit) {
        commandMap.put(choice, new DescribableFunction() {
            @Override
            public String value() {
                return bindingString.value();
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
    public MultipleChoiceView.Builder hideKey(boolean hideKey) {
        this.hideKey = hideKey;
        return this;
    }

    @Override
    public MultipleChoiceView build() {
        assert keyList.size() > 0;
        return new MultipleChoiceView(keyList, commandMap, error, hideKey);
    }
}
