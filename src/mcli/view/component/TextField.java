package mcli.view.component;

import mcli.view.model.InputLayer;
import mcli.view.model.StringValidator;
import mcli.view.model.CommandFunction;

import java.util.regex.Pattern;

public class TextField implements InputLayer {
    private final CommandFunction onFill;
    private final StringValidator validator;
    private final StringValidator error;


    TextField(CommandFunction onFill, StringValidator validator, StringValidator error) {
        this.onFill = onFill;
        this.validator = validator;
        this.error = error;
    }

    public interface Builder {
        Builder onFill(CommandFunction onFill);
        Builder addValidator(StringValidator validator);
        Builder setError(StringValidator error);
        TextField build();
    }

    public static Builder getBuilder() {
        return new TextFieldBuilder();
    }

    @Override
    public boolean read(String comm) {
        if (validator == null || validator.validate(comm)) {
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
