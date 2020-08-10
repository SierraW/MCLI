package mcli.view.views;

import mcli.view.component.Label;
import mcli.view.component.SelectionField;
import mcli.view.component.TextField;
import mcli.view.controller.NavigationViewController;
import mcli.view.model.Binding;
import mcli.view.model.InputLayer;
import mcli.view.model.TextLayer;

import java.util.ArrayList;

public abstract class View {

    private NavigationViewController navigationViewController;
    private final ArrayList<TextLayer> textLayers = new ArrayList<>();
    private final ArrayList<InputLayer> inputLayers = new ArrayList<>();

    public View() {
        view();
    }

    public void setNavigationViewController(NavigationViewController controller) {
        this.navigationViewController = controller;
    }

    public ArrayList<TextLayer> getTextLayers() {
        return textLayers;
    }

    public ArrayList<InputLayer> getInputLayers() {
        return inputLayers;
    }

    public abstract void view();

    private InputLayer addInputLayer(InputLayer inputLayer) {
        inputLayers.add(inputLayer);
        return inputLayer;
    }

    private TextLayer addTextLayer(TextLayer textLayer) {
        textLayers.add(textLayer);
        return textLayer;
    }

    private boolean hasInputLayer() {
        return inputLayers.size() > 0;
    }

    private boolean hasTextLayer() {
        return textLayers.size() > 0;
    }

    private void addView(View view) {
        if (view.hasTextLayer()) {
            textLayers.addAll(view.getTextLayers());
        }
        if (view.hasInputLayer()) {
            inputLayers.addAll(view.getInputLayers());
        }
    }

    public void component(View view) {
        addView(view);
    }

    public void component(TextLayer textLayer) {
        addTextLayer(textLayer);
    }

    public void component(InputLayer inputLayer) {
        addInputLayer(inputLayer);
    }

    public void redirect(View newView) {
        navigationViewController.redirect(newView);
    }

    public void back() {
        navigationViewController.back();
    }
}
