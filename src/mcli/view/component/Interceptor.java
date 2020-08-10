package mcli.view.component;

import mcli.view.model.Function;

public class Interceptor extends View {

    private Function function;

    public Interceptor(Function function) {
        this.function = function;
    }

    @Override
    public void view() {

    }

    @Override
    public void print() {
        function.apply();
    }
}
