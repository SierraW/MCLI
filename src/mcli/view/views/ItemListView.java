package mcli.view.views;

import mcli.view.component.Label;
import mcli.view.model.ItemList;

public class ItemListView<T> extends View {
    private ItemList<T> itemList;
    private Integer selectedIndex;

    public ItemListView(ItemList<T> itemList) {
        this.itemList = itemList;
    }

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
        component(Label.getBuilder().setText(itemList::prompt).build());
        component(
                MultipleChoiceView.getBuilder()
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
                .addQuestion("b", "go back", this::back)
                .build()
        );
    }
}
