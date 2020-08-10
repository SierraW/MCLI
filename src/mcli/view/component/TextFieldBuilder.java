package mcli.view.component;

import mcli.view.model.CommandFunction;
import mcli.view.model.StringValidator;

public class TextFieldBuilder implements TextField.Builder {
    private CommandFunction onFill;
    private StringValidator validator;
    private StringValidator error;

    @Override
    public TextField.Builder onFill(CommandFunction onFill) {
        this.onFill = onFill;
        return this;
    }

    @Override
    public TextField.Builder addValidator(StringValidator validator) {
        this.validator = validator;
        return this;
    }

    @Override
    public TextField.Builder setError(StringValidator error) {
        this.error = error;
        return this;
    }

    @Override
    public TextField build() {
        assert onFill != null;
        assert validator != null;
        return new TextField(onFill, validator, error);
    }
}
