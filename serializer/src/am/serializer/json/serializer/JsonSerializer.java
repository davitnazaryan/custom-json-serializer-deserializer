package am.serializer.json.serializer;

import am.serializer.json.annotation.IgnoreField;
import am.serializer.json.annotation.PropertyName;
import am.serializer.json.model.User;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class JsonSerializer {

  private JsonSerializer() throws IllegalAccessException {
    throw new IllegalAccessException("Can't instantiate class.");
  }

  private static void serialize0(StringBuilder result, Object el) throws InvocationTargetException, IllegalAccessException {
    if (el == null) {
      result.append("null,");
      return;
    }
    Class valueClass = el.getClass();
    if (valueClass.isEnum() || el instanceof CharSequence) {
      result.append("\"").append(el.toString()).append("\"");
    } else if (el instanceof Number || el instanceof Boolean
        || el instanceof Character) {
      result.append(el.toString());
    } else {
      result.append(serialize(el));
    }
    result.append(",");
  }


  public static String serialize(Object object) throws InvocationTargetException, IllegalAccessException {

    StringBuilder result = new StringBuilder();
    Class clazz = object.getClass();
    if (clazz.isArray()) {
      result.append("[");
      for (int i = 0; i < Array.getLength(object); i++) {
        serialize0(result, Array.get(object, i));
      }
    } else if (object instanceof Iterable) {
      result.append("[");
      Iterator iterator = ((Iterable) object).iterator();
      while (iterator.hasNext()) {
        serialize0(result, iterator.next());
      }
    } else if (object instanceof Map) {
      result.append("{");
      Map map = (Map) object;
      for (Object mapEl : map.entrySet()) {
        Map.Entry entry = (Map.Entry) mapEl;
        result.append("\"").append(entry.getKey()).append("\":");
        serialize0(result, entry.getValue());
      }
    } else {
      Map<String, Method> methodMap = new HashMap<>();
      for (Method method : clazz.getMethods()) {
        methodMap.put(method.getName(), method);
      }

      result.append("{");

      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields) {

        if (!field.isAnnotationPresent(IgnoreField.class)) {
          String fieldName = field.getName();
          String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
              + fieldName.substring(1);
          if (field.isAnnotationPresent(PropertyName.class)) {
            fieldName = field.getAnnotation(PropertyName.class).value();
          }
          result.append("\"").append(fieldName).append("\":");
          Method getterMethod = methodMap.get(getterMethodName);
          if (getterMethod != null) {
            Object value = getterMethod.invoke(object);
            serialize0(result, value);
          } else {
            serialize0(result, null);
          }
        }
      }
    }
    if (result.length() > 1) {
      result.deleteCharAt(result.length() - 1);
    }
    if (clazz.isArray() || object instanceof Iterable) {
      result.append("]");
    } else {
      result.append("}");
    }
    return result.toString();
  }

  public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
    System.out.println(serialize(new LinkedHashMap() {{
      put("name", "askgf");
      put("asd", 1);
      put("dsfsdf", true);
      put("fdg", new int[]{1, 2, 3});
      put("asf", new User());
    }}));
  }

}
