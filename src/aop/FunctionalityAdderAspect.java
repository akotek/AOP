package aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import test.DarkAction;
import test.IranPlayer;

import java.io.Serializable;

@Aspect
public class FunctionalityAdderAspect {

    @DeclareParents(value = "test.Some*")
    private Serializable serilInterface;

    @DeclareParents(value = "test.Some*", defaultImpl = IranPlayer.class)
    private DarkAction darkAction;
}
