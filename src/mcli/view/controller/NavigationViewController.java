package mcli.view.controller;

import mcli.view.model.InputLayer;
import mcli.view.model.TextLayer;
import mcli.view.views.View;
import mcli.view.model.ViewStack;

import java.io.BufferedReader;
import java.io.IOException;

public class NavigationViewController {
    private final ViewStack viewStack;

    public NavigationViewController() {
        viewStack = new ViewStack();
    }

    public void setRootView(View rootView) {
        this.viewStack.clear();
        redirect(rootView);
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
        for (TextLayer textLayer : view.getTextLayers()) {
            textLayer.print();
        }
    }

    private void read(View view, String comm) {
        for (InputLayer inputLayer : view.getInputLayers()) {
            if (inputLayer.read(comm)) {
                return;
            }
        }
    }

    public void redirect(View newView) {
        newView.view();
        newView.setNavigationViewController(this);
        viewStack.push(newView);
    }

    public void back() {
        viewStack.pop();
    }
}
