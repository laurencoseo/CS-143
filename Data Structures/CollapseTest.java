package testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CollapseTest {

    public static void collapse(Stack<Integer> stack) {
        Stack<Integer> stack2 = new Stack<>();
        int i = 0;
        while (i < stack.size()) {
            if (stack.size() % 2 != 0) {
                stack2.add(stack.pop());
            }
            if (stack.size() >= 2) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int sum = num1 + num2;
                System.out.println("num 1: " + num1 +
                        " num 2: " + num2 +
                        " sum: " + sum);
                stack2.add(sum);
            }
        }

        System.out.println(stack2);
        i =0;
        while (i < stack2.size()) {
            Integer num = stack2.pop();
            stack.add(num);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> myList = new Stack<>();
        // int[] nums = {7, 2, 8, 9, 4, 13, 7, 1, 9, 10};

        int[] nums = {1, 2, 3, 4, 5};

        for (int i = 0; i < nums.length; i++) {
            myList.add(nums[i]);
        }

        System.out.println(myList);
        collapse(myList);
        System.out.println(myList);
    }
}
