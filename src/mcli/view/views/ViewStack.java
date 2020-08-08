package mcli.view.views;

import java.util.ArrayList;

public class ViewStack extends ArrayList<View> {
    public void push(View view) {
        add(0, view);
    }

    public View pop() {
        if (isEmpty())
            return null;
        return remove(0);
    }

    public View top() {
        if (isEmpty())
            return null;
        return get(0);
    }
}
