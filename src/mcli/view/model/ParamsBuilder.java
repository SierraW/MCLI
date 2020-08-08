package mcli.view.model;

import mcli.view.controller.ViewController;

import java.util.ArrayList;

public class ParamsBuilder implements ViewController.Builder {
    ArrayList<String[]> params = new ArrayList<>();

    @Override
    public ViewController.Builder addQuestion(String question, String regex) {
        params.add(new String[]{question, regex});
        return this;
    }

    @Override
    public String[][] build() {
        assert params.size() != 0;
        String[][] output = new String[params.size()][2];
        for (int i = 0; i < params.size(); i++) {
            output[i][0] = params.get(i)[0];
            output[i][1] = params.get(i)[1];
        }
        return output;
    }
}
