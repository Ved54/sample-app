public class HelloWorldTest {
    public static void main(String[] args) {
        String output = "Hello, Jenkins CI/CD Pipeline!";
        if (output.contains("Jenkins")) {
            System.out.println("TEST PASSED ✅");
        } else {
            System.out.println("TEST FAILED ❌");
            System.exit(1);
        }
    }
}

