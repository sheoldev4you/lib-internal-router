package internal.router;

public final class InternalRouterUtil {
  private InternalRouterUtil() {
  }

  public static Object getNumeric(String value) {
    try {
      return Integer.valueOf(value);
    } catch (Exception e) {
      return value;
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T cast(Object obj) {
    return (T) obj;
  }
}
