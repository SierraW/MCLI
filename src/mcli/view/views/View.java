package mcli.view.views;

import mcli.view.model.Function;
import mcli.view.model.StateFunction;

import java.util.ArrayList;

public abstract class View implements StateFunction {

    private final ArrayList<View> views = new ArrayList<>();
    private final ArrayList<View> inputViews = new ArrayList<>();
    public final ArrayList<String> input = new ArrayList<>();

    boolean isDisplayOnlyView() {
        return true;
    }

    public View() {
        view();
    }

    public abstract void view();

    @Override
    public void run(String comm) {
        if (comm == null) {
            for (View view : Environment.viewStack.top().views) {
                view.show(null);
            }
        } else {
            for (View view : Environment.viewStack.top().inputViews) {
                if (view.show(comm)) {
                    break;
                }
            }
        }
    }

    boolean show(String comm) {
        return false;
    }

    private View addView(View view) {
        if (view.isDisplayOnlyView()) {
            views.add(view);
        } else {
            views.add(view);
            inputViews.add(view);
        }
        return view;
    }

    public void Label(String text) {
        addView(new Label(text));
    }

    public MultipleChoiceView MultipleChoiceView(String introduction) {
        return (MultipleChoiceView) addView(new MultipleChoiceView(introduction));
    }

    public ShortAnswerView ShortAnswerView(ArrayList<String> input, Function onSuccess) {
        return (ShortAnswerView) addView(new ShortAnswerView(input, onSuccess));
    }

    public View View(View view) {
        return addView(view);
    }

    public void redirect(View newView) {
        Environment.viewStack.push(newView);
    }

    public void back() {
        Environment.viewStack.pop();
    }
}
