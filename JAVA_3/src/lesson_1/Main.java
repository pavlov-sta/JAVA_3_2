package lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] myArray = {1,2};

        System.out.println(Arrays.toString(myArray));
        swap(myArray, 0, 1);
        System.out.println(Arrays.toString(myArray));

        ArrayList<Integer> arrList = toArrayList(myArray);

    }

    public static void swap(Integer[] arr, int index1, int index2) {
        int value = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = value;
    }

    public static  ArrayList toArrayList(Integer[] arr) {
        return new ArrayList(Arrays.asList(arr));
    }
}

