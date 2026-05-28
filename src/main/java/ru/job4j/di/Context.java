package ru.job4j.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    private final Map<String, Object> beans = new HashMap<>();

    public void register(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors : " + clazz.getCanonicalName());
        }

        Constructor<?> constructor = constructors[0];
        List<Object> args = new ArrayList<>();
        for (Class<?> arg : constructor.getParameterTypes()) {
            if (!beans.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object wasn't found in context: " + arg.getCanonicalName());
            }
            args.add(beans.get(arg.getCanonicalName()));
        }

        try {
            beans.put(clazz.getCanonicalName(), constructor.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Couldn't create an instance of: " + clazz.getCanonicalName(), e);
        }
    }

    public <T> T get(Class<T> instance) {
        return instance.cast(beans.get(instance.getCanonicalName()));
    }
}
