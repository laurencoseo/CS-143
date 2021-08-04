package testing;

import java.util.ArrayList;

public class RemoveInRangeTest {

    public static void removeInRange(ArrayList<Integer> list, int value, int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.println("i = " + i);

            if (list.get(i) == value) {
                list.remove(i);
                i--;
                end--;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        int[] numbers = {0, 0, 2, 0, 4, 0, 6, 0, 8, 0, 10, 0, 12, 0, 14, 0, 16};
        for (int i = 0; i < numbers.length; i++) {
            myList.add(numbers[i]);
        }

        removeInRange(myList, 0, 5, 13);
        System.out.println(myList);
    }
}
