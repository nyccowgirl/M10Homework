package Practice;

import java.util.Stack;

public class PracticeDriver {

    public static void main(String[] args) {
        String text1 = "racecar";
        String text2 = "hello";
        String text3 = "sees";
        String text4 = "a";
        String text5 = "";

        System.out.println(isPalindrome(text1));            // true
        System.out.println(isPalindrome(text2));            // false
        System.out.println(isPalindrome(text3));            // true
        System.out.println(isPalindrome(text4));            // true
        System.out.println(isPalindrome(text5));            // false
    }

    // Question 6:
    public static boolean isPalindrome(String text) {
        Stack<Character> stack = new Stack<>();
        int index;

        if (text.length() == 0) {       // if empty, return false (edge case)
            return false;
        }

        for (index = 0; index < text.length() / 2; index++) {
            stack.push(text.charAt(index));
        }

        // For odd number of characters
        if (text.length() % 2 != 0) {
            index++;
        }

        while (index < text.length()) {
            if (!(Character.valueOf(text.charAt(index)).equals(stack.pop()))) {
                return false;
            }
            index++;
        }

        return true;
    }

    // Question 9:
    public static <T> StackInterface<T> createCopyStack(StackInterface<T> originalStack) {
        StackInterface<T> copyStack = new LinkedStack<T>(); // or these could be Homework.ArrayStack
        StackInterface<T> reverseStack = new LinkedStack<T>();

        while (!originalStack.isEmpty()) {
            reverseStack.push(originalStack.pop());
        }

        while (!reverseStack.isEmpty()) {
            T element = reverseStack.pop();
            originalStack.push(element);
            copyStack.push(element);
        }

        return copyStack;
    }
}
