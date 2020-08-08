package mcli.view.model;

import mcli.view.controller.TraderViewController;

public class ItemListViewController<T> extends TraderViewController {

    private final String HOME = "h";
    private final String SELECT = "s";
    private CommandMap cmSelectItem;
    private ItemListView<T> itemListView;
    private Integer selectedIndex;

    public ItemListView<T> getItemListView() {
        return itemListView;
    }

    public void setItemListView(ItemListView<T> itemListView) {
        this.itemListView = itemListView;
    }

    public ItemListViewController(Function onExit) {
        super(onExit);
        addState(HOME, this::browse);
        addState(SELECT, this::select);
        //cmSelectItem
        cmSelectItem = new CommandMap();
        cmSelectItem.setPrompt(() -> itemListView.prompt());
        cmSelectItem.set("<", () -> {
            if (itemListView.isExistPreviousPage()) {
                return "Previous page.";
            }
            return null;
        }, () -> itemListView.previousPage());
        cmSelectItem.set(">", () -> {
            if (itemListView.isExistNextPage()) {
                return "Next page.";
            }
            return null;
        },() -> itemListView.nextPage());
        cmSelectItem.set("back", () -> "Go back.", this::exit);
    }

    public void extendCmSelectItem(String comm, Describable describable, Function function) {
        cmSelectItem.set(comm, describable, function);
    }

    public void browse(String comm) {
        if (itemListView == null) {
            System.out.println("ERROR: itemListView not set.");
            exit();
            return;
        }
        cmSelectItem.run(comm);
    }

    public void select(String comm) {
        if (itemListView == null) {
            System.out.println("ERROR: itemListView not set.");
            exit();
            return;
        }
        if (comm == null) {
            itemListView.prompt();
            cmSelectItem.prompt();
        }
        conParams(newParamsBuilder()
                        .addQuestion("s [index] to select an item.", "^[<sbn>]( [0-9])?$")
                        .build(),
                comm,
                () -> {
            String[] fixedComm = input.get(0).split(" ");
            if (fixedComm.length == 1) {
                cmSelectItem.run(fixedComm[0]);
            } else {
                int index = Integer.parseInt(input.get(0).split(" ")[1]);
                if (itemListView.isContains(index)) {
                    selectedIndex = index;
                    exit();
                } else {
                    System.out.println("Index out of range.");
                    input.clear();
                }
            }

            }
        );
    }

    public T getSelectedItem() {
        if(itemListView == null)
            return null;
        return itemListView.getItem(selectedIndex);
    }
}
