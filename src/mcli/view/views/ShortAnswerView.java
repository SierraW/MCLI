package mcli.view.views;

import mcli.view.component.ProgressBar;
import mcli.view.component.TextField;
import mcli.view.component.View;
import mcli.view.model.InputValidation;

import java.util.ArrayList;


public class ShortAnswerView extends View implements ProgressBar.DataSource {
    private ShortAnswerOnSuccess onSuccess;
    private final ArrayList<String> questions = new ArrayList<>();
    private final ArrayList<InputValidation> validationList = new ArrayList<>();
    private final ArrayList<String> input = new ArrayList<>();
    private InputValidation error;
    private boolean displayProgressBar = false;

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

    public ShortAnswerView onSuccess(ShortAnswerOnSuccess success) {
        this.onSuccess = success;
        return this;
    }

    public ShortAnswerView setError(InputValidation error) {
        this.error = error;
        return this;
    }

    public ShortAnswerView addQuestion(String question, String validationRegEx) {
        questions.add(question);
        validationList.add((comm) -> TextField.validate(comm, validationRegEx));
        return this;
    }

    public ShortAnswerView addQuestion(String question, InputValidation validation) {
        questions.add(question);
        validationList.add(validation);
        return this;
    }

    public ShortAnswerView showProgressBar(boolean displayProgressBar) {
        this.displayProgressBar = displayProgressBar;
        return this;
    }

    @Override
    public void view() {
        ProgressBar()
                .setBarLength(60)
                .setDataSource(this);
        Label(() -> questions.get(input.size()));
        TextField()
                .addValidation((comm) -> validationList.get(input.size()).validate(comm))
                .onFill((comm) -> {
                        input.add(comm);
                        if (input.size() == questions.size()) {
                            onSuccess.onSuccess(input.toArray(String[]::new));
                            input.clear();
                        }
                    })
                .setError(this::error);
    }

    private boolean error(String comm) {
        if (error!=null) {
            error.validate(comm);
            return true;
        }
        return false;
    }

}
