package aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import test.DarkPlayerActions;
import test.IranPlayer;

import java.io.Serializable;

@Aspect
public class FunctionalityAdderAspect {

    @DeclareParents(value = "test.Some*")
    private Serializable serilInterface;

    @DeclareParents(value = "test.Some*", defaultImpl = IranPlayer.class)
    private DarkPlayerActions darkPlayerActions;
}
