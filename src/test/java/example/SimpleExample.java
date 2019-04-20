package example;

import example.object.ObjectExample;
import internal.router.InternalRouteMapper;
import internal.router.InternalRouter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExample {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExample.class);

  @Test
  public void simpleExample() {
    InternalRouter internalRouter = new InternalRouter()
            .withInternalRouteMapper(
                    new InternalRouteMapper()
                            .withAddPackageController("example/controller")
            );
    String result = internalRouter.resolve("test");
    if (null != result) {
      LOGGER.info("\"test\" => \"{}\"", result);
    }

    result = internalRouter.resolve("simple regex test : bla");
    if (null != result) {
      LOGGER.info("\"simple regex test : bla\" => \"{}\"", result);
    }

    Integer numberResult = internalRouter.resolve("simple regex number test : 42");
    if (null != numberResult) {
      LOGGER.info("\"simple regex number test : 42\" => \"{}\"", numberResult);
    }

    ObjectExample objectExample = internalRouter.resolve("power fire level 10 with WATER");
    if (null != objectExample) {
      LOGGER.info("\"power fire level 10 with WATER\" => \"{} / {} / {}\"",
              objectExample.getPower(), objectExample.getLevel(), objectExample.getAddon());
    }
  }
}
