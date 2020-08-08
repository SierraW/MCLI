package mcli.view.views;

import mcli.view.model.Function;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ShortAnswerView extends View {
    private Function onSuccess;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> validation = new ArrayList<>();
    private ArrayList<String> input;
    private String error;

    public ShortAnswerView(ArrayList<String> input, Function onSuccess) {
        this.onSuccess = onSuccess;
        this.input = input;
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
    boolean isDisplayOnlyView() {
        return false;
    }

    @Override
    public void view() {

    }

    @Override
    boolean show(String comm) {
        if (comm == null) {
            System.out.println(questions.get(input.size()));
            return false;
        }
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
            onSuccess.apply();
            input.clear();
        }
        return true;
    }

    private boolean validate(String input, String regEx) {
        if (input == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(input).matches();
    }
}
