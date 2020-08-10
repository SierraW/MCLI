package mcli.view.component;

import mcli.view.model.InputValidation;
import mcli.view.model.StateFunction;

import java.util.regex.Pattern;

public class TextField extends Read {
    private StateFunction onFill;
    private InputValidation validation;
    private InputValidation error;
    public final static String STR_REGEX = "^[a-zA-Z0-9]{1,32}$";
    public final static String STR_SPACE_REGEX = "^[a-zA-Z0-9 ]{1,32}$";

    @Override
    public TextField onFill(StateFunction onFill) {
        this.onFill = onFill;
        return this;
    }


    public TextField addValidation(InputValidation validation) {
        this.validation = validation;
        return this;
    }

    @Override
    public TextField setError(InputValidation error) {
        this.error = error;
        return this;
    }

    @Override
    boolean read(String comm) {
        if (validation == null || validation.validate(comm)) {
            onFill.run(comm);
            return true;
        } else {
            if (error != null) {
                return error.validate(comm);
            }
            return false;
        }
    }

    public static boolean validate(String input, String regEx) {
        if (input == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(input).matches();
    }
}
