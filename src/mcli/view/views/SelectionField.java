package mcli.view.views;

import mcli.view.model.StateFunction;

import java.util.Set;

public class SelectionField extends View{

    public interface Selections {
        Set<String> getSelections();
    }

    private final Selections selections;
    private final StateFunction onFill;
    private final String error;

    public SelectionField(Selections selections, StateFunction onFill) {
        this(selections, null, onFill);
    }

    public SelectionField(Selections selections, String error, StateFunction onFill) {
        this.selections = selections;
        this.onFill = onFill;
        this.error = error;
    }

    @Override
    boolean read(String comm) {
        if (selections.getSelections().contains(comm)) {
            onFill.run(comm);
            return true;
        } else {
            if (error != null) {
                System.out.println(error);
                return true;
            }
            return false;
        }
    }

    @Override
    public void view() {
    }
}
