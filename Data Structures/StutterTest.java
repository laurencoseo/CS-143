package testing;

import java.util.ArrayList;

public class StutterTest {

    public static void stutter(ArrayList<String> list, int k) {
        if (k == 0) {
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
                i--;
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < k - 1; j++) {
                    list.add(i, list.get(i));
                }
                i += k - 1;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>();
        String[] words = {"how", "are", "you?"};
        for (int i = 0; i < words.length; i++) {
            myList.add(words[i]);
        }

        stutter(myList, 4);
        System.out.println(myList);
    }
}
