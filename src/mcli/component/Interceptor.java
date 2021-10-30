package mcli.component;

import mcli.model.Function;
import mcli.model.TextLayer;

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
