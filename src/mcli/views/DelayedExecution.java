package mcli.views;

import mcli.component.Interceptor;
import mcli.model.Function;

public class DelayedExecution extends View{
    private int delayTimesLeft;
    private final Function delayedFunc;

    public DelayedExecution(Function delayedFunc) {
        this(delayedFunc, 0);
    }

    public DelayedExecution(Function delayedFunc, int numberOfDelayTimes) {
        this.delayedFunc = delayedFunc;
        this.delayTimesLeft = numberOfDelayTimes;
        view();
    }

    @Override
    public void view() {
        component(
                new Interceptor(() -> {
                    if (delayTimesLeft-- == 0)
                        delayedFunc.apply();
                })
        );
    }

    @Override
    public void redirect(View newView) {
        super.redirect(newView);
    }
}
