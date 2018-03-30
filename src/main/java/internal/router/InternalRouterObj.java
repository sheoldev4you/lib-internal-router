package internal.router;

import java.lang.reflect.Method;

public class InternalRouterObj {
  private Method method;
  private Class<?> currentClass;
  private String route;
  private String regex;

  public Class<?> getCurrentClass() {
    return currentClass;
  }

  public Method getMethod() {
    return method;
  }

  public String getRegex() {
    return regex;
  }

  public String getRoute() {
    return route;
  }

  public void setCurrentClass(Class<?> currentClass) {
    this.currentClass = currentClass;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  public InternalRouterObj withCurrentClass(Class<?> currentClass) {
    setCurrentClass(currentClass);
    return this;
  }

  public InternalRouterObj withMethod(Method method) {
    setMethod(method);
    return this;
  }

  public InternalRouterObj withRegex(String regex) {
    setRegex(regex);
    return this;
  }

  public InternalRouterObj widthRoute(String route) {
    setRoute(route);
    return this;
  }
}
