package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

public class SerializableTest {

    @Test
    public void testSerializationOccured(){
        SomeClass s1 = new SomeClass();
        SomeSingletonClass s2 = new SomeSingletonClass();

        Assertions.assertTrue(s1 instanceof Serializable && s1 instanceof DarkAction);
        Assertions.assertTrue(s2 instanceof Serializable && s2 instanceof DarkAction);
    }
}
