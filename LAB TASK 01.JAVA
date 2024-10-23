import java.util.Scanner;

public class SumOfNaturalNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        // Using the formula to calculate the sum
        int sum = n * (n + 1) / 2;

        System.out.println("The sum of the first " + n + " natural numbers is: " + sum);
    }
}