package mcli.view.component;

import mcli.view.model.InputLayer;
import mcli.view.model.StringValidator;
import mcli.view.model.CommandFunction;

import java.util.Set;

public class SelectionField implements InputLayer {

    public interface DataSource {
        Set<String> getSelections();
    }

    private final DataSource dataSource;
    private final CommandFunction onFill;
    private final StringValidator error;

    SelectionField(DataSource dataSource, CommandFunction onFill, StringValidator error) {
        this.dataSource = dataSource;
        this.onFill = onFill;
        this.error = error;
    }

    public interface Builder {
        Builder setDataSource(DataSource dataSource);
        Builder onFill(CommandFunction onFill);
        Builder setError(StringValidator error);
        SelectionField build();
    }

    public static Builder getBuilder() {
        return new SelectionFieldBuilder();
    }

    @Override
    public boolean read(String comm) {
        if (dataSource.getSelections().contains(comm)) {
            onFill.run(comm);
            return true;
        } else {
            if (error != null) {
                return error.validate(comm);
            }
            return false;
        }
    }
}
