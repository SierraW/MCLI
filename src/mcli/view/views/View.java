package mcli.view.views;

import mcli.view.controller.NavigationViewController;
import mcli.view.model.InputLayer;
import mcli.view.model.TextLayer;

import java.util.ArrayList;

public abstract class View {

    private NavigationViewController navigationViewController;
    private final ArrayList<TextLayer> textLayers = new ArrayList<>();
    private final ArrayList<InputLayer> inputLayers = new ArrayList<>();

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

    private void addInputLayer(InputLayer inputLayer) {
        inputLayers.add(inputLayer);
    }

    private void addTextLayer(TextLayer textLayer) {
        textLayers.add(textLayer);
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
        if (navigationViewController != null)
            navigationViewController.redirect(newView);
        else
            throw new NullPointerException("Attempting to redirect without having a navigation view controller.");
    }

    public void back() {
        if (navigationViewController != null)
            navigationViewController.back();
        else
            throw new NullPointerException("Attempting to go back without having a navigation view controller.");
    }
}
