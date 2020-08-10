package mcli.view.component;

import mcli.view.model.InputValidation;
import mcli.view.model.StateFunction;

import java.util.Set;

public class SelectionField extends Read{

    public interface DataSource {
        Set<String> getSelections();
    }

    private DataSource dataSource;
    private StateFunction onFill;
    private InputValidation error;

    public SelectionField setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    @Override
    public SelectionField onFill(StateFunction onFill) {
        this.onFill = onFill;
        return this;
    }

    @Override
    public SelectionField setError(InputValidation error) {
        this.error = error;
        return this;
    }

    @Override
    boolean read(String comm) {
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
