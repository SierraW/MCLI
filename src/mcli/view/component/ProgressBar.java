package mcli.view.component;

public class ProgressBar extends View {
    public interface DataSource {
        int getCurrent();
        int getTotal();
        boolean enable();
    }

    private int barLength = 120;
    private DataSource dataSource;

    public ProgressBar setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public ProgressBar setBarLength(int barLength) {
        this.barLength = barLength;
        return this;
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
        Label(() -> {
            if (dataSource != null && dataSource.enable())
                return getProgressBar(dataSource.getCurrent(), dataSource.getTotal(), barLength);
            else
                return null;
        });
    }
}
