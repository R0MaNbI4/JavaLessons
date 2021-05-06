import Annotations.AfterSuite;
import Annotations.BeforeSuite;

public class Test {
    @BeforeSuite
    public void beforeSuite() {
    }

    @Annotations.Test
    public void test() {
        System.out.println("priority = 1");
    }

    @Annotations.Test(priority = 2)
    public void test2() {
        System.out.println("priority = 2");
    }

    @AfterSuite
    public void afterSuite() {
    }
}