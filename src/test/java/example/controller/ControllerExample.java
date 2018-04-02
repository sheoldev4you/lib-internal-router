package example.controller;

import example.object.ObjectExample;
import internal.router.annotation.InternalController;
import internal.router.annotation.InternalRoute;

/**
 * https://regex101.com/
 */
@InternalController
public class ControllerExample {
  @InternalRoute("test")
  public String testRoute() {
    return "ok";
  }

  @InternalRoute("simple regex test : (.*)$")
  public String testSimpleRegex(String value) {
    return value;
  }

  @InternalRoute("simple regex number test : ([0-9]*)$")
  public int testSimpleNumberRegex(int value) {
    return value;
  }

  @InternalRoute("power ([a-z]*) level ([0-9]*) with ([A-Z]*$)")
  public ObjectExample testWithObject(String power, int level, String addon) {
    return new ObjectExample()
            .withAddon(addon)
            .withPower(power)
            .withLevel(level);
  }
}
