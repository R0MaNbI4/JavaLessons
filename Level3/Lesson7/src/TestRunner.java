import Annotations.AfterSuite;
import Annotations.BeforeSuite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class TestRunner {
    private static Method beforeSuite;
    private static Method afterSuite;
    private static ArrayList<Method> tests;

    public static void start(Class testClass) {
        reset();
        getMethods(testClass);
        sortTests();
        invokeMethods(createNewInstance(testClass));
    }

    private static void reset() {
        beforeSuite = null;
        afterSuite = null;
        tests = new ArrayList<>();
    }

    private static void invokeMethods(Test test) {
        try {
            beforeSuite.invoke(test);

            for (Method method : tests) {
                method.invoke(test);
            }
            afterSuite.invoke(test);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException();
        }
    }

    private static void getMethods(Class<Test> testClass) {
        for (Method method : testClass.getMethods()) {
            afterSuite = getSingletonMethod(method, AfterSuite.class, afterSuite);
            beforeSuite = getSingletonMethod(method, BeforeSuite.class, beforeSuite);
            if (isTest(method)) {
                tests.add(method);
            }
        }
    }

    private static Method getSingletonMethod(Method verifiableMethod, Class annotation, Method methodStorage) {
        if (verifiableMethod.getAnnotation(annotation) != null) {
            if (methodStorage != null) {
                throw new RuntimeException(String.format("There can be no more than one method with annotation \"%s\"", annotation.getName()));
            }
        }
        return verifiableMethod;
    }

    private static boolean isTest(Method method) {
        if (method.getAnnotation(Annotations.Test.class) != null) {
            return true;
        }
        return false;
    }

    private static Test createNewInstance(Class testClass) {
        try {
            return (Test) testClass.getConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException();
        }
    }

    private static void sortTests() {
        tests.sort(Comparator.comparingInt(o -> o.getAnnotation(Annotations.Test.class).priority()));
    }
}