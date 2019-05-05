package test;// This should be used to test that AOP compiles and works behind the hood

// Run the sayHello and if result equals "Hello World" we are good,
// else: AJC is not compiling correctly

public class TestAOP {

    public static void main(String[] args) {
        sayHello();
    }

    public static void sayHello() {
        System.out.print("Hello ");
    }
}
