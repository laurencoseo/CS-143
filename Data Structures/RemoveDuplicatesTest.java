package testing;

import java.util.ArrayList;

public class RemoveDuplicatesTest {

    public static void removeDuplicates(ArrayList<String> list) {
        int i = 0;
        while (i < list.size() - 1) {
            System.out.println("i = " + i + ", list.size = " + list.size());
            if (list.get(i) == list.get(i + 1)) {
                list.remove(i);
            } else {
                i++;
            }
        }

    }

    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>();
        String[] words = {"be", "be", "is", "not", "or", "question", "that", "the", "to", "to"};
        for (int i = 0; i < words.length; i++) {
            myList.add(words[i]);
        }
        System.out.println(myList);
        System.out.println();
        System.out.println("----------DUPLICATES TEST-----------");
        removeDuplicates(myList);
        System.out.println(myList);
    }
}

