package mcli.view.component;

import mcli.view.controller.NavigationViewController;
import mcli.view.model.Binding;
import mcli.view.model.Function;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.ShortAnswerView;

import java.util.ArrayList;

public abstract class View {

    private NavigationViewController navigationViewController;
    private ArrayList<View> views = new ArrayList<>();
    private ArrayList<Read> reads = new ArrayList<>();

    public View() {
        view();
    }

    public View setNavigationViewController(NavigationViewController controller) {
        this.navigationViewController = controller;
        return this;
    }

    public ArrayList<View> getViews() {
        return views;
    }

    public void setViews(ArrayList<View> views) {
        this.views = views;
    }

    public abstract void view();

    public boolean read(String comm){ // todo separate
        for(Read read : reads) {
            if (read.read(comm)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for(View view : views) {
            view.print();
        }
    }

    private Read addRead(Read read) {
        reads.add(read);
        return read;
    }

    private View addView(View view) {
        views.add(view);
        return view;
    }

    public Label Label(String text) {
        return (Label) addView(new Label(text));
    }

    public Label Label(Binding<String> stringBinding) {
        return (Label) addView(new Label(stringBinding));
    }

    public MultipleChoiceView MultipleChoiceView() {
        return (MultipleChoiceView) addView(new MultipleChoiceView());
    }

    public ShortAnswerView ShortAnswerView() {
        return (ShortAnswerView) addView(new ShortAnswerView());
    }

    public TextField TextField() {
        return (TextField) addRead(new TextField());
    }

    public Interceptor Interceptor(Function function) {
        return (Interceptor) addView(new Interceptor(function));
    }

    public SelectionField SelectionField() {
        return (SelectionField) addRead(new SelectionField());
    }

    public ProgressBar ProgressBar() {
        return (ProgressBar) addView(new ProgressBar());
    }

    public View View(View view) {
        addView(view);
        return this;
    }

    public void redirect(View newView) {
        navigationViewController.redirect(newView);
    }

    public void back() {
        navigationViewController.back();
    }
}
