package mcli.view.model;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandMap extends HashMap<String, DescribableFunction> implements StateFunction {
    private ArrayList<Function> promptList = new ArrayList<>();
    private Function error;

    /**
     * Set function at the start.
     * @param prompt what to do at the start of this controller.
     */
    public void setPrompt(Function prompt) {
        this.promptList = new ArrayList<>();
        this.promptList.add(prompt);
    }

    /**
     * add function to the prompt list
     * @param prompt what to do at the start of controller
     */
    public void addPrompt(Function prompt) {
        this.promptList.add(prompt);
    }

    /**
     * execute all set prompts from the list.
     */
    public void prompt() {
        for (Function f: promptList) {
            f.apply();
        }
        this.forEach((key, value) -> {
            if (value.describe() != null)
                System.out.println(key + ": " +value.describe());
        });
    }

    /**
     * what will do after user input not match any of available options.
     * @param error what to do after user input not match any of available options. normally should out print input invalid.
     */
    public void setError(Function error) {
        this.error = error;
    }

    /**
     * execute command map.
     * @param comm user input command
     */
    public void run(String comm) {
        if (comm == null) {
            prompt();
            return;
        }
        DescribableFunction func = this.get(comm);
        if (func == null || func.describe() == null) {
            if (error != null) {
                this.error.apply();
            } else {
                System.out.println("Input invalid. Warning: error message not set.");
            }
        } else {
            func.apply();
        }
    }

    /**
     * set the command
     * @param comm key of the a command, must be unique.
     * @param describable description of this command, will prompt to user about what this command will do.
     * @param onSubmit what to do after user input this command.
     */
    public void set(String comm, Describable describable, Function onSubmit) {
        DescribableFunction describableFunction = new DescribableFunction() {
            @Override
            public void apply() {
                onSubmit.apply();
            }

            @Override
            public String describe() {
                return describable.describe();
            }
        };
        super.put(comm, describableFunction);
    }
}
