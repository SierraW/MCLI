package mcli.view.controller;

import mcli.view.component.View;
import mcli.view.component.ViewStack;

import java.io.BufferedReader;
import java.io.IOException;

public class NavigationViewController {
    private final ViewStack viewStack;

    public NavigationViewController() {
        viewStack = new ViewStack();
    }

    public NavigationViewController setRootView(View rootView) {
        this.viewStack.clear();
        rootView.setNavigationViewController(this);
        this.viewStack.push(rootView);
        return this;
    }

    public View top() {
        return viewStack.top();
    }

    public void push(View view) {
        viewStack.push(view);
    }

    public View pop() {
        return viewStack.pop();
    }

    public void run(BufferedReader reader) {
        String comm = null;
        while (true) {
            view(viewStack.top());
            try {
                comm = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            read(viewStack.top(), comm);
        }
    }

    private void view(View view) {
        for (View view1 : view.getViews()) {
            view1.print();
        }
    }

    private void read(View view, String comm) {
        for (View view1 : view.getViews()) {
            view1.read(comm);
        }
    }

    public void redirect(View newView) {
        newView.setNavigationViewController(this);
        viewStack.push(newView);
    }

    public void back() {
        viewStack.pop();
    }
}
