package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        //printData(tasksData);
        //System.out.println("Printing deadlines");
        //printDeadlines(tasksData);

        //System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        //Replacing codes with stream
        //printDataUsingStreams(tasksData);
        //printDeadlineUsingStreams(tasksData);
        printDeadlineUsingStreamsSorted(tasksData);
        System.out.println("Printing after filtering by String");
        for(Task t: filterByString(tasksData, "11")) {
            System.out.println(t);
        }
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static int countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Counting deadlines using streams");
        int count;
        count  = (int) tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();

        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasksData) {
        System.out.println(("Printing data using streams"));
        tasksData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    //Code added to filter task using lambdas
    public static void printDeadlineUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using streams");
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    public static void printDeadlineUsingStreamsSorted(ArrayList<Task> tasksData) {
        System.out.println("Sort and print deadlines using streams");
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredTaskList;
        filteredTaskList = (ArrayList<Task>) tasksData.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());

        return filteredTaskList;
    }

}
