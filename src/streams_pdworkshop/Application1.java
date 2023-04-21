package streams_pdworkshop;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Application1 {
    public static void main(String[] args) {

        Integer[] intArray = {1,2,3,4,5,6,7,6, 8,8,9,10,10};
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));

        String[] wordsArr = {"Hello", "how", "are", "you", "?"};
        List<String> listOfWords = new ArrayList<>(Arrays.asList(wordsArr));

        // Hello ---> 5
        // How ---> 3
        // Are ---> 3
        // You ---> 3
        // ? ---> 1

        // Integer ----> List[String]
        // 3 ----> ['How', 'You', 'Are']
        // 5 ----> ['Hello']
        // 1 ----> ['?']

        // To create a variable in IJ ->  ctrl + alt + V

        //Collectors.groupingBy
        Map<Integer, List<String>> wordLengthMap = listOfWords.stream()
                .collect(Collectors.groupingBy(
                        word -> word.length()
                ));
        System.out.println(wordLengthMap);

        // Collectors.counting
        // Count the number of words which are greater than 2
        Long howManyLongWords = listOfWords.stream()
                .filter(word -> word.length() > 2)
                .collect(Collectors.counting());
        System.out.println(howManyLongWords);

        // Collectors.partitioningBy
        // Print which are greater than 2 and which are not using partitioningBy()
        Map<Boolean, List<String>> wordLengthMap2 =
                listOfWords.stream()
                        .collect(Collectors.partitioningBy(word -> word.length()>2));
        System.out.println(wordLengthMap2);

        // Collectors.joining
        // Joining forms a brand new string -- can have delimiters
        String longWords = listOfWords.stream()
                .filter(word -> word.length() > 2)
                .collect(Collectors.joining(" : "));
        System.out.println(longWords);

        // reduce() -- terminal operation
        /*int s= 0;
        for(int i=0; i<10;i++)
            s=s+i;*/

        // identity = 0
        // accumulator = s

        // reduce()
        // Finding sum of all the numbers in an ListArray
        Integer reducedListsOfIntegers = listOfIntegers.stream()
                .reduce(0, (s, i) ->{
                    System.out.printf("s --> %d i---> %d %n", s, i); // for the sake of demonstration
                    return  s + i;
                        }
                ); // terminal operations
        System.out.println(reducedListsOfIntegers);

        // Use Case: To print all the even numbers using stream
        listOfIntegers.stream()
                .filter(x ->x%2 ==0) // intermediate operations
                .forEach(n ->System.out.println(n)); // terminal operations

        // Use Case: To print all the even numbers using stream and collect them to list
        List<Integer> evens =listOfIntegers.stream()
                .filter(x ->x%2 ==0) // intermediate operations
                .collect(Collectors.toList()); // terminal operations

        // Use Case: To retrieve all the numbers greater than 6 and collect them to set
        Set<Integer> integerList = listOfIntegers.stream()
                                        .filter(x -> x > 6)
                                        .collect(Collectors.toSet());
        System.out.println(integerList);

        // Use Case: Use Case: To print all the even numbers using stream and collect them to set
        Set<Integer> evensSet =listOfIntegers.stream()
                .filter(x ->x%2 ==0) // intermediate operations
                .collect(Collectors.toSet()); // terminal operations
        System.out.println(evensSet);

        // -- Print the words which are less than 4 --
        List<String> words = listOfWords.stream()
                .filter(s ->s.length() < 4) // intermediate operations
                .collect(Collectors.toList()); // terminal operations
        System.out.println(words);

        // -------------------------------------------------------------
        List<String> shoppingList = new ArrayList<>();
        shoppingList.add("pasta");
        shoppingList.add("pineapple");
        shoppingList.add("bread");
        shoppingList.add("coffee");
        shoppingList.add("milk");

        // Use Case:
        // --- sort ---> convert all in  upper case ---> filter out which starts with P ---> collect
        List<String> shoppingLists = shoppingList.stream().sorted()
                .map(items -> items.toUpperCase())
                .filter(item -> item.startsWith("P"))
                .collect(Collectors.toList());
        System.out.println(shoppingLists);

        /// ---------------------XXX----------------------------
    }
}
