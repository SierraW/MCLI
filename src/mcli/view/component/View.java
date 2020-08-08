package mcli.view.component;

import mcli.view.model.Describable;
import mcli.view.model.Function;
import mcli.view.model.StateFunction;
import mcli.view.views.MultipleChoiceView;
import mcli.view.views.ShortAnswerView;

import java.util.ArrayList;

public abstract class View implements StateFunction {

    private ArrayList<View> views = new ArrayList<>();

    public View() {
        view();
    }

    public ArrayList<View> getViews() {
        return views;
    }

    public void setViews(ArrayList<View> views) {
        this.views = views;
    }

    public abstract void view();

    @Override
    public void run(String comm) {
        if (comm == null) {
            Environment.viewStack.top().show();
        } else {
            Environment.viewStack.top().read(comm);
        }
    }

    boolean read(String comm){
        for(View view : views) {
            if (view.read(comm)) {
                return true;
            }
        }
        return false;
    }

    void show() {
        for(View view : views) {
            view.show();
        }
    }

    private View addView(View view) {
        views.add(view);
        return view;
    }

    public Label Label(String text) {
        return (Label) addView(new Label(text));
    }

    public Label Label(Describable stringBinding) {
        return (Label) addView(new Label(stringBinding));
    }

    public MultipleChoiceView MultipleChoiceView() {
        return (MultipleChoiceView) addView(new MultipleChoiceView());
    }

    public ShortAnswerView ShortAnswerView() {
        return (ShortAnswerView) addView(new ShortAnswerView());
    }

    public TextField TextField() {
        return (TextField) addView(new TextField());
    }

    public Interceptor Interceptor(Function function) {
        return (Interceptor) addView(new Interceptor(function));
    }

    public SelectionField SelectionField() {
        return (SelectionField) addView(new SelectionField());
    }

    public ProgressBar ProgressBar() {
        return (ProgressBar) addView(new ProgressBar());
    }

    public View View(View view) {
        addView(view);
        return this;
    }

    public void redirect(View newView) {
        Environment.viewStack.push(newView);
    }

    public void back() {
        Environment.viewStack.pop();
    }
}
