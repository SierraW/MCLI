package mcli.view.model;

import java.util.ArrayList;

public class ItemList<T> {
    private final ArrayList<T> itemList;
    private final int displayedItemPerPage = 5;
    private int pageIndex = 0;
    private int currentCount = 0;

    public ItemList(ArrayList<T> itemList) {
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

    public String prompt() {
        StringBuilder sb = new StringBuilder();
        if (itemList.size() == 0) {
            sb.append("No results.\n");
            return sb.toString();
        }
        getDisplayItems();
        int totalPages = itemList.size() / displayedItemPerPage;
        if (itemList.size() % displayedItemPerPage > 0) {
            totalPages++;
        }
        if (totalPages <= 1) {
            sb.append("Displaying ").append(pageIndex + 1).append(" out of ").append(totalPages).append(" page.\n");
        } else {
            sb.append("Displaying ").append(pageIndex + 1).append(" out of ").append(totalPages).append(" pages.\n");
        }
        return sb.toString();
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
