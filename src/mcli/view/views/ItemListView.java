package mcli.view.views;

import mcli.view.component.Label;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.View;

import java.util.ArrayList;
import java.util.List;

public class ItemListView<T> extends View implements ProgressBar.DataSource {
    private final List<T> itemList;
    private final int displayedItemPerPage = 5;
    private int pageIndex = 0;
    private int currentCount = 0;
    private final boolean displayProgressBar;

    public ItemListView(ArrayList<T> itemList) {
        this(itemList, false);
    }

    public ItemListView(ArrayList<T> itemList, boolean displayProgressBar) {
        this.itemList = itemList;
        this.displayProgressBar = displayProgressBar;
        view();
    }

    @Override
    public int getCurrent() {
        return pageIndex;
    }

    @Override
    public int getTotal() {
        return totalPage() - 1;
    }

    @Override
    public boolean enable() {
        return displayProgressBar;
    }

    public interface DataSource<T> {
        List<T> retrieve();
    }

    private List<T> getDisplayItems() {
        int initialIndex = pageIndex * displayedItemPerPage;
        ArrayList<T> ts = new ArrayList<>();
        currentCount = 0;
        for (int i = initialIndex; i<initialIndex+displayedItemPerPage; i++) {
            if (i < itemList.size()) {
                currentCount++;
                ts.add(itemList.get(i));
            }
        }
        return ts;
    }

    private int totalPage() {
        return itemList.size() / displayedItemPerPage;
    }

    public boolean isContains(int index) {
        return index <= currentCount;
    }

    public T getItem(int index) {
        if (!isContains(index)) {
            return null;
        }
        int initialIndex = pageIndex * displayedItemPerPage;
        return itemList.get(initialIndex + index - 1);
    }

    public String prompt() {
        StringBuilder sb = new StringBuilder();
        if (itemList.size() == 0) {
            sb.append("No results.");
            return sb.toString();
        }
        int count = 1;
        for (T t: getDisplayItems()) {
            sb.append(count++).append(": ").append(t.toString()).append("\n");
        }
        int totalPages = totalPage();
        if (itemList.size() % displayedItemPerPage > 0) {
            totalPages++;
        }
        if (totalPages <= 1) {
            sb.append("Displaying ").append(pageIndex + 1).append(" out of ").append(totalPages).append(" page.");
        } else {
            sb.append("Displaying ").append(pageIndex + 1).append(" out of ").append(totalPages).append(" pages.");
        }
        return sb.toString();
    }

    public boolean isExistPreviousPage() {
        return pageIndex > 0 && totalPage() >= 1;
    }

    public void previousPage() {
        if (isExistPreviousPage()) {
            pageIndex--;
        }
    }

    public boolean isExistNextPage() {
        int totalPages = (itemList.size() - 1) / displayedItemPerPage;
        return pageIndex < totalPages;
    }

    public void nextPage() {
        if (isExistNextPage()) {
            pageIndex++;
        }
    }

    @Override
    public void view() {
        component(
                ProgressBar.getBuilder()
                .setBarLength(50)
                .setDataSource(this)
                .build()
        );
        component(
                Label.getBuilder()
                        .setText(this::prompt)
                        .build()
        );
        component(
                MultipleChoiceView.getBuilder()
                        .addQuestion("<", () -> {
                            if (this.isExistPreviousPage()) {
                                return "Previous page.";
                            }
                            return null;
                        }, this::previousPage)
                        .addQuestion(">", () -> {
                            if (this.isExistNextPage()) {
                                return "Next page.";
                            }
                            return null;
                        }, this::nextPage)
                        .build()
        );
    }
}
