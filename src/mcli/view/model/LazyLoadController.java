package mcli.view.model;

import mcli.view.controller.ViewController;

public interface LazyLoadController {
    ViewController getController();   // initialize view controller.
}
