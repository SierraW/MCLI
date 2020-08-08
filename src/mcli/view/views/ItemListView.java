package mcli.view.views;

import mcli.view.component.View;
import mcli.view.component.ItemList;

public class ItemListView<T> extends View {
    private ItemList<T> itemList;
    private Integer selectedIndex;

    public ItemList<T> getItemList() {
        return itemList;
    }

    public void setItemList(ItemList<T> itemList) {
        this.itemList = itemList;
    }

    public T getSelectedItem() {
        if(itemList == null)
            return null;
        return itemList.getItem(selectedIndex);
    }

    @Override
    public void view() {
        Label(itemList.prompt());
        MultipleChoiceView()
                .addQuestion("<", () -> {
                    if (itemList.isExistPreviousPage()) {
                        return "Previous page.";
                    }
                    return null;
                }, itemList::previousPage)
                .addQuestion(">", () -> {
                    if (itemList.isExistNextPage()) {
                        return "Next page.";
                    }
                    return null;
                }, itemList::nextPage)
                .addQuestion("b", "go back", this::back);
    }
}
