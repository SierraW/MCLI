package mcli.view.views;

import java.util.ArrayList;

public class Environment {
    public static ViewStack viewStack;
    public final static String STR_REGEX = "^[a-zA-Z0-9]{1,32}$";
    public final static String STR_SPACE_REGEX = "^[a-zA-Z0-9 ]{1,32}$";

    public static void setRoot(View view) {
        viewStack = new ViewStack();
        viewStack.push(view);
    }
}