package mcli.view.component;

import mcli.view.model.Function;
import mcli.view.model.TextLayer;

public class Interceptor extends TextLayer {

    private Function function;

    public Interceptor(Function function) {
        this.function = function;
    }

    @Override
    public void print() {
        function.apply();
    }
}
