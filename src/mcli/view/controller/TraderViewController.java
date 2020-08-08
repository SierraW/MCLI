package mcli.view.controller;

import mcli.view.model.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Command line interface controller
 */
public abstract class TraderViewController implements StateFunction {
    public final ArrayList<String> input = new ArrayList<>();
    public final String STR_REGEX = "^[a-zA-Z0-9]{1,32}$";
    public final String STR_SPACE_REGEX = "^[a-zA-Z0-9 ]{1,32}$";
    private final Function onExit;
    private ControllerStateMap csm;

    /**
     * Constructor
     * @param onExit What to execute when this controller exit.
     */
    public TraderViewController(Function onExit) {
        this.onExit = onExit;
    }

    /**
     * Change the current state of the controller
     * @param state the state "key" given to a state (this state key must be added using addState or addControllerState method first).
     */
    public void switchState(String state) {
        csm.switchState(state);
    }

    /**
     * Get current state of this controller
     * @return the state "key" of current state.
     */
    public String getCurrentState() {
        return csm.getCurrentState();
    }

    /**
     * Add a state to this controller.
     * @param state unique key of a state.
     * @param stateFunc what to do when controller being switch to this state.
     */
    public void addState(String state, StateFunction stateFunc) {
        if (csm == null) {
            csm = new ControllerStateMap();
        }
        csm.addState(state, stateFunc);
    }

    /**
     * Add a state to this controller. (for connecting multiple controller use.)
     * @param state unique key of a state.
     * @param controller a controller designed to handle this state.
     */
    public void addControllerState(String state, LazyLoadController controller) {
        if (csm == null) {
            csm = new ControllerStateMap();
        }
        csm.addLazyController(state, controller);
    }

    /**
     * Execute function
     * @param comm input from user.
     */
    public void run(String comm) {
        if (csm == null || csm.getCurrentState() == null) {
            System.out.println("ERROR: not state added to the controller.");
            System.out.println("Press enter to continue.");
            exit();
            return;
        }
        csm.getStateFunction().run(comm);
    }

    /**
     * Exit function, should get called at controller finished its work or get terminated. Will execute onExit which set previously at
     * constructor.
     */
    public void exit() {
        onExit.apply();
    }

    /**
     * Builder for conParams() 1st argument.
     */
    public interface Builder {
        Builder addQuestion(String question, String regex);
        String[][] build();
    }

    /**
     * Get a builder for building conParams() 1st argument
     * @return new builder.
     */
    public Builder newParamsBuilder() {
        return new ParamsBuilder();
    }

    /**
     * Get complex input from user.
     * @param args array of question that is going to ask the user. Each element contains [question, regex that validate the input] both in string format
     *             number of element result in size of input array. Position of the input value will be the same as the position of the element.
     * @param comm input user command
     * @param onSuccess what function will do when constructing params is finished. retrieve user input using input.get(n), where n is position
     *                  of the question at params passed in.
     */
    public void conParams(String[][] args, String comm, Function onSuccess) {
        if (comm == null) {
            System.out.println(args[input.size()][0]);
            return;
        }
        if (!validate(comm, args[input.size()][1])) {
            System.out.println("Input invalid");
        } else {
            input.add(comm);
        }
        if (input.size() == args.length) {
            onSuccess.apply();
            input.clear();
        }
    }

    /**
     * validate the input is correct.
     * @param input input needs to be validate.
     * @param regEx validate RegEx
     * @return  if is validate or not.
     */
    private boolean validate(String input, String regEx) {
        if (input == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(input).matches();
    }
}
