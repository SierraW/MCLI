package mcli.view.views;

import mcli.view.component.Label;

public class ProgressBar extends View {
    public interface DataSource {
        int getCurrent();
        int getTotal();
        boolean enable();
    }

    private int barLength;
    private DataSource dataSource;

    ProgressBar(DataSource dataSource, int barLength) {
        this.dataSource = dataSource;
        this.barLength = barLength;
        view();
    }

    public interface Builder {
        Builder setDataSource(DataSource dataSource);
        Builder setBarLength(int barLength);
        ProgressBar build();
    }

    public static Builder getBuilder() {
        return new ProgressBarBuilder();
    }

    private String getProgressBar(int current, int total, int barLength) {
        assert barLength > 1;
        assert current <= total;
        StringBuilder progressBar = new StringBuilder("[");
        double percentage = (double)current / (double) total;
        int displayLen = (int)(percentage * (double) barLength);
        for (int i=0; i<barLength; i++) {
            if (i < displayLen) {
                progressBar.append("=");
            } else if (i == displayLen) {
                progressBar.append(">");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("] ");
        progressBar.append(String.format("%.2f", percentage * 100)).append("%");
        progressBar.append("\r");
        return progressBar.toString();
    }


    @Override
    public void view() {
        component(Label.getBuilder().setText(this::toString).build());
    }

    @Override
    public String toString() {
        if (dataSource != null && dataSource.enable())
            if (dataSource.getTotal() > 0 && dataSource.getCurrent() <= dataSource.getTotal())
                return getProgressBar(dataSource.getCurrent(), dataSource.getTotal(), barLength);
        return null;
    }
}
