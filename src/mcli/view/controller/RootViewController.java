package mcli.view.controller;

import mcli.view.views.Environment;
import mcli.view.views.View;

import java.io.BufferedReader;
import java.io.IOException;

public class RootViewController {
    private final View rootView;
    private String comm;

    public RootViewController(View rootView) {
        this.rootView = rootView;
        comm = null;
        Environment.setRoot(rootView);
    }

    public boolean run(BufferedReader reader) {
        while (true) {
            rootView.run(null);
            try {
                comm = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rootView.run(comm);
        }
    }

}
