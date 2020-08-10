package mcli.view.component;

import mcli.view.model.CommandFunction;
import mcli.view.model.StringValidator;

public class SelectionFieldBuilder implements SelectionField.Builder {
    private SelectionField.DataSource dataSource;
    private CommandFunction onFill;
    private StringValidator error;

    @Override
    public SelectionField.Builder setDataSource(SelectionField.DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    @Override
    public SelectionField.Builder onFill(CommandFunction onFill) {
        this.onFill = onFill;
        return this;
    }

    @Override
    public SelectionField.Builder setError(StringValidator error) {
        this.error = error;
        return this;
    }

    @Override
    public SelectionField build() {
        assert onFill != null;
        assert dataSource != null;
        return new SelectionField(dataSource, onFill, error);
    }
}
