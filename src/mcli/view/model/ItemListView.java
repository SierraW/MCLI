package mcli.view.model;

import java.util.ArrayList;

/**
 * this view give nice user interface when displaying long list of items. if number of T is bigger than 'displayedItemPerPage' value,
 * items will folded to multiple pages. follow by next page and previous page command.
 */
public class ItemListView<T> {
    private final ArrayList<T> itemList;
    private final int displayedItemPerPage = 5;
    private int pageIndex = 0;
    private int currentCount = 0;

    public ItemListView(ArrayList<T> itemList) {
        this.itemList = itemList;
    }

    private ArrayList<T> getDisplayItems() {
        int initialIndex = pageIndex * displayedItemPerPage;
        ArrayList<T> ts = new ArrayList<>();
        currentCount = 1;
        for (int i = initialIndex; i<initialIndex+displayedItemPerPage; i++) {
            if (i < itemList.size()) {
                ts.add(itemList.get(i));
            }
        }
        return ts;
    }

    public boolean isContains(int index) {
        return index < currentCount;
    }

    public T getItem(int index) {
        if (!isContains(index)) {
            return null;
        }
        int initialIndex = pageIndex * displayedItemPerPage;
        return itemList.get(initialIndex + index - 1);
    }

    public void prompt() {
        if (itemList.size() == 0) {
            System.out.println("No results.");
            return;
        }
        getDisplayItems();
        int totalPages = itemList.size() / displayedItemPerPage;
        if (itemList.size() % displayedItemPerPage > 0) {
            totalPages++;
        }
        if (totalPages <= 1) {
            System.out.printf("Displaying %d out of %d page.\n", pageIndex + 1, totalPages);
        } else {
            System.out.printf("Displaying %d out of %d pages.\n", pageIndex + 1, totalPages);
        }
    }

    public boolean isExistPreviousPage() {
        int totalPages = itemList.size() / displayedItemPerPage;
        return pageIndex > 0 && totalPages > 1;
    }

    public void previousPage() {
        if (isExistPreviousPage()) {
            pageIndex--;
        }
    }

    public boolean isExistNextPage() {
        int totalPages = itemList.size() / displayedItemPerPage;
        return pageIndex < totalPages;
    }

    public void nextPage() {
        if (isExistNextPage()) {
            pageIndex++;
        }
    }
}
