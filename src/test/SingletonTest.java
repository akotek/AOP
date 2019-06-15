package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    public void testSingletonAspect(){

        // Create same instances of same class
        // Test both have same reference (== Singleton pattern)

        SomeSingletonClass s1 = new SomeSingletonClass();
        SomeSingletonClass s2 = new SomeSingletonClass();

        Assertions.assertSame(s1, s2);
    }
}