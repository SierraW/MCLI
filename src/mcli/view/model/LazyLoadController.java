package mcli.view.model;

import mcli.view.controller.TraderViewController;

public interface LazyLoadController {
    TraderViewController getController();   // initialize view controller.
}
