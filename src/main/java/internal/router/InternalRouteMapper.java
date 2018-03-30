package internal.router;

import org.reflections.Reflections;

import internal.router.annotation.InternalController;

import java.util.HashMap;
import java.util.Map;

public class InternalRouteMapper {
  private Map<String, Class<?>> internalController = new HashMap<>();

  public Map<String, Class<?>> getInternalController() {
    return internalController;
  }

  public Class<?> getInternalControllerClass(String name) {
    if (internalController.containsKey(name)) {
      return internalController.get(name).getClass();
    }
    return null;
  }

  public void addPackageController(String name) {
    Reflections reflections = new Reflections(name);
    for (Class<?> currentClass : reflections.getTypesAnnotatedWith(InternalController.class)) {
      internalController.put(currentClass.getName(), currentClass);
    }
  }

  public InternalRouteMapper withAddPackageController(String name) {
    addPackageController(name);
    return this;
  }
}
