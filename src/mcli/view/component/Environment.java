package mcli.view.component;

public class Environment {
    public static ViewStack viewStack;


    public static void setRoot(View view) {
        viewStack = new ViewStack();
        viewStack.push(view);
    }
}