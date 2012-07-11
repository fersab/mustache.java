package com.github.mustachejava.reflect;

import com.github.mustachejava.ObjectHandler;
import com.github.mustachejava.util.Wrapper;
import com.google.common.base.Predicate;

import java.util.Map;

import static com.github.mustachejava.reflect.ReflectionObjectHandler.unwrap;

/**
 * Guards whether or not a name was present in the map.
 */
public class MapGuard implements Predicate<Object[]> {
  private final ObjectHandler oh;
  private final int scopeIndex;
  private final String name;
  private final boolean contains;
  private final Wrapper[] wrappers;

  public MapGuard(ObjectHandler oh, int scopeIndex, String name, boolean contains, Wrapper[] wrappers) {
    this.oh = oh;
    this.scopeIndex = scopeIndex;
    this.name = name;
    this.contains = contains;
    this.wrappers = wrappers;
  }

  @Override
  public boolean apply(Object[] objects) {
    Object scope = unwrap(oh, scopeIndex, wrappers, objects);
    if (scope instanceof Map) {
      Map map = (Map) scope;
      if (contains) {
        return map.containsKey(name);
      } else {
        return !map.containsKey(name);
      }
    }
    return true;
  }
}
