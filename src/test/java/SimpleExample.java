import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import internal.router.InternalRouteMapper;
import internal.router.InternalRouter;
import internal.router.annotation.InternalController;

@InternalController
public class SimpleExample {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExample.class);

  @Test
  public void simpleExample() {
    InternalRouter internalRouter = new InternalRouter()
        .withInternalRouteMapper(
            new InternalRouteMapper()
                .withAddPackageController("controller")
        );
    String result = internalRouter.resolve("test");
    if (null != result) {
      LOGGER.info("Value of route : \"{}\"", result);
    }
  }
}
