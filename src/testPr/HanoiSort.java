package testPr;

import java.util.*;

public class HanoiSort {

    private final Deque<Integer> main = new ArrayDeque<Integer>();
    private final Deque<Integer> help1 = new ArrayDeque<Integer>();
    private final Deque<Integer> help2 = new ArrayDeque<Integer>();

    private int taskCount = 0;
    private int opCount = 0;

    private void sort(List<Integer> input) {
        taskCount++;
        main.addAll(input);
        sortMain();
        verify();
        main.clear();
    }

    private void verify() {
        if (!help1.isEmpty()) {
            throw new IllegalStateException("non-empty help1\n" + state());
        }
        if (!help2.isEmpty()) {
            throw new IllegalStateException("non-empty help2\n" + state());
        }
        int last = 0;
        for (int i: main) {
            if (last > i) {
                throw new IllegalStateException("unsorted main: " + main);
            }
            last = i;
        }
    }

    private void done() {
        System.out.println();
        System.out.print(opCount + "/" + taskCount);
    }

    private void move(Deque<Integer> from, Deque<Integer> to) {
        if (from == to) throw new IllegalArgumentException("moving from/to " + from);
        Integer i = from.pop();
        if (to != main && !to.isEmpty() && i > to.peek()) {
            throw new IllegalStateException(
                    from + " " + i + " -> " + to);
        }
        to.push(i);
        opCount++;
    }

    private String name(Deque<Integer> stack) {
        return stack == help1 ? "help1" :
                stack == help2 ? "help2" :
                        "main";
    }

    private String state() {
        return "main:  " + main +
                "\nhelp1: " + help1 +
                "\nhelp2: " + help2;
    }

    //=====

    private void ensureMain(Deque<Integer> stack) {
        if (stack != main) {
            throw new IllegalArgumentException("Expected main, got " + name(stack) + "\n" + state());
        }
    }

    private void ensureHelp(Deque<Integer> stack) {
        if (stack == main) {
            throw new IllegalArgumentException("Expected help, got main\n" + state());
        }
    }

    private void ensureHelpers(Deque<Integer> stack1, Deque<Integer> stack2) {
        ensureHelp(stack1);
        ensureHelp(stack2);
    }

    private void sortMain() {
        int height = main.size();
        int topIndex = height;
        while (topIndex == height && height > 1) {
            topIndex = lastIndexOfLargest(height, main);
            height--;
        }
        if (topIndex == height) {
            // is already sorted
            return;
        }
        // split stack at largest element
        int part1Count = topIndex;
        int part2Count = height - topIndex;
        // move largest and first part to help 1
        moveFromMain(part1Count+1, help1, help2);
        // merge both parts to help 2, leaving largest on 1
        mergeToHelp(part2Count, main, part1Count, help1, help2);
        // move largest to main
        move(help1, main);
        // move remaining to main
        moveToMain(height, help2, help1);
    }

    /** Moves elements from main to helper, sorting them */
    private void moveFromMain(int amount, Deque<Integer> target, Deque<Integer> help) {
        if (amount < 1) return;
        ensureHelpers(target, help);
        int topIndex = lastIndexOfLargest(amount, main);
        int part1Count = topIndex;
        int part2Count = amount - topIndex - 1;
        // move first part to help
        moveFromMain(part1Count, help, target);
        // move largest to target
        move(main, target);
        // merge both parts to target
        mergeToHelp(part2Count, main, part1Count, help, target);
    }

    /** Moves elements from helper to main, keeping them sorted */
    private void moveToMain(int amount, Deque<Integer> source, Deque<Integer> help) {
        if (amount < 1) return;
        ensureHelpers(source, help);
        moveHelper(amount-1, source, help);
        move(source, main);
        moveToMain(amount-1, help, source);
    }

    /** Moves elements between helpers */
    private void moveHelper(int amount, Deque<Integer> source, Deque<Integer> target) {
        pushToMain(amount, source);
        popFromMain(amount, target);
    }

    /** Merges top of main and helper to other helper */
    private void mergeToHelp(int mainAmount, Deque<Integer> main, int helpAmount, Deque<Integer> help, Deque<Integer> target) {
        ensureMain(main);
        ensureHelpers(help, target);
        if (mainAmount < 0) mainAmount = 0;
        if (helpAmount < 0) helpAmount = 0;
        while (mainAmount > 0 || helpAmount > 0) {
            // while there is something to merge
            int largestMain = valueOfLargest(mainAmount, main);
            int largestHelp = valueOfLargest(helpAmount, help);
            if (largestMain > largestHelp) {
                // largest is in main
                int index = firstIndexOfLargest(mainAmount, main);
                if (index > 0) {
                    // move excess to help:
                    int mainTop = index;
                    int helpTop = elementsSmallerThan(help, largestMain);
                    if (helpTop > 0) {
                        // 1. move top of help to target
                        moveHelper(helpTop, help, target);
                        // 2. merge old top with excess from main
                        mergeToHelp(mainTop, main, helpTop, target, help);
                    } else {
                        moveFromMain(mainTop, help, target);
                    }
                    mainAmount -= mainTop;
                    helpAmount += mainTop;
                }
                move(main, target);
                mainAmount--;
            } else {
                // largest is at bottom of help
                int helpTop = helpAmount - 1; // largest is at bottom
                // move top to main
                pushToMain(helpTop, help);
                mainAmount += helpTop;
                // move largest to target
                move(help, target);
                helpAmount = 0;
            }
        }
    }

    private void pushToMain(int amount, Deque<Integer> from) {
        for (; amount > 0; amount--) move(from, main);
    }

    private void popFromMain(int amount, Deque<Integer> to) {
        for (; amount > 0; amount--) move(main, to);
    }

    private int firstIndexOfLargest(int height, Deque<Integer> stack) {
        if (height == 0) throw new IllegalArgumentException("height == 0");
        int topValue = 0;
        int topIndex = 0;
        int i = 0;
        for (Integer e: stack) {
            if (e > topValue) {
                topValue = e;
                topIndex = i;
            }
            if (++i == height) break;
        }
        return topIndex;
    }

    private int lastIndexOfLargest(int height, Deque<Integer> stack) {
        if (height == 0) throw new IllegalArgumentException("height == 0");
        int topValue = 0;
        int topIndex = 0;
        int i = 0;
        for (Integer e: stack) {
            if (e >= topValue) {
                topValue = e;
                topIndex = i;
            }
            if (++i == height) break;
        }
        return topIndex;
    }

    private int valueOfLargest(int height, Deque<Integer> stack) {
        int v = Integer.MIN_VALUE;
        for (Integer e: stack) {
            if (height-- == 0) break;
            if (e > v) v = e;
        }
        return v;
    }

    private int elementsSmallerThan(Deque<Integer> stack, int value) {
        int i = 0;
        for (Integer e: stack) {
            if (e >= value) return i;
            i++;
        }
        return i;
    }

    public static void main(String[] args) throws Exception {
        HanoiSort hanoi = new HanoiSort();
        int N = 6;
        for (int len = 1; len <= N; len++) {
            Integer[] input = new Integer[len];
            List<Integer> inputList = Arrays.asList(input);
            int max = N;
            for (int i = 1; i < len; i++) max *= N;
            for (int run = 0; run < max; run++) {
                int n = run;
                for (int i = 0; i < len; n /= N, i++) {
                    input[i] = (n % N)+1;
                }
                try {
                    hanoi.sort(inputList);
                } catch (Exception e) {
                    System.out.println(inputList);
                    e.printStackTrace(System.out);
                    return;
                }
            }
        }
        hanoi.done();
    }
}
