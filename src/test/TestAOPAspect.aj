package test;

public aspect TestAOPAspect {

    pointcut greeting() : execution (* test.TestAOP.sayHello(..));

    after() returning() : greeting() {
        System.out.println("World");
    }
}
