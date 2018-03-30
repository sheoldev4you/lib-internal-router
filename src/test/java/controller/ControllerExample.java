package controller;

import internal.router.annotation.InternalController;
import internal.router.annotation.InternalRoute;

@InternalController
public class ControllerExample {
  @InternalRoute("test")
  public String testRoute() {
    return "test ok";
  }


}
