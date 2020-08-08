package mcli.view.views;

import mcli.view.model.Function;
import mcli.view.model.StateFunction;

import java.util.regex.Pattern;

public class TextField extends View {
    private StateFunction onFill;
    private String regEx;

    public TextField(String validatorRegEx, StateFunction onFill) {
        this.onFill = onFill;
        this.regEx = validatorRegEx;
    }

    @Override
    public void view() {

    }

    @Override
    boolean read(String comm) {
        if (validate(comm, regEx)) {
            onFill.run(comm);
            return true;
        } else {
            return false;
        }
    }

    private boolean validate(String input, String regEx) {
        if (input == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(input).matches();
    }
}
