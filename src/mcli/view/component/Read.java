package mcli.view.component;

import mcli.view.model.InputValidation;
import mcli.view.model.StateFunction;

public abstract class Read {

    abstract boolean read(String comm);

    abstract Read onFill(StateFunction onFill);

    abstract Read setError(InputValidation error);

}
