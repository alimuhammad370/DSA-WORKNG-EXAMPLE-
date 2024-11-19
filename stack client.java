import java.util.Stack;

public class Stackclient {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(8);
        stack.push(11);
        stack.push(13);
        stack.push(20);
        stack.push(11);

        System.out.println("1.Size of stack after push operation: " + stack.size());
        System.out.println("2. Pop Element from stack : ");
        while (!stack.isEmpty()) {
            System.out.printf(" %d" , stack.pop());
        }
        System.out.println("\n3. Size of stack after pop operation: " + stack.size());
    }
}