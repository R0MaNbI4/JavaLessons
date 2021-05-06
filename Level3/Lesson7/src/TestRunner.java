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
        beforeSuite = null; // Необходимо, если TestRunner будет использоваться для вызова нескольких разных тестов
        afterSuite = null;
        tests = new ArrayList<>();

        getMethods(testClass);
        invokeMethods(createNewInstance(testClass));
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
            if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterSuite != null) {
                    throw new RuntimeException("There can be no more than one method with annotation \"AfterSuite\"");
                }
                afterSuite = method;
            }
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuite != null) {
                    throw new RuntimeException("There can be no more than one method with annotation \"BeforeSuite\"");
                }
                beforeSuite = method;
            }
            if (method.getAnnotation(Annotations.Test.class) != null) {
                tests.add(method);
            }
        }

        sortTests();
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