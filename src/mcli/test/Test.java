package mcli.test;

import mcli.view.views.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        RootViewController rootViewController = new RootViewController(new HelloWorldView());
        rootViewController.run(bufferedReader);
    }
}
