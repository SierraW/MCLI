package mcli.view.views;

import mcli.view.component.TextField;
import mcli.view.model.StringValidator;

import java.util.ArrayList;

public class ShortAnswerViewBuilder implements ShortAnswerView.Builder {
    private ShortAnswerView.ShortAnswerOnSuccess onSuccess;
    private final ArrayList<String> questions = new ArrayList<>();
    private final ArrayList<StringValidator> validators = new ArrayList<>();
    private StringValidator error;
    private boolean displayProgressBar = false;

    @Override
    public ShortAnswerViewBuilder onSuccess(ShortAnswerView.ShortAnswerOnSuccess success) {
        this.onSuccess = success;
        return this;
    }

    @Override
    public ShortAnswerViewBuilder setError(StringValidator error) {
        this.error = error;
        return this;
    }

    @Override
    public ShortAnswerViewBuilder addQuestion(String question, String validationRegEx) {
        questions.add(question);
        validators.add((comm) -> TextField.validate(comm, validationRegEx));
        return this;
    }

    @Override
    public ShortAnswerViewBuilder addQuestion(String question, StringValidator validation) {
        questions.add(question);
        validators.add(validation);
        return this;
    }

    @Override
    public ShortAnswerViewBuilder showProgressBar(boolean displayProgressBar) {
        this.displayProgressBar = displayProgressBar;
        return this;
    }

    @Override
    public ShortAnswerView build() {
        assert onSuccess != null;
        assert questions.size() > 0;
        assert validators.size() > 0;
        return new ShortAnswerView(questions, validators, error, onSuccess, displayProgressBar);
    }
}
