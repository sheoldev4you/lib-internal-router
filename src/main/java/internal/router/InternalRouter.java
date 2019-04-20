package internal.router;

import internal.router.annotation.InternalRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class InternalRouter {
  private static final Logger LOGGER = LoggerFactory.getLogger(InternalRouter.class);
  private InternalRouteMapper internalRouteMapper = new InternalRouteMapper();

  private Stream<String> matchStream(InternalRouterObj internalRouterObj) {
    final Matcher matcher = Pattern.compile(internalRouterObj.getRegex())
            .matcher(internalRouterObj.getRoute());
    final Stream.Builder<String> stringBuilder = Stream.builder();
    if (matcher.find()) {
      for (int i = 0; i < matcher.groupCount(); i++) {
        stringBuilder.add(matcher.group(i + 1));
      }
    }
    return stringBuilder.build();
  }

  private Optional<InternalRouterObj> findRoute(Class<?> currentClass,
                                                String route,
                                                Class<? extends InternalRoute> annotation) {
    return Arrays.stream(currentClass.getDeclaredMethods())
            .filter(method -> method.isAnnotationPresent(annotation)
                    && route.matches(method.getAnnotation(annotation).value()))
            .map(method -> new InternalRouterObj()
                    .widthRoute(route)
                    .withCurrentClass(currentClass)
                    .withMethod(method)
                    .withRegex(method.getAnnotation(annotation).value())).findFirst();
  }

  public void setInternalRouteMapper(InternalRouteMapper internalRouteMapper) {
    this.internalRouteMapper = internalRouteMapper;
  }

  public InternalRouter withInternalRouteMapper(InternalRouteMapper internalRouteMapper) {
    this.internalRouteMapper = internalRouteMapper;
    return this;
  }

  public <T> T resolve(String route, Object... args) {
    AtomicReference<T> object = new AtomicReference<>();
    internalRouteMapper.getInternalController()
            .forEach((key, value) -> object.set(action(value, route, args)));
    return object.get();
  }

  public Object action(Object object, String route, Object... args) {
    return action(object.getClass(), route, args);
  }

  public <T> T action(Class<?> currentClass, String route, Object... args) {
    return action(currentClass, InternalRoute.class, route, args);
  }

  public <T> T action(Class<?> currentClass, Class<? extends InternalRoute> annotation, String route, Object... args) {
    Optional<InternalRouterObj> internalRouterObjOptional = findRoute(currentClass, route, annotation);
    if (internalRouterObjOptional.isPresent()) {
      InternalRouterObj internalRouterObj = internalRouterObjOptional.get();
      try {
        return InternalRouterUtil.cast(internalRouterObj.getMethod().invoke(
                currentClass.newInstance(),
                args.length == 0 ? matchStream(internalRouterObj)
                        .map(InternalRouterUtil::getNumeric).toArray() : args
        ));
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
        LOGGER.error("{}", e);
      }
    }
    return null;
  }
}