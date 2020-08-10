package mcli.view.views;

import mcli.view.component.Label;
import mcli.view.component.TextField;
import mcli.view.model.Binding;
import mcli.view.model.StringValidator;

import javax.xml.validation.Validator;
import java.util.ArrayList;


public class ShortAnswerView extends View implements ProgressBar.DataSource {
    private final ShortAnswerOnSuccess onSuccess;
    private final ArrayList<String> questions;
    private final ArrayList<StringValidator> validators;
    private final ArrayList<String> input = new ArrayList<>();
    private final StringValidator error;
    private final boolean displayProgressBar;

    ShortAnswerView(ArrayList<String> questions, ArrayList<StringValidator> validators, StringValidator error, ShortAnswerOnSuccess onSuccess, boolean displayProgressBar) {
        this.questions = questions;
        this.validators = validators;
        this.error =error;
        this.onSuccess = onSuccess;
        this.displayProgressBar = displayProgressBar;
    }

    public interface Builder {
        Builder addQuestion(String question, String validationRegEx);
        Builder addQuestion(String question, StringValidator stringValidator);
        Builder setError(StringValidator error);
        Builder onSuccess(ShortAnswerOnSuccess onSuccess);
        Builder showProgressBar(boolean show);
        ShortAnswerView build();
    }

    public static Builder getBuilder() {
        return new ShortAnswerViewBuilder();
    }

    @Override
    public int getCurrent() {
        return input.size();
    }

    @Override
    public int getTotal() {
        return questions.size();
    }

    @Override
    public boolean enable() {
        return displayProgressBar;
    }


    public interface ShortAnswerOnSuccess {
        void onSuccess(String[] input);
    }

    @Override
    public void view() {
        component(
                ProgressBar.getBuilder()
                        .setBarLength(50)
                        .setDataSource(this)
                        .build()
        );
        component(
                Label.getBuilder()
                        .setText(this::printQuestion)
                        .build()
        );
        component(
                TextField.getBuilder()
                        .addValidator(this::validate)
                        .onFill(this::getResult)
                        .setError(this::error)
                        .build()
        );
    }

    private String printQuestion() {
        return questions.get(input.size());
    }

    private boolean validate(String comm) {
        return validators.get(input.size()).validate(comm);
    }

    private void getResult(String result) {
        input.add(result);
        if (input.size() == questions.size()) {
            onSuccess.onSuccess(input.toArray(String[]::new));
            input.clear();
        }
    }

    private boolean error(String comm) {
        if (error!=null) {
            error.validate(comm);
            return true;
        }
        return false;
    }

}
