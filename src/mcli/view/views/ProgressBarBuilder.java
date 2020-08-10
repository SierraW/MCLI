package mcli.view.views;

public class ProgressBarBuilder implements ProgressBar.Builder {

    private ProgressBar.DataSource dataSource;
    private int barLength = 70;

    @Override
    public ProgressBarBuilder setDataSource(ProgressBar.DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    @Override
    public ProgressBarBuilder setBarLength(int barLength) {
        this.barLength = barLength;
        return this;
    }

    @Override
    public ProgressBar build() {
        assert dataSource != null;
        return new ProgressBar(dataSource, barLength);
    }
}
