package mcli.view.views;

import mcli.view.model.Function;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ShortAnswerView extends View {
    private ShortAnswerOnSuccess onSuccess;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> validation = new ArrayList<>();
    private ArrayList<String> input = new ArrayList<>();
    private String error;

    public interface ShortAnswerOnSuccess {
        void onSuccess(String[] input);
    }

    public ShortAnswerView(ShortAnswerOnSuccess onSuccess) {
        this.onSuccess = onSuccess;
    }

    public ShortAnswerView addQuestion(String question, String validationRegEx) {
        questions.add(question);
        validation.add(validationRegEx);
        return this;
    }

    public ShortAnswerView setError(String error) {
        this.error = error;
        return this;
    }

    @Override
    public void view() {

    }

    @Override
    public boolean read(String comm) {
        if (!validate(comm, validation.get(input.size()))) {
            if (error != null) {
                System.out.println(error);
                return true;
            }
            return false;
        } else {
            input.add(comm);
        }
        if (input.size() == questions.size()) {
            onSuccess.onSuccess(input.toArray(new String[0]));
            input.clear();
        }
        return true;
    }

    @Override
    void show() {
        System.out.println(questions.get(input.size()));
    }

    private boolean validate(String input, String regEx) {
        if (input == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(input).matches();
    }
}
