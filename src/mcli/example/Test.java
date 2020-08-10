package mcli.example;

import mcli.view.controller.NavigationViewController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        NavigationViewController navigationViewController = new NavigationViewController();
        navigationViewController.setRootView(new HelloWorldView());
        navigationViewController.run(bufferedReader);
    }
}
