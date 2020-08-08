package mcli.view.model;

import mcli.view.controller.TraderViewController;

import java.util.HashMap;

public class ControllerStateMap extends HashMap<String, StateFunction> {
    private final HashMap<String, LazyLoadController> lazyControllerHashMap = new HashMap<>(); // store "how to" init the controller
    private TraderViewController currentController; // store current controller (LazyController Only)

    private String currentState;

    public void switchState(String state) {
        if (!containsKey(state) && !lazyControllerHashMap.containsKey(state)) {
            System.out.printf("WARNING: switch to unset state '%s'.\n", state);
            return;
        }
        currentController = null;

        if (lazyControllerHashMap.containsKey(state)) {
            currentController = lazyControllerHashMap.get(state).getController();
        }

        currentState = state;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void addState(String key, StateFunction stateFunction) {
        if (containsKey(key)) {
            System.out.printf("Warning: duplicated state key '%s.'\n", key);
            return;
        }
        put(key, stateFunction);
        if (size() == 1) {
            switchState(key);
        }
    }

    public void addLazyController(String key, LazyLoadController controller) {
        if (lazyControllerHashMap.containsKey(key)) {
            System.out.printf("Warning: duplicated state key '%s.'\n", key);
            return;
        }
        lazyControllerHashMap.put(key, controller);
    }

    public StateFunction getStateFunction() {
        if (currentController != null) {
            return currentController;
        }
        return get(currentState);
    }
}
