package org.acme;

// https://www.techinterviewhandbook.org/grind75

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.smallrye.mutiny.unchecked.Unchecked;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class leetCodeProblems {
    /****************************************************** */
    class Twosum {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> numObj = new HashMap<>();
            for (int i = 0, len = nums.length; i < len; i++) {
                int num = nums[i];
                int complement = target - num;
                if (numObj.get(complement) != null) {
                    return new int[] { numObj.get(complement), i };
                } else {
                    numObj.put(num, i);
                }
            }
            return new int[] {};
        }

    }

    /****************************************************** */
    class ValidParentheses {
        public boolean isValid(String s) {
            if (s.length() % 2 != 0) {
                return false;
            }

            List<Character> expectedCloses = new ArrayList<Character>();
            Map<String, Integer> obj = new HashMap<String, Integer>();
            for (int i = 0, len = s.length(); i < len; i++) {
                // if an open something, we are cool, just put it in the things expected to be
                // closed
                char openParen = '(';
                char closeParen = ')';
                char openSquiggly = '{';
                char closeSquiggly = '}';
                char openBracket = '[';
                char closeBracket = ']';
                char c = s.charAt(i);
                if (c == openParen) {
                    expectedCloses.add(closeParen);
                } else if (c == openSquiggly) {
                    expectedCloses.add(closeSquiggly);
                } else if (c == openBracket) {
                    expectedCloses.add(closeBracket);
                } else {
                    if (expectedCloses.size() > 0 && expectedCloses.get(expectedCloses.size() - 1) == c) {
                        expectedCloses.remove(expectedCloses.size() - 1);
                    } else {
                        return false;
                    }
                }
            }

            return expectedCloses.size() == 0;

            // char[] parentheticals = {'(',')','{','}','[',']'};
            // String opens = "({[";
            // String closes = ")}]";
            // Map<String,Integer> obj= new HashMap<String,Integer>();
            // for(int i=0,len = s.length(); i<len; i++){
            // String character = s.charAt(i)+"";
            // if(parentheticals.contains(character)){
            // if(obj.get(character) != null){
            // obj.put(character,obj.get(character)+1);
            // }else{
            // obj.put(character,1);
            // }
            // }

            // }
            // Boolean matchingParentheses = Boolean.valueOf(obj.get("(") == obj.get(")"));
            // Boolean matchingSquigglies = Boolean.valueOf(obj.get("{") == obj.get("}"));
            // Boolean matchingBrackets = Boolean.valueOf(obj.get("[") == obj.get("]"));
            // return matchingParentheses && matchingSquigglies && matchingBrackets;

            // return true;
        }

    }

    /****************************************************** */
    /*
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class MergeTwoLists {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode merged = new ListNode();
            ListNode tmp = merged;

            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    tmp.next = new ListNode(list1.val);
                    list1 = list1.next;
                } else {
                    tmp.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
                tmp = tmp.next;
            }

            if (list1 != null) {
                tmp.next = new ListNode(list1.val, list1.next);
            }
            if (list2 != null) {
                tmp.next = new ListNode(list2.val, list2.next);
            }

            return merged.next;
        }

    }

    /****************************************************** */
    class sStockPurchaseCalculator {
        public int maxProfit(int[] prices) {
            int leastSoFar = Integer.MAX_VALUE;
            int highestProfitSoFar = 0;
            int profitToConsider = 0;

            for (int i = 0, len = prices.length; i < len; i++) {
                if (prices[i] < leastSoFar) {
                    leastSoFar = prices[i];
                }
                profitToConsider = prices[i] - leastSoFar;
                if (highestProfitSoFar < profitToConsider) {
                    highestProfitSoFar = profitToConsider;
                }
            }
            return highestProfitSoFar;
        }

    }

    /****************************************************** */
    public class ValidPalindrome {
        public boolean isPalindrome(String s) {
            if (s.isEmpty()) {
                return true;
            }
            int left = 0, right = s.length() - 1;
            char cLeft, cRight;
            while (left <= right) {
                cLeft = s.charAt(left);
                cRight = s.charAt(right);
                if (!Character.isLetterOrDigit(cLeft)) {
                    left++;
                } else if (!Character.isLetterOrDigit(cRight)) {
                    right--;
                } else {
                    if (Character.toLowerCase(cLeft) != Character.toLowerCase(cRight)) {
                        return false;
                    }
                    left++;
                    right--;
                }
            }

            return true;
        }

    }

    // ORRRRR
    // class valid-palindrome { -- have to iterate more than one time through,
    // right? it's the regexp that slows it down as well as splitting it.
    public class ValidPalindrome1 {
        public boolean isPalindrome(String s) {
            String[] arr = s.replaceAll("[^a-zA-Z0-9]", "").split("");
            for (int i = 0, len = arr.length; i < len / 2; i++) {
                if (!arr[i].equalsIgnoreCase(arr[len - i - 1])) {
                    return false;
                }
            }
            return true;
        }
    }

    /****************************************************** */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class InvertTree {

        public TreeNode invertTree(TreeNode root) {
            if (root == null)
                return null;
            return new TreeNode(root.val, invertTree(root.right), invertTree(root.left));
        }

    }

    /****************************************************** */
    class ValidAnagram {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            } else if (s.equals(t)) {
                return true;
            }

            Map<String, Integer> map = new HashMap<String, Integer>();
            for (int i = 0, len = s.length(); i < len; i++) {
                char sChar = s.charAt(i);
                char tChar = t.charAt(i);
                System.out.println("sChar: <" + sChar + ">");
                System.out.println("tChar: <" + tChar + ">");
                if (sChar != tChar) {
                    String sCharToIncrement = "s|" + sChar; // "s|e" +1, -- "s|o" +1
                    String tCharToIncrement = "t|" + tChar; // "t|o" +1, -- "t|e" +1
                    String sCharToDecrement = "s|" + tChar; // "s|o" no minus, because not there -- "s|e" +1
                    String tCharToDecrement = "t|" + sChar; // "t|e" no minus, because not there -- "t|o" +1
                    mapChars(sCharToIncrement, tCharToIncrement, sCharToDecrement, tCharToDecrement, map);
                    // System.out.println("map: <"+map+">");
                    // System.out.println("_______________________________");
                }
            }
            // System.out.println("map: <"+map+">");
            return map.isEmpty();
        }

        private void mapChars(String sCharToIncrement, String tCharToIncrement, String sCharToDecrement,
                String tCharToDecrement, Map<String, Integer> map) {
            int sCharToIncrementCount = map.get(sCharToIncrement) == null ? 0 : (Integer) map.get(sCharToIncrement);
            int tCharToIncrementCount = map.get(tCharToIncrement) == null ? 0 : (Integer) map.get(tCharToIncrement);
            int sCharToDecrementCount = map.get(sCharToDecrement) == null ? 0 : (Integer) map.get(sCharToDecrement);
            int tCharToDecrementCount = map.get(tCharToDecrement) == null ? 0 : (Integer) map.get(tCharToDecrement);

            // System.out.println("sCharToIncrementCount: <"+sCharToIncrementCount+">");
            // System.out.println("tCharToIncrementCount: <"+tCharToIncrementCount+">");
            // System.out.println("sCharToDecrementCount: <"+sCharToDecrementCount+">");
            // System.out.println("tCharToDecrementCount: <"+tCharToDecrementCount+">");

            if (sCharToIncrementCount == -1) {
                // System.out.println("removing: sCharToIncrement");
                map.remove(sCharToIncrement);
            } else {
                // System.out.println("incrementing: sCharToIncrement");
                map.put(sCharToIncrement, sCharToIncrementCount + 1);
            }

            if (tCharToIncrementCount == -1) {
                // System.out.println("removing: tCharToIncrement");
                map.remove(tCharToIncrement);
            } else {
                // System.out.println("incrementing: tCharToIncrement");
                map.put(tCharToIncrement, tCharToIncrementCount + 1);
            }

            if (sCharToDecrementCount == 1) {
                // System.out.println("removing: sCharToDecrement");
                map.remove(sCharToDecrement);
            } else {
                // System.out.println("decrementing: sCharToDecrement");
                map.put(sCharToDecrement, sCharToDecrementCount - 1);
            }
            if (tCharToDecrementCount == 1) {
                // System.out.println("removing: tCharToDecrement");
                map.remove(tCharToDecrement);
            } else {
                // System.out.println("decrementing: tCharToDecrement");
                map.put(tCharToDecrement, tCharToDecrementCount - 1);
            }
        }

    }

    // for s=ooeee,t=eeeoo
    // needs:
    // first->need to end up with {t|o=1,s|e=1};
    // second->need to end up with {t|o=2,s|e=2};
    // second->
    // if exists s|e, then if s|e=1, remove(s|e), else s|e=s|e--
    // if exists t|o, then if t|o=1, remove(t|o), else t|o=t|o--
    class OptimalValidAnagram {
        public boolean isAnagram(String s, String t) {
            // Create fixed size int array of 26 Englist alphabet lower case
            int[] engAlphabet = new int[26];

            // Create char array from string s and string t
            char[] char1 = s.toCharArray();
            char[] char2 = t.toCharArray();

            // Loop through the char1 array and it is increasing the index position
            for (int i = 0; i < char1.length; i++) {
                engAlphabet[char1[i] - 'a']++;
            }

            // Loop through the char2 array and it is decreasing the index position

            for (int i = 0; i < char2.length; i++) {

                engAlphabet[char2[i] - 'a']--;
            }

            // In the last step, we need to make sure , our int array is 0.
            // If there is any int array value not a zero that means , it is not
            // anagram
            for (int letter : engAlphabet) {
                if (letter != 0) {
                    return false;
                }
            }

            return true;

        }

    }

    // ORRR Runtime: 3 ms, faster than 94.52% of Java online submissions for Valid
    // Anagram.
    // Memory Usage: 42.3 MB, less than 94.77% of Java online submissions for Valid
    // Anagram.
    class ElegantAlmostOptimalValidAnagram {
        public boolean isAnagram3(String s, String t) {
            char[] first = s.toCharArray();
            char[] second = t.toCharArray();
            return Arrays.equals(sort(first), sort(second));
        }

        private char[] sort(char[] arr) {
            Arrays.sort(arr);
            return arr;
        }

    }

    /****************************************************** */
    class FindIndexOfNumberInOrderedArray {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int midpoint = (left + right) / 2;
                int num = nums[midpoint];
                if (num == target)
                    return midpoint;
                else if (num < target)
                    left = midpoint + 1;
                else
                    right = midpoint - 1;
            }

            return -1;
        }

    }

    // ORRRRR (my attempt, but not flawed)
    class FindIndexOfNumberInOrderedArray2 {
        public int search(int[] nums, int target) {
            int low = 0;
            int high = nums.length - 1;
            return recursive(nums, low, high, target);
        }

        public int recursive(int[] nums, int low, int high, int target) {
            if (low > high)
                return -1;
            int mid = (low + high) / 2;

            if (nums[mid] == target)
                return mid;

            if (target > nums[mid])
                return recursive(nums, mid + 1, high, target);
            else
                return recursive(nums, low, mid - 1, target);
        }
    }

    // ORRRRRRRRRRR
    public class FindIndexOfNumberInOrderedArray3 {
        private int[] nums;

        public int search(int[] nums, int target) {
            this.nums = nums;
            return binarySearch(0, nums.length - 1, target);
        }

        public int binarySearch(int left, int right, int target) {

            if (left <= right && left < nums.length) {
                int mid = left + (right - left) / 2; // no int overflow here

                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    return binarySearch(left, mid - 1, target);
                } else {
                    return binarySearch(mid + 1, right, target);
                }
            }

            return -1;
        }

    }

    /****************************************************** */
    public class FloodFill { // this junk is foolish and I am not well-versed in photo
        // editing, so this is just canned logic
        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            if (image[sr][sc] != color)
                doFloodFill(image, sr, sc, image[sr][sc], color);
            return image;
        }

        private void doFloodFill(int[][] image, int i, int j, int c0, int c1) {
            if (i < 0 || j < 0 || i >= image.length || j >= image[0].length || image[i][j] != c0)
                return;
            image[i][j] = c1;
            doFloodFill(image, i, j - 1, c0, c1);
            doFloodFill(image, i, j + 1, c0, c1);
            doFloodFill(image, i - 1, j, c0, c1);
            doFloodFill(image, i + 1, j, c0, c1);
        }

    }

    /****************************************************** */
    class MaxContiguousSubArrayON {
        public int maxSubArray(int[] nums) {
            int sum = 0, max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                sum = sum < 0 ? nums[i] : (sum + nums[i]);
                max = Math.max(sum, max);
            }
            return max;
        }

    }

    // ORRRRRRRR
    // one of the least big o depth-wise.
    class MaxContiguousSubArrayLowMemDepth {

        public int maxSubArray1(int[] nums) {
            int csum = nums[0];
            int osum = nums[0];

            for (int i = 1; i < nums.length; i++) {
                if (csum >= 0) {
                    csum += nums[i];
                } else {
                    csum = nums[i];
                }

                if (csum > osum) {
                    osum = csum;
                }
            }
            return osum;
        }

    }

    /****************************************************** */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class CheckBinaryTreeBalanced { // can differ by no more than one,
        // length-wise
        Boolean isBalanced = true;

        public boolean isBalanced(TreeNode root) {
            checkBalanced(root);
            return isBalanced;
        }

        private int checkBalanced(TreeNode root) {
            if (root == null)
                return 0;
            int left = checkBalanced(root.left);
            int right = checkBalanced(root.right);
            if (Math.abs(left - right) > 1)
                isBalanced = false;
            return Math.max(left, right) + 1;
        }

    }

    /****************************************************** */
    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class checkCycleExists {
        // really fast

        public boolean hasCycle(ListNode head) {
            ListNode walker = head;
            ListNode runner = head;
            while (runner != null && runner.next != null && runner.next.next != null) {
                walker = walker.next;
                runner = runner.next.next;
                if (walker == runner)
                    return true;
            }
            return false;
        }
    }
    // ORRRR (uses a map)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class CheckSycleExistsFast { // low memory usage
        public boolean hasCycle2(ListNode head) {

            if (head == null || head.next == null)
                return false;

            ListNode next = head.next;
            ListNode fastNext = next.next;

            Map<ListNode, Integer> seenMap = new HashMap<>();

            while (next != null && fastNext != null) {

                if (seenMap.containsKey(fastNext)) {
                    return true;
                } else {
                    seenMap.put(next, 90);
                    seenMap.put(fastNext, 90);
                }

                if (next == fastNext) {
                    return true;
                }

                next = next.next;
                fastNext = fastNext.next;

                if (fastNext == null) {
                    return false;
                } else {
                    fastNext = fastNext.next;
                }
            }

            return false;
        }

    }

    /****************************************************** */
    // So this one was really confusing.
    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
    // So this is first instantiated, then an int is pushed, then and int is popped,
    // then the stack is peeped. Since Queues are LIFO and Stacks are LIFO, we need
    // to have two Stacks to see the reverse order (both push and pop have to be
    // reversed). Heres an input:
    // - commands: ["MyQueue","push","push","peek","pop","empty"]
    // - arr: [[],[1],[2],[],[],[]]
    // This is first instantiating the class, then
    // obj.push(arr[indexOf(command)]);,followed by
    // obj.push(arr[indexOf(command)]);,
    // obj.peek();obj.pop();,obj.empty();. These *should* return the expected array
    // as the return types. The return array is what is judged. There is an approach
    // where the
    class ImplementingQueueStacks {
        private Stack<Integer> queue;
        private Stack<Integer> tmp;

        public void MyQueue() {
            queue = new Stack<>();
            tmp = new Stack<>();
        }

        public void push(int x) {
            // only push the new item when the order is maintained as pre-condition to push
            while (!tmp.isEmpty()) {
                queue.push(tmp.pop());
            }
            queue.push(x);
        }

        public int pop() {
            // all contents of queue -> tmp as a pre-condition to pop
            while (!queue.isEmpty()) {
                tmp.push(queue.pop());
            }

            return tmp.pop();
        }

        public int peek() {
            // all contents of queue -> tmp as a pre-condition to pop
            while (!queue.isEmpty()) {
                tmp.push(queue.pop());
            }

            return tmp.peek();
        }

        public boolean empty() {
            return queue.isEmpty() && tmp.isEmpty();
        }
    }

    // ORRRRRR
    // This one is a lot more optimized, IDK why
    class ImplementingQueueStacksLowMemUsage {
        // Stacks are Last In - First Out (LIFO)

        // push incoming elements
        Stack<Integer> input;

        // peek / pop from here
        Stack<Integer> output;
        // Queues however are First In - First Out (FIFO)

        public void MyQueue() {
            input = new Stack<Integer>();
            output = new Stack<Integer>();
        }

        public void push(int x) {
            // Pushes element x to the back of the queue.
            // instead of top of the stack
            input.push(x);
        }

        // make use of peek
        public int pop() {
            // Removes the element from the front of the queue and returns it.
            // instead of removing top of the stack
            peek();
            return output.pop();
        }

        public int peek() {
            // Returns the element at the front of the queue.
            // instead of top of queue
            if (output.empty()) {
                while (!input.empty()) {
                    output.push(input.pop());
                }
            }
            return output.peek();
        }

        public boolean empty() {
            // Returns true if the queue is empty, false otherwise.
            return input.empty() && output.empty();
        }
    }

    /****************************************************** */
    /*
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     */
    // recursive
    public class isBadVersionMine /* extends VersionControl */ {
        private int lowestBadVersionFoundSoFar = Integer.MAX_VALUE;
        private int offset = Integer.MAX_VALUE / 2;

        public int firstBadVersion(int n) {
            // System.out.println("___________________");
            // System.out.println("n: <"+n+">, lowestBadVersionFoundSoFar:
            // <"+lowestBadVersionFoundSoFar+">, offset: <"+offset+">");
            Boolean nIsBad = isBadVersion(n);
            if (nIsBad) {
                // System.out.println("isBad!");
                lowestBadVersionFoundSoFar = n;
                if (!isBadVersion(n - 1) || n == 1) {
                    // System.out.println("Just what we wanted!");
                    return n;
                } else {
                    lowestBadVersionFoundSoFar = n - 1;
                    offset = Math.min(n, offset) / 2 + 1;
                    // System.out.println("n-1 is bad too, updating lowestBadVersionFoundSoFar:
                    // <"+lowestBadVersionFoundSoFar+">, offset: <"+offset+">");
                    return firstBadVersion(n - offset);
                }
            } else {
                offset /= 2;
                // System.out.println("version is not bad, n: <"+n+"> offset/2:
                // <"+(offset)+">");
                return firstBadVersion(lowestBadVersionFoundSoFar - offset);
            }
        }

        private Boolean isBadVersion(int n) {
            if (n < 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    // ORRRR
    public class isBadVersionElegantRecursive /* extends VersionControl */ {
        public int firstBadVersion(int n) {
            return isBadVersion(1, n);
        }

        private int isBadVersion(int start, int end) {
            int mid = start + (end - start) / 2;
            return start >= end
                    ? isBadVersion(end)
                            ? end
                            : -1
                    : isBadVersion(mid)
                            ? isBadVersion(start, mid)
                            : isBadVersion(mid + 1, end);
        }

        private Boolean isBadVersion(int n) {
            if (n < 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    // ORRRR
    // optimized
    public class isBadVersionOptimized /* extends VersionControl */ {
        public int firstBadVersion(int n) {
            int min = 1;
            int midpoint;
            while (min < n) {
                // need to be offset(min) agnostic, so get midpoint on top of min
                midpoint = (n - min) / 2 + min;
                if (isBadVersion(midpoint)) {
                    // get right half of left half
                    n = midpoint;
                } else {
                    // get left half of right half, excluding current not-bad version
                    min = midpoint + 1;
                }
            }

            return min;
        }

        private Boolean isBadVersion(int n) {
            if (n < 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    /****************************************************** */
    class RansomNoteMapMine {
        public boolean canConstruct(String ransomNote, String magazine) {

            Map<Character, Integer> ransomNoteMap = new HashMap<>();
            Map<Character, Integer> magazineMap = new HashMap<>();

            char[] ransomNoteArr = ransomNote.toCharArray();
            char[] magazineArr = magazine.toCharArray();

            doMap(ransomNoteArr, ransomNoteMap);
            doMap(magazineArr, magazineMap);

            Set<Character> ransomNoteKeySet = ransomNoteMap.keySet();

            for (char c : ransomNoteKeySet) {
                if (magazineMap.get(c) == null || ransomNoteMap.get(c) > magazineMap.get(c)) {
                    return false;
                }
            }

            return true;
        }

        private void doMap(char[] arr, Map<Character, Integer> map) {
            for (int i = 0, len = arr.length; i < len; i++) {
                char c = arr[i];
                var mapped = map.get(c);
                if (mapped != null) {
                    map.put(c, (int) mapped + 1);
                } else {
                    map.put(c, 1);
                }

            }
        }
    }

    // ORRRR
    // This one is confusing. I know the int[26] is for the chars in the alphabet,
    // but confused as to why there is not one 'a' for many ransom note 'a's?
    class RansomNoteSolutionFast {
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] nextIndexToStart = new int[26];
            for (char temp : ransomNote.toCharArray()) {
                // returns -1 if no index found!
                int index = magazine.indexOf(temp, nextIndexToStart[temp - 'a']);
                if (index == -1)
                    return false;
                nextIndexToStart[temp - 'a'] = index + 1;
            }
            return true;
        }
    }

    // ORRRR
    class RansomNoteSolutionMoreUnderstandable {
        public boolean canConstruct(String ransomNote, String magazine) {
            if (ransomNote.length() > magazine.length())
                return false;

            int[] arr = new int[26];
            int i = 0;
            // add char options
            for (char c : magazine.toCharArray()) {
                arr[c - 'a']++;
            }
            // reduce char options, if less than
            for (char c : ransomNote.toCharArray()) {
                arr[c - 'a']--;
                if (arr[c - 'a'] < 0) { // can be zero for some reason, maybe the first entry of arr[c-'a']=0?
                    return false;
                }
            }
            return true;
        }
    }

    /****************************************************** */
    // this is of the order of the Fibonacci Sequence
    // this algorithm is right, but quickly takes up too much memory.
    // I would assume it's technically a stack overflow error. soo
    // many waiting to find out what the next one gets.
    class ClimbingStairs {
        public int climbStairs(int n) {
            if (n < 2)
                return n / 2 + 1;
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    // working
    class ClimbingStairsWorking {
        public int climbStairs(int n) {
            // if (n < 2)
            // return n / 2 + 1;
            // intuitive first answer through recursion, but
            // ran into stack overflow around 43 deep.
            // return climbStairs(n-1)+climbStairs(n-2);
            int previous = 1, current = 1;
            while (n-- > 1) {
                previous = (current += previous) - previous;
            }
            return current;

        }
    }

    /****************************************************** */
    class longestPalindromeMine {
        public int longestPalindrome(String s) {
            int[] caps = new int[26];
            int[] chars = new int[26];
            int len = 0;

            for (char c : s.toCharArray()) {
                if (c >= 97)
                    chars[c - 'a']++;
                else
                    caps[c - 'A']++;
            }

            // System.out.println("caps: <"+Arrays.toString(caps)+">, chars:
            // <"+Arrays.toString(chars)+">");

            for (int i = 0; i < 26; i++) {
                len += 2 * (caps[i] / 2);
                len += 2 * (chars[i] / 2);
                ;
            }

            return len % 2 == 1 || len == s.length() ? len : len + 1;

        }
    }

    /****************************************************** */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class reverseLinkedListMine {
        int count = 0;
        ListNode node;

        public ListNode reverseList(ListNode head) {
            if (head == null)
                return null;
            List<Integer> arr = new ArrayList<>();
            do {
                arr.add(head.val);
                head = head.next;
            } while (head != null);
            // System.out.println("arr: <"+arr+">");

            for (int i = 0, len = arr.size(); i < len; i++) {
                int val = arr.get(i);
                node = new ListNode(val, node);
                // System.out.println("arr.get(i-1): <"+arr.get(i-1)+">");
            }
            return node;
        }
    }
    // ORRRR

    class reverseLinkedListTheOneIwasTryingToGet {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }
            return prev;
        }
    }

    /****************************************************** */
    /*
     * Given an array nums of size n, return the majority element.
     * 
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You
     * may assume that the majority element always exists in the array.
     */
    class BoyceMooreVotingAlgorithm {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }
    // ORRRRRRRRR

    public class MajorityElementOptimal {
        public int majorityElement(int[] num) {
            // it's either the majority element or it isn't.
            int major = num[0], count = 1;
            for (int i = 1; i < num.length; i++) {
                if (count == 0) {
                    count++;
                    major = num[i];
                } else if (major == num[i]) {
                    count++;
                } else
                    count--;

            }
            return major;
        }
    }

    // ORRRRRR
    class MajorityElementMine {
        public int majorityElement(int[] nums) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int key = Integer.MIN_VALUE;
            int max = 0;
            for (int num : nums) {
                var mapped = map.get(num);
                if (mapped != null) {
                    mapped++;
                    map.put(num, mapped);
                } else {
                    mapped = 1;
                    map.put(num, mapped);
                }
                if (map.get(num) > max) {
                    key = num;
                    max = mapped;
                }
            }

            int[] ints = new int[nums.length];

            return key;
        }
    }

    /****************************************************** */
    // Given two binary strings a and b, return their sum as a binary string.

    class AddBinaries {
        public String addBinary(String a, String b) {
            int i = a.length() - 1, j = b.length() - 1;
            StringBuilder ans = new StringBuilder();
            int carry = 0;

            while (i >= 0 || j >= 0) {
                int sum = carry;
                if (i >= 0)
                    sum = sum + a.charAt(i) - '0';
                if (j >= 0)
                    sum = sum + b.charAt(j) - '0';
                ans.append(sum % 2);
                carry = sum / 2;
                i--;
                j--;

            }
            if (carry != 0)
                ans.append(1);

            return ans.reverse().toString();
        }
    }

    // ORRRRRRR
    class mySolutionIncomplete {
        public String addBinary(String a, String b) {

            int aLen = a.length();
            int bLen = b.length();
            int aOffset = aLen > bLen ? 0 : bLen - aLen;
            int bOffset = bLen > aLen ? 0 : aLen - bLen;
            int carryOver = 0;
            System.out.println("aOffset:<" + aOffset + ">, bOffset:<" + bOffset + ">");

            for (int i = Math.max(aLen, bLen) - 1; i > -1; i--) {
                int aIndex = i - aOffset;
                int bIndex = i - bOffset;

                System.out.println("aIndex: <" + aIndex + ">, bIndex: <" + bIndex + ">");

                int aInt = aIndex > -1 && (a.charAt(aIndex) + "") != null ? Integer.parseInt(a.charAt(aIndex) + "") : 0;
                int bInt = bIndex > -1 && (b.charAt(bIndex) + "") != null ? Integer.parseInt(b.charAt(bIndex) + "") : 0;

                int sum = aInt + bInt;
                // use listNode to do the next ones, add up a string by looping through it,
                // finally send back the generated string.

                System.out.println("aInt: <" + aInt + ">, bInt: <" + bInt + ">, sum: <" + sum + ">");
            }

            return "test";
        }

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /****************************************************** */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public class DiameterOfBinaryTree {
        public int diameterOfBinaryTree(TreeNode root) {
            // ints are passed by value, rather arrays are passed by reference. This makes
            // it a little faster apparently
            int[] diameter = new int[1];
            height(root, diameter);
            return diameter[0];
        }

        private int height(TreeNode node, int[] diameter) {
            if (node == null) {
                return 0;
            }
            int lh = height(node.left, diameter);
            int rh = height(node.right, diameter);
            diameter[0] = Math.max(diameter[0], lh + rh);
            return 1 + Math.max(lh, rh);
        }
    }

    // ORRRRR
    class DiameterOfBinaryTreeFaster {
        int[] result = new int[1];

        public int diameterOfBinaryTree(TreeNode root) {
            calculateHeight(root);
            return result[0];
        }

        public int calculateHeight(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftHeight = calculateHeight(root.left);
            int rightHeight = calculateHeight(root.right);

            result[0] = Math.max(result[0], (leftHeight + rightHeight));

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // ORRRR
    class DiameterOfBinaryTreeLeastMemory {
        private int diameter;

        public int diameterOfBinaryTree(TreeNode root) {
            diameter = 0;
            longestPath(root);
            return diameter;
        }

        public int longestPath(TreeNode root) {
            if (root == null)
                return 0;
            int leftPath = longestPath(root.left);
            int rightPath = longestPath(root.right);
            if (leftPath + rightPath > diameter)
                diameter = leftPath + rightPath;
            return Math.max(leftPath, rightPath) + 1;
        }
    }

    /****************************************************** */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    // got this one on my own, which is awesome.
    class MiddleOfLinkedListVICTORY {
        ListNode mid;
        ListNode fast;

        public ListNode middleNode(ListNode head) {
            mid = head;
            fast = head;
            return getMiddleNode(head);
        }

        private ListNode getMiddleNode(ListNode head) {
            // System.out.println("head.val: <"+head.val+">");
            if (fast == null || fast.next == null)
                return head;
            mid = head;
            fast = fast.next.next;
            return getMiddleNode(head.next);
        }
    }

    // ORRRR
    class MiddleOfLinkedListLeastMemory {
        public ListNode middleNode(ListNode head) {
            ListNode mid = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                mid = mid.next;
                fast = fast.next.next;
            }
            return mid;
        }
    }

    /****************************************************** */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    /*
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum
     * Depth of Binary Tree.
     * Memory Usage: 42.8 MB, less than 74.74% of Java online submissions for
     * Maximum Depth of Binary Tree.
     */
    class MaximumDepthOfBinaryTree {
        public int maxDepth(TreeNode root) {
            // Base Condition
            if (root == null)
                return 0;
            // Hypothesis
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            // Induction
            return Math.max(left, right) + 1;
        }
    }

    // ORRRRRRR
    @SuppressWarnings("Unchecked")
    class MaxDepthOptimalMemory {
        public int maxDepth(TreeNode root) {
            Queue<TreeNode> tree = new LinkedList();
            tree.offer(root);
            int depth = 0;

            if (root == null) {
                return depth;
            }

            // can't do this because base case null
            // depth = 1;
            /*
             * while (!tree.isEmpty()) {
             * TreeNode node = tree.poll();
             * if (node.left != null)
             * tree.offer(node.left);
             * if (node.right != null)
             * tree.offer(node.right);
             * System.out.println("Node.val " + node.val );
             * if (node.left != null || node.right != null )
             * depth += 1;
             * }
             * 
             * return depth;
             */

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            return Math.max(left, right) + 1;
        }
    }

    /****************************************************** */
    class ContainsDuplicateMine {
        public boolean containsDuplicate(int[] nums) {
            Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
            for (int num : nums) {
                if (map.get(num) != null) {
                    return true;
                }
                map.put(num, true);
            }
            return false;
        }
    }

    // ORRRRR
    class ContainsDuplicateOptimalSpeed {
        public boolean containsDuplicate(int[] nums) {
            Set numsSet = new HashSet<Integer>();
            for (int i = 0, len = nums.length; i < len; i++) {
                if (!numsSet.add(nums[i]))
                    return true;
            }
            return false;
        }
    }

    // ORRRRR
    class ContainsDuplicateOptimalDepth {
        public boolean containsDuplicate(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++)
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            for (Map.Entry<Integer, Integer> entry : map.entrySet())
                if (entry.getValue() >= 2)
                    return true;

            return false;
        }
    }

    /****************************************************** */

    class InsertInterval {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> ans = new ArrayList<>();
            int[] tmp = newInterval;

            for (int i = 0; i < intervals.length; i++) {
                int[] interval = intervals[i];
                int min = interval[0], max = interval[1], tmpMin = tmp[0], tmpMax = tmp[1];

                if (min > tmpMax) {
                    ans.add(tmp);
                    tmp = interval;
                } else if (max >= tmpMin) {
                    tmp = new int[] {
                            Math.min(min, tmpMin),
                            Math.max(max, tmpMax)
                    };
                } else {
                    ans.add(interval);
                }
            }
            ans.add(tmp);
            return ans.toArray(new int[ans.size()][2]);
        }
    }

    // ORRRR
    // most sensical
    class InsertIntervalFast {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> result = new ArrayList<>();
            int i = 0;
            int start = newInterval[0];
            int end = newInterval[1];

            // below the new Interval minimum, no change necessary
            while (i < intervals.length && intervals[i][1] < start) {
                result.add(intervals[i++]);
            }

            // intersection of interval indexed arrays and new Interval span, absorb into
            // one
            while (i < intervals.length && intervals[i][0] <= end) {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
                i++;
            }
            // add coalesced interval
            result.add(new int[] { start, end });

            // above the new Interval maximum, no change necessary
            while (i < intervals.length) {
                result.add(intervals[i++]);
            }

            /*
             * can make this into one line and avoid iterating through result list..
             * int[][] res= new int[result.size()][];
             * 
             * for(int j = 0; j<result.size(); j++){
             * res[j] = result.get(j);
             * }
             * return res;
             */

            return result.toArray(new int[result.size()][2]);
        }
    }

    /****************************************************** */
    class ZeroOneMatrixMySolutionTooLong {
        int[][] matrix;

        public int[][] updateMatrix(int[][] mat) {
            matrix = mat;
            int[][] out = mat;
            for (int i = 0, matLen = mat.length; i < matLen; i++) {
                int[] row = mat[i];
                for (int j = 0, rowLen = row.length; j < rowLen; j++) {
                    int cell = row[j];
                    if (cell == 0) {
                        continue;
                    } else {
                        out[i][j] = Math.min(
                                Math.min(
                                        findZeroFrom(i, j - 1, 1, "LEFT"),
                                        findZeroFrom(i, j + 1, 1, "RIGHT")),
                                Math.min(
                                        findZeroFrom(i - 1, j, 1, "UP"),
                                        findZeroFrom(i + 1, j, 1, "DOWN")));
                    }
                }
            }
            return mat;
        }

        private int findZeroFrom(int i, int j, int distanceSoFar, String direction) {
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
                return Integer.MAX_VALUE;
            }
            if (matrix[i][j] == 0) {
                return distanceSoFar;
            }
            distanceSoFar++;
            switch (direction) {
                case "LEFT":
                    return Math.min(
                            findZeroFrom(i, j - 1, distanceSoFar, "LEFT"),
                            findZeroFrom(i - 1, j - 1, distanceSoFar, "UP"));
                case "RIGHT":
                    return Math.min(
                            findZeroFrom(i, j + 1, distanceSoFar, "RIGHT"),
                            findZeroFrom(i + 1, j + 1, distanceSoFar, "DOWN"));
                case "UP":
                    return Math.min(
                            findZeroFrom(i - 1, j, distanceSoFar, "UP"),
                            findZeroFrom(i - 1, j + 1, distanceSoFar, "RIGHT"));
                case "DOWN":
                    return Math.min(
                            findZeroFrom(i + 1, j, distanceSoFar, "DOWN"),
                            findZeroFrom(i + 1, j - 1, distanceSoFar, "LEFT"));
                default:
                    System.out.println("BAD CASE! <" + direction + ">");
                    break;
            }
            return Integer.MAX_VALUE;
        }
    }

    // ORRRRR
    class ZeroOneMatrixFastSolution {
        public int[][] updateMatrix(int[][] matrix) {
            int matrixLen = matrix.length,
                    rowLen = matrix[0].length,
                    maxDistance = matrixLen + rowLen; // The distance of cells is up to (matLen+rowLen)
            // find value based on top down
            for (int row = 0; row < matrixLen; row++) {
                for (int col = 0; col < rowLen; col++) {
                    if (matrix[row][col] == 0)
                        continue;
                    int top = maxDistance, left = maxDistance;
                    if (row - 1 >= 0)
                        top = matrix[row - 1][col];
                    if (col - 1 >= 0)
                        left = matrix[row][col - 1];
                    matrix[row][col] = Math.min(top, left) + 1;
                }
            }
            // find value based on bottom up
            for (int row = matrixLen - 1; row >= 0; row--) {
                for (int col = rowLen - 1; col >= 0; col--) {
                    if (matrix[row][col] == 0)
                        continue;
                    int bottom = maxDistance, right = maxDistance;
                    if (row + 1 < matrixLen)
                        bottom = matrix[row + 1][col];
                    if (col + 1 < rowLen)
                        right = matrix[row][col + 1];
                    // set final value on min of value from above, below, left, and right.
                    matrix[row][col] = Math.min(matrix[row][col], Math.min(bottom, right) + 1);
                }
            }
            return matrix;
        }
    }

    /****************************************************** */
    class Solution {
        public int[][] kClosest(int[][] points, int k) {
            List<int[]> out = new ArrayList<int[]>();
            if (points == null)
                return null;
            if (points.length == 1)
                return points;
            long min = Long.MAX_VALUE;
            for (int[] point : points) {
                long distanceToOrigin = getDistanceToOrigin(point);
                if (distanceToOrigin < min) {
                    out = new ArrayList<int[]>();
                    out.add(point);
                } else if (distanceToOrigin == min) {
                    out.add(point);
                }
            }

            return out.toArray(new int[out.size()][2]);

        }

        private long getDistanceToOrigin(int[] point) {
            return (long) Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
        }
    }

    /****************************************************** */

    class FindMinDistanceFromOrigin { // not within scope of following challenge, but cool nonetheless
        public int[][] kClosest(int[][] points, int k) {
            List<int[]> out = new ArrayList<int[]>();
            if (points == null)
                return null;
            if (points.length == 1)
                return points;
            double min = Double.MAX_VALUE;
            for (int[] point : points) {
                double distanceToOrigin = getDistanceToOrigin(point);
                System.out.println(
                        "point: <" + Arrays.toString(point) + ">, distanceToOrigin: <" + distanceToOrigin + ">");
                if (distanceToOrigin < min) {
                    System.out.println("found a new min! resetting");
                    min = distanceToOrigin;
                    out = new ArrayList<int[]>();
                    out.add(point);
                } else if (distanceToOrigin == min) {
                    System.out.println("equivalence! adding point.");
                    out.add(point);
                }
                System.out.println("-----------------------------");
            }

            // return out.stream().limit(k).collect(Collectors.toList()).toArray(new
            // int[out.size()][2]);
            return out.toArray(new int[out.size()][2]);

        }

        private double getDistanceToOrigin(int[] point) {
            double radicand = Math.pow(point[0], 2) + Math.pow(point[1], 2);
            System.out.println("radicand: <" + radicand + ">");
            return Math.sqrt(radicand);
        }
    }

    /****************************************************** */

    class KClosestPointsToOriginMineFusterCluck {
        public int[][] kClosest(int[][] points, int k) {
            List<int[]> out = new ArrayList<int[]>();
            Map<Double, List<Integer>> map = new HashMap<Double, List<Integer>>();

            for (int i = 0, len = points.length; i < len; i++) {
                int[] point = points[i];
                double distanceToOrigin = getDistanceToOrigin(point);
                List<Integer> mappedPoints = map.get(distanceToOrigin);

                // dealing with point distance as yet unencountered
                if (mappedPoints == null) {
                    // System.out.println("here, making new array");
                    mappedPoints = new ArrayList<Integer>();
                }
                // System.out.println("point: <"+Arrays.toString(point)+">");
                mappedPoints.add(i);
                map.put(distanceToOrigin, mappedPoints);
            }

            // now, sort the keys and iterate through that array.
            List<Double> keys = new ArrayList<Double>(map.keySet());
            Collections.sort(keys);
            // System.out.println("keys: <"+keys+">");

            // add the prescribed count
            int safety = keys.size();
            while (k > 0 && safety > 0) {
                for (int i = 0, len = keys.size(); i < len; i++) {
                    double distanceToOrigin = keys.get(i);
                    List<Integer> equidistantPoints = map.get(distanceToOrigin);
                    // System.out.println("i: <"+i+">, points: <"+equidistantPoints+">, map:
                    // <"+map+">");
                    if (equidistantPoints.size() == 1 && k > 0) {
                        // System.out.println("equidistantPoints[0]: <"+equidistantPoints.get(0)+">");
                        int index = equidistantPoints.get(0);
                        out.add(points[index]);
                        k--;
                    } else if (k > 0) {
                        for (int j = 0, listLen = equidistantPoints.size(); j < listLen; j++) {
                            Integer index = equidistantPoints.get(j);
                            if (k > 0) {
                                out.add(points[index]);
                                k--;
                            }
                        }
                    }
                    safety--;
                }
            }

            return out.toArray(new int[out.size()][2]);

        }

        private double getDistanceToOrigin(int[] point) {
            double radicand = Math.pow(point[0], 2) + Math.pow(point[1], 2);
            // System.out.println("radicand: <" + radicand + ">");
            return Math.sqrt(radicand);
        }
    }

    // ORRRRR
    class KClosestPointsToOriginWTFDude {
        Random rand = new Random();

        public int[][] kClosest(int[][] points, int k) {
            int n = points.length;
            random_select(points, 0, n - 1, k);
            return Arrays.copyOfRange(points, 0, k);
        }

        public void random_select(int[][] points, int left, int right, int k) {
            int pivotId = left + rand.nextInt(right - left + 1);
            int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
            swap(points, right, pivotId);
            int i = left - 1;
            for (int j = left; j < right; ++j) {
                int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
                if (dist <= pivot) {
                    ++i;
                    swap(points, i, j);
                }
            }
            ++i;
            swap(points, i, right);
            if (k - 1 < i) {
                random_select(points, left, i - 1, k);
            } else if (k - 1 > i) {
                random_select(points, i + 1, right, k);
            }
        }

        public void swap(int[][] points, int index1, int index2) {
            int[] temp = points[index1];
            points[index1] = points[index2];
            points[index2] = temp;
        }
    }

    // ORRRRRRR
    /*
     * Runtime: 7 ms, faster than 95.19% of Java online submissions for K Closest
     * Points to Origin.
     * Memory Usage: 117.8 MB, less than 46.66% of Java online submissions for K
     * Closest Points to Origin.
     */
    class KClosestPointsToOriginMakeyMoreSensey {
        public int[][] kClosest(int[][] points, int k) {
            return quickSelect(points, k);
        }

        private int[][] quickSelect(int[][] points, int k) {
            int left = 0, right = points.length - 1;
            int pivotIndex = points.length;
            while (pivotIndex != k) {
                // Repeatedly partition the array
                // while narrowing in on the kth element
                pivotIndex = partition(points, left, right);
                if (pivotIndex < k) {
                    left = pivotIndex;
                } else {
                    right = pivotIndex - 1;
                }
            }

            // Return the first k elements of the partially sorted array
            return Arrays.copyOf(points, k);
        }

        private int partition(int[][] points, int left, int right) {
            int[] pivot = choosePivot(points, left, right);
            int pivotDist = squaredDistance(pivot);
            while (left < right) {
                // Iterate through the range and swap elements to make sure
                // that all points closer than the pivot are to the left
                if (squaredDistance(points[left]) >= pivotDist) {
                    int[] temp = points[left];
                    points[left] = points[right];
                    points[right] = temp;
                    right--;
                } else {
                    left++;
                }
            }

            // Ensure the left pointer is just past the end of
            // the left range then return it as the new pivotIndex
            if (squaredDistance(points[left]) < pivotDist)
                left++;
            return left;
        }

        private int[] choosePivot(int[][] points, int left, int right) {
            // Choose a pivot element of the array
            return points[left + (right - left) / 2];
        }

        private int squaredDistance(int[] point) {
            // Calculate and return the squared Euclidean distance
            return point[0] * point[0] + point[1] * point[1];
        }
    }

    // ORRRRR
    // supposedly beat 99% in memory
    class KClosestPointsToOriginLowMemoryDistribution {
        public int[][] kClosest(int[][] points, int k) {
            int[][] result = new int[k][2];
            PriorityQueue<PointInfo> q = new PriorityQueue<>(k, (p1, p2) -> {
                return p2.distanceFromOrigin - p1.distanceFromOrigin;
            });

            for (int[] point : points) {
                int distanceFromOrigin = (int) Math.pow(point[0], 2) + (int) Math.pow(point[1], 2);
                q.add(new PointInfo(point, distanceFromOrigin));
                if (q.size() > k) {
                    q.poll();
                }
            }

            int resultIdx = 0;
            while (!q.isEmpty()) {
                result[resultIdx++] = q.poll().point;
            }

            return result;

        }

        class PointInfo {
            int[] point;
            int distanceFromOrigin;

            public PointInfo(int[] point, int distanceFromOrigin) {
                this.point = point;
                this.distanceFromOrigin = distanceFromOrigin;
            }
        }
    }

    // ORRRRR
    class KClosestPointsToOriginQuickerSlicker {
        public int[][] kClosest(int[][] points, int k) {
            if (points.length == 0)
                return null;

            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return (distanceSquared(b) - distanceSquared(a));
                }
            });

            for (int i = 0; i < Math.min(points.length, k); i++) {
                pq.add(points[i]);
            }

            for (int i = k; i < points.length; i++) {
                if (distanceSquared(points[i]) < distanceSquared(pq.peek())) {
                    pq.add(points[i]);
                }

                if (pq.size() > k)
                    pq.poll();
            }

            int[][] ans = new int[k][];

            int i = 0;
            while (!pq.isEmpty()) {
                ans[i++] = pq.poll();
            }

            return ans;
        }

        public int distanceSquared(int[] coor) {
            return coor[0] * coor[0] + coor[1] * coor[1];
        }

    }

    /****************************************************** */
    class LongesetSubstringWithoutRepeating {
        public int lengthOfLongestSubstring(String s) {
            int i = 0, j = 0, max = 0;
            Set<Character> set = new HashSet<>();

            while (j < s.length()) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j++));
                    max = Math.max(max, set.size());
                } else {
                    set.remove(s.charAt(i++));
                }
            }

            return max;
        }
    }

    // ORRRR
    class LongestSubStringWithoutRepeatingLowRuntime {
        public int lengthOfLongestSubstring(String s) {
            int start = 0, ans = 0;
            int[] map = new int[255];
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 0;
                start = Math.max(start, map[c]);
                ans = Math.max(ans, i + 1 - start);
                // System.out.println(start + " " + ans);
                map[c] = i + 1;
            }
            return ans;
        }
    }

    /****************************************************** */
    class ThreeSumMostlyMine {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            if (nums == null || nums.length <= 2 || nums[0] > 0)
                return new ArrayList<List<Integer>>();
            List<List<Integer>> out = new ArrayList<List<Integer>>();

            for (int i = 0, len = nums.length; i < len; i++) {
                int num = nums[i];
                // if the number is less than twice the highest OR greater than half the
                // minimum, it follows then the number cannot contribute to threeSum
                if ( //
                (i < len - 1 && (Math.abs(num) > 2 * Math.abs(nums[len - 1]))) ||
                        (i > 0 && (Math.abs(num) > 2 * Math.abs(nums[0]))) ||
                        (i > 0 && num == nums[i - 1])) {
                    // System.out.println("ruling out: <"+num+">");
                    continue;
                }
                // if num=x, then we are looking for two numbers to sum to its complement
                int j = i + 1, k = len - 1, complement = -nums[i];
                // don't let j be less than k, otherwise we hit redundant cases
                while (j < k) {
                    if (nums[j] + nums[k] == complement) {
                        // if int[]:
                        // out.add(new int[] {num, nums[j], nums[k]});

                        out.add(Arrays.asList(num, nums[j], nums[k]));
                        j++;
                        k--;

                        // skip same result with both
                        while (j < k && nums[j] == nums[j - 1])
                            j++;
                        while (j < k && nums[k] == nums[k + 1])
                            k--;
                    } else if (nums[j] + nums[k] > complement) {
                        // greater than what we need, get lower
                        k--;
                        // skip same result
                        while (j < k && nums[k] == nums[k + 1])
                            k--;
                    } else {
                        // only option is we are too low, get higher
                        j++;
                        // skip same result
                        while (j < k && nums[j] == nums[j - 1])
                            j++;
                    }
                }
                // System.out.println("nums: <"+Arrays.toString(nums)+">, num[i]: <"+num+">");
            }
            return out;
        }
    }

    /****************************************************** */
    class ThreeSumLowRuntimeOVERMYHEAD {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new LinkedList<>();
            // no possible threeSum
            if (nums.length < 3)
                return result;
            int nCount = 0, pCount = 0, zCount = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            // brackets (even for single operation conditionals) are faster, and much faster
            // than ternaries
            for (int num : nums) {
                if (num < 0) {
                    // get count of negatives
                    nCount++;
                } else if (num > 0) {
                    // get count of positives
                    pCount++;
                } else {
                    // get count of zeroes
                    zCount++;
                }
                if (num < min) {
                    min = num;
                }
                if (num > max) {
                    max = num;
                }
            }
            if (zCount > 2) {
                // only one can happen with all three zero, so get that one
                result.add(Arrays.asList(0, 0, 0));
            }
            if (nCount == 0 || pCount == 0) {
                return result;
            }
            if (min < -2 * max) {
                min = -2 * max;
            }
            if (max > -2 * min) {
                max = -2 * min;
            }
            // max allocation for int[]s
            int[] negative = new int[nCount], positive = new int[pCount], count = new int[max - min + 1];
            nCount = pCount = 0;
            // second iteration makes O(2N), still less than O(N*N)
            for (int num : nums) {
                if (num < min || num > max) {
                    // can't contribute to threeSum
                    continue;
                }
                // place complement to num and min in indexed position relative to its sign
                if (count[num - min]++ == 0) { // add entry to count here
                    if (num < 0) {
                        negative[nCount++] = num;
                    } else if (num > 0) {
                        positive[pCount++] = num;
                    }
                }
            }
            // can sort now, these are lower count than N, no more than N/2 each
            Arrays.sort(negative, 0, nCount--);
            Arrays.sort(positive, 0, pCount--);
            int left, right, remain, from, to, lCount, rCount;
            for (int pStart = 0; nCount >= 0 && pCount >= 0; nCount--) {
                left = negative[nCount];
                from = Arrays.binarySearch(positive, pStart, pCount, -left / 2);
                to = Arrays.binarySearch(positive, pStart, pCount, -left * 2);
                if (from < 0) {
                    from = Math.max(0, -from - 1);
                }
                if (to < 0) {
                    to = Math.min(pCount, -to - 1);
                }
                pStart = from;
                for (int k = to; k >= from; k--) {
                    right = positive[k];
                    remain = -left - right;
                    if (remain > right) {
                        break;
                    }
                    if (remain >= left && count[remain - min] > 0) {
                        lCount = count[left - min];
                        rCount = count[right - min];
                        if ((remain != left || lCount > 1) && (remain != right || rCount > 1)) {
                            result.add(Arrays.asList(left, remain, right));
                        }
                    }
                }
            }
            return result;
        }
    }

    /****************************************************** */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    /*
     * Runtime: 2 ms, faster than 34.11% of Java online submissions for Binary Tree
     * Level Order Traversal.
     * Memory Usage: 43.4 MB, less than 60.57% of Java online submissions for Binary
     * Tree Level Order Traversal.
     */
    class BinaryTreeLevelOrderTraversalMineYAYYY {
        List<List<Integer>> list = new ArrayList<List<Integer>>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null)
                return list;
            // double bracket notation
            // ArrayList<String> list = new ArrayList<List<Integer>() {{
            // add(Arrays.asList(root.val));
            // }};

            if (root.left == null && root.right == null) {
                return new ArrayList<List<Integer>>() {
                    {
                        add(Arrays.asList(root.val));
                    }
                };
            }
            list.add(new ArrayList<Integer>() {
                {
                    add(root.val);
                }
            });
            addTreeNodeToList(root.left, 1);
            addTreeNodeToList(root.right, 1);

            return list;
        }

        private void addTreeNodeToList(TreeNode root, int depth) {
            if (root == null) {
                return;
            }
            // System.out.println("list: <"+list+">, length: <"+list.size()+"> depth:
            // <"+depth+">");

            List<Integer> currentDepthList = new ArrayList<Integer>();

            if (depth == list.size()) {
                // only get here in next iteration, vis a vi no fourth level without a third
                list.add(new ArrayList<Integer>() {
                    {
                        add(root.val);
                    }
                });
                depth++;
            } else {
                currentDepthList = list.get(depth);
                currentDepthList.add(root.val);
                list.set(depth++, currentDepthList);
            }
            // System.out.println("add: list.size(): <"+list.size()+">, depth: <"+depth+">,
            // list: <"+list+">");

            addTreeNodeToList(root.left, depth);
            addTreeNodeToList(root.right, depth);
        }

    }

    // OOORRRRRRR
    // 0 ms
    class BinaryTreeLevelOrderTraversalLowTimeDuration {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();

            if (root == null)
                return res;

            queue.add(root);
            while (!queue.isEmpty()) {
                int len = queue.size();
                List<Integer> level = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    TreeNode curr = queue.poll();
                    level.add(curr.val);
                    if (curr.left != null) {
                        queue.add(curr.left);
                    }
                    if (curr.right != null) {
                        queue.add(curr.right);
                    }
                }
                res.add(level);
                System.out.println("res: <" + res + ">");
                /*
                 * for [3,9,20,null,null,15,7]
                 * res: <[[3]]>
                 * res: <[[3], [9, 20]]>
                 * res: <[[3], [9, 20], [15, 7]]>
                 */
            }
            return res;
        }

        // public List<List<Integer>> levelOrder(TreeNode root) {
        // Queue<TreeNode> unvisited = new LinkedList<>();
        // List<List<Integer>> res = new ArrayList<>();
        // if(root == null) return res;
        // unvisited.add(root);
        // while(!unvisited.isEmpty()){

        // List<Integer> level = new ArrayList<>();

        // for (int i = 0; i < unvisited.size(); i++) {
        // TreeNode temp = unvisited.poll();
        // level.add(temp.val);
        // if (temp.left != null) {
        // unvisited.add(temp.left);
        // }
        // if (temp.right != null) {
        // unvisited.add(temp.right);
        // }

        // }
        // res.add(level);
        // }
        // return res;
        // }
    }

    /****************************************************** */
    /*
     * // Definition for a Node.
     * class Node {
     * public int val;
     * public List<Node> neighbors;
     * public Node() {
     * val = 0;
     * neighbors = new ArrayList<Node>();
     * }
     * public Node(int _val) {
     * val = _val;
     * neighbors = new ArrayList<Node>();
     * }
     * public Node(int _val, ArrayList<Node> _neighbors) {
     * val = _val;
     * neighbors = _neighbors;
     * }
     * }
     */

    class CloneNodeMineish {
        public HashMap<Node, Node> map = new HashMap<>();

        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }
            if (map.get(node) != null) {
                return map.get(node);
            }
            Node clone = new Node(node.val);
            for (Node neighbor : node.neighbors) {
                clone.neighbors.add(cloneGraph(neighbor));
            }

            return clone;
        }
    }

    // ORRRRR
    class CloneNodeQueue {
        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }
            HashMap<Node, Node> nodeMap = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(node);
            nodeMap.put(node, new Node(node.val));
            while (!queue.isEmpty()) {
                Node polledNode = queue.poll();
                for (Node neighbor : polledNode.neighbors) {
                    if (!nodeMap.containsKey(neighbor)) {
                        queue.offer(neighbor);
                        nodeMap.put(neighbor, new Node(neighbor.val));
                    }
                    nodeMap.get(polledNode).neighbors.add(nodeMap.get(neighbor));
                }
            }
            return nodeMap.get(node);
        }
    }

    /****************************************************** */
    // this one
    class ReversePolishNotationWithStack {
        public int evalRPN(String[] a) {
            Stack<Integer> stack = new Stack<Integer>();

            for (int i = 0; i < a.length; i++) {
                switch (a[i]) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;

                    case "-":
                        stack.push(-stack.pop() + stack.pop());
                        break;

                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;

                    case "/":
                        int n1 = stack.pop(), n2 = stack.pop();
                        stack.push(n2 / n1);
                        break;

                    default:
                        stack.push(Integer.parseInt(a[i]));
                }
            }

            return stack.pop();
        }
    }

    // ORRRRRRR
    /*
     * Runtime: 2 ms, faster than 99.99% of Java online submissions for Evaluate
     * Reverse Polish Notation.
     * Memory Usage: 42.2 MB, less than 86.79% of Java online submissions for
     * Evaluate Reverse Polish Notation.
     */
    /*
     * time complexity:
     * O(n), where n is the length of the array tokens. It is necessary to traverse
     * the array tokens once to calculate the value of the inverse Polish
     * expression.
     * Space complexity:
     * O(n), where n is the length of the array tokens. Use the stack to store the
     * numbers in the calculation process, and the number of elements in the stack
     * will not exceed the length of the reverse Polish expression
     * 
     */
    class EvalRPN {

        public int evalRPN(String[] tokens) {
            int len = tokens.length;
            // makeshift stack, limit length to half token length (each operand takes two
            // numbers (at least) and turns into one)
            int[] arr = new int[(len + 1) / 2];
            int index = -1;
            for (int i = 0; i < len; i++) {
                String token = tokens[i];
                switch (token) {
                    case "+":
                        index--;
                        arr[index] += arr[index + 1];
                        // System.out.println("arr (+): <"+Arrays.toString(arr)+">");
                        break;
                    case "-":
                        index--;
                        arr[index] -= arr[index + 1];
                        // System.out.println("arr (-): <"+Arrays.toString(arr)+">");
                        break;
                    case "*":
                        index--;
                        arr[index] *= arr[index + 1];
                        // System.out.println("arr (*): <"+Arrays.toString(arr)+">");
                        break;
                    case "/":
                        index--;
                        arr[index] /= arr[index + 1];
                        // System.out.println("arr (/): <"+Arrays.toString(arr)+">");
                        break;
                    default:
                        // by rule, the only case left is that of a digit. increment index, add new
                        // operand, and move on
                        index++;
                        arr[index] = Integer.parseInt(token);
                        // System.out.println("arr (d): <"+Arrays.toString(arr)+">");
                }
            }

            // System.out.println("stack (f): <"+Arrays.toString(stack)+">");
            return arr[index];
        }
    }

    /****************************************************** */
    /*
     * Runtime: 2 ms, faster than 100.00% of Java online submissions for Course
     * Schedule.
     * Memory Usage: 42.6 MB, less than 93.54% of Java online submissions for Course
     * Schedule.
     */
    class CourseScheduleLikeMyMindButDontUnderstandYet {
        public boolean canFinish(int numCourses, int[][] prerequisites) {

            LinkedList<Integer>[] graph = new LinkedList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }
            for (int[] prerequisite : prerequisites) {
                graph[prerequisite[1]].add(prerequisite[0]);
            }

            int degree[] = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (hasCycle(graph, i, degree, -1)) {
                    return false;
                }
            }

            return true;
        }

        boolean hasCycle(LinkedList<Integer>[] graph, int index, int[] degree, int parent) {
            degree[index] = 1;

            Iterator<Integer> it = graph[index].iterator();
            while (it.hasNext()) {
                int newIndex = it.next();

                if (degree[newIndex] == 1) {
                    return true;
                }

                if (degree[newIndex] == 0) {
                    if (hasCycle(graph, newIndex, degree, index)) {
                        return true;
                    }
                }

            }

            degree[index] = 2;

            return false;
        }
    }

    // ORRRRRRR
    public class CourseScheduleQueueSituation {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            /*
             * System.out.println("prerequisites: <" + Arrays.deepToString(prerequisites) +
             * ">");
             */
            ArrayList[] graph = new ArrayList[numCourses];
            int[] degree = new int[numCourses];
            Queue queue = new LinkedList();
            int count = 0;

            for (int i = 0; i < numCourses; i++) {
                graph[i] = new ArrayList();
            }
            // System.out.println("graph: <" + Arrays.deepToString(graph) + ">");
            // System.out.println("--------------------------------------------------------------");

            for (int i = 0; i < prerequisites.length; i++) {

                // System.out.println("1. i: <"+i+">, degree: <"+Arrays.toString(degree) +">,
                // graph: <"+Arrays.toString(graph)+">, degree[prerequisites[i][1]]:
                // <"+(degree[prerequisites[i][1]])+">");
                // logically the same as degree[prerequisites[i][1]] = 1, which is just a
                // marker, and will be decremented to 0 (marked as complete when analyzed,
                // essentially) later.
                degree[prerequisites[i][1]]++;
                // System.out.println("2. i: <" + i + ">, degree: <" + Arrays.toString(degree)
                // + ">, graph: <" + Arrays.toString(graph) + ">, degree[prerequisites[i][1]]:
                // <" + degree[prerequisites[i][1]] + ">");

                graph[prerequisites[i][0]].add(prerequisites[i][1]);

                // System.out.println("3. i: <" + i + ">, degree: <" + Arrays.toString(degree)
                // + ">, graph: <" + Arrays.toString(graph) + ">, degree[prerequisites[i][1]]:
                // <" + degree[prerequisites[i][1]] + ">");

                // System.out.println("==============================================================");

            }

            // System.out.println("--------------------------------------------------------------");

            for (int i = 0; i < degree.length; i++) {
                System.out.println("degree[i]: <" + degree[i] + ">");
                if (degree[i] == 0) {
                    queue.add(i);
                    count++;
                }
                /* System.out.println("queue: <"+queue+">"); */
            }

            // System.out.println("--------------------------------------------------------------");

            while (queue.size() > 0) {
                int course = (int) queue.poll();
                for (int i = 0, len = graph[course].size(); i < len; i++) {
                    int pointer = (int) graph[course].get(i);
                    // System.out.println("pointer: <"+pointer+">, degree: <"+Arrays.toString(degree
                    // )+">");

                    degree[pointer]--;
                    /* System.out.println("degree: <"+Arrays.toString(degree)+">"); */
                    if (degree[pointer] == 0) {
                        queue.add(pointer);
                        count++;
                        /* System.out.println("queue: <"+queue+">"); */
                    }
                }
            }
            return count == numCourses;
        }
    }

    /*
     * output:
     * numCourses: 5
     * prerequisites: <[[1, 0], [2, 3], [1, 4]]>
     * graph: <[[], [], [], [], []]>
     * --------------------------------------------------------------
     * 1. i: <0>, degree: <[0, 0, 0, 0, 0]>, graph: <[[], [], [], [], []]>,
     * degree[prerequisites[i][1]]: <0>
     * 2. i: <0>, degree: <[1, 0, 0, 0, 0]>, graph: <[[], [], [], [], []]>,
     * degree[prerequisites[i][1]]: <1>
     * 3. i: <0>, degree: <[1, 0, 0, 0, 0]>, graph: <[[], [0], [], [], []]>,
     * degree[prerequisites[i][1]]: <1>
     * ==============================================================
     * 1. i: <1>, degree: <[1, 0, 0, 0, 0]>, graph: <[[], [0], [], [], []]>,
     * degree[prerequisites[i][1]]: <0>
     * 2. i: <1>, degree: <[1, 0, 0, 1, 0]>, graph: <[[], [0], [], [], []]>,
     * degree[prerequisites[i][1]]: <1>
     * 3. i: <1>, degree: <[1, 0, 0, 1, 0]>, graph: <[[], [0], [3], [], []]>,
     * degree[prerequisites[i][1]]: <1>
     * ==============================================================
     * 1. i: <2>, degree: <[1, 0, 0, 1, 0]>, graph: <[[], [0], [3], [], []]>,
     * degree[prerequisites[i][1]]: <0>
     * 2. i: <2>, degree: <[1, 0, 0, 1, 1]>, graph: <[[], [0], [3], [], []]>,
     * degree[prerequisites[i][1]]: <1>
     * 3. i: <2>, degree: <[1, 0, 0, 1, 1]>, graph: <[[], [0, 4], [3], [], []]>,
     * degree[prerequisites[i][1]]: <1>
     * ==============================================================
     * --------------------------------------------------------------
     * degree[i]: <1>
     * queue: <[]>
     * degree[i]: <0>
     * queue: <[1]>
     * degree[i]: <0>
     * queue: <[1, 2]>
     * degree[i]: <1>
     * queue: <[1, 2]>
     * degree[i]: <1>
     * queue: <[1, 2]>
     * --------------------------------------------------------------
     * pointer: <0>, degree: <[1, 0, 0, 1, 1]>
     * degree: <[0, 0, 0, 1, 1]>
     * queue: <[2, 0]>
     * pointer: <4>, degree: <[0, 0, 0, 1, 1]>
     * degree: <[0, 0, 0, 1, 0]>
     * queue: <[2, 0, 4]>
     * pointer: <3>, degree: <[0, 0, 0, 1, 0]>
     * degree: <[0, 0, 0, 0, 0]>
     * queue: <[0, 4, 3]>
     */
    /****************************************************** */
    /*
     * Not the best, but it's all mine!
     * More or less the array of arrays based on characters matching as the depth
     * continues. Not easy for me to grasp. Like CS50's Harry Potter names.
     * Runtime: 1256 ms, faster than 5.06% of Java online submissions for Implement
     * Trie (Prefix Tree).
     * Memory Usage: 150.5 MB, less than 5.00% of Java online submissions for
     * Implement Trie (Prefix Tree).
     */
    class Trie {

        List<String> trie;

        public Trie() {
            this.trie = new ArrayList<String>();
        }

        public void insert(String word) {
            this.trie.add(word);
        }

        public boolean search(String word) {
            return this.trie.stream().anyMatch(word::equals);
        }

        public boolean startsWith(String prefix) {
            // System.out.println("prefix: <"+prefix+">, trie: <"+this.trie+">");
            List<String> filtered = this.trie
                    .stream()
                    .filter(word -> word.substring(0, Math.min(prefix.length(), word.length())).equals(prefix))
                    .collect(Collectors.toList());
            // System.out.println("filtered: <"+filtered+">");
            return filtered.size() > 0;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    // ORRRRRR
    /*
     * Example of CS50 approach, children being list of subsequent characters
     * progressing down the tree (trie (pronounced try)), again, breaking the String
     * into its individual chars.
     */
    class Trie2 {
        private Character character;
        private boolean ends;
        private Trie2[] children = new Trie2[26];

        public Trie2() {
        }

        public Trie2(Character character) {
            this.character = character;
        }

        public void insert(String word) {
            insert(word, -1);
        }

        public boolean search(String word) {
            return search(word, -1);
        }

        public boolean startsWith(String prefix) {
            return startsWith(prefix, -1);
        }
        /* break into privately controlled functions */

        private void insert(String word, int index) {
            if (index == word.length() - 1) {
                ends = true;
                return;
            }
            if (index + 1 < word.length()) {
                Character nextChar = word.charAt(index + 1);
                int ind = nextChar - 'a';
                Trie2 nextTrieNode = children[ind];
                if (nextTrieNode == null) {
                    nextTrieNode = new Trie2(nextChar);
                    children[ind] = nextTrieNode;
                }
                nextTrieNode.insert(word, index + 1);
            }
        }

        private boolean search(String word, int index) {
            if (index == word.length() - 1) {
                return ends;
            }
            if (index + 1 < word.length()) {
                Character nextChar = word.charAt(index + 1);
                int ind = nextChar - 'a';
                Trie2 nextTrieNode = children[ind];
                if (nextTrieNode != null) {
                    return nextTrieNode.search(word, index + 1);
                }
            }
            return false;
        }

        private boolean startsWith(String prefix, int index) {
            if (index + 1 < prefix.length()) {
                Character nextChar = prefix.charAt(index + 1);
                int ind = nextChar - 'a';
                Trie2 nextTrieNode = children[ind];
                if (nextTrieNode != null) {
                    return nextTrieNode.startsWith(prefix, index + 1);
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    /****************************************************** */
    public class CoinChangeChallenge {
        public int coinChange(int[] coins, int amount) {
            if (amount < 1)
                return 0;
            int[] dp = new int[amount + 1];
            int sum = 0;

            while (++sum <= amount) {
                // i++ returns the val, then increments it by one, while ++i increments the val
                // by one and returns the new val
                // e.g. int i=0, int[] = new int[2], arr[i++]=1 would have arr = [1,0], while
                // arr[++i]=2 would have arr=[0,1] and both would have the next iteration of
                // i=1;
                int min = -1;
                for (int coin : coins) {
                    if (sum >= coin && dp[sum - coin] != -1) {
                        int temp = dp[sum - coin] + 1;
                        min = min < 0 ? temp : (temp < min ? temp : min);
                    }
                }
                dp[sum] = min;
                System.out.println("dp: <" + Arrays.toString(dp) + ">");
            }
            System.out.println("----------------------------");
            System.out.println("dp: <" + Arrays.toString(dp) + ">");
            return dp[amount];
        }
    }

    // ORRRRRRRR
    class CoinChangeDeriver {
        Integer[] dp;

        public int coinChange(int[] coins, int amount) {
            if (amount == 0)
                return 0;
            Arrays.sort(coins);
            dp = new Integer[amount + 1];
            for (int i = 1; i < coins[0] && i <= amount; i++)
                dp[i] = -1;

            for (int i : coins)
                if (i <= amount)
                    dp[i] = 1;
            dp[0] = 0;
            return helper(coins, amount);
        }

        public int helper(int[] coins, int amount) {
            if (dp[amount] != null) {
                return dp[amount];
            }
            int min = Integer.MAX_VALUE;
            for (int i = coins.length - 1; i >= 0; i--) {
                if ((long) (min) * (long) coins[i] <= (long) amount)
                    break;
                if (coins[i] <= amount) {
                    int temp = helper(coins, amount - coins[i]);
                    if (temp != -1) {
                        min = Math.min(min, temp + 1);
                    }
                }
            }
            if (min == Integer.MAX_VALUE)
                min = -1;

            dp[amount] = min;
            return min;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 385 ms, faster than 6.06% of Java online submissions for Product of
     * Array Except Self.
     * Memory Usage: 70.2 MB, less than 6.25% of Java online submissions for Product
     * of Array Except Self.
     */
    class ProductOfArrayExceptSelfMINE {
        private Boolean hasZero = false;
        private Integer zeroIndex;
        private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        public int[] productExceptSelf(int[] nums) {
            int len = nums.length;
            int[] output = new int[nums.length];
            for (int i = 0; i < len; i++) {
                // make map, if more than one zero all products will be zero, stop and return at
                // that point.
                // otherwise, add map entry
                int num = nums[i];
                // System.out.println("num: <"+nums[i]+">");
                if (num == 0) {
                    if (hasZero) {
                        // second zero, all products will be zero
                        return new int[len];
                    }
                    hasZero = true;
                    zeroIndex = i;
                }
                var count = map.get(num);
                if (count == null) {
                    map.put(num, 1);
                } else {
                    map.put(num, ++count);
                }
            }
            // System.out.println("map: <"+map+">");
            if (hasZero) {
                output[zeroIndex] = getProduct(0);
            } else {
                for (int i = 0; i < len; i++) {
                    int self = nums[i];
                    System.out.println("self: <" + self + ">");
                    int numberOfOccurrences = map.get(self);
                    output[i] = getProduct(self);
                }
            }
            // iterate through each, set the output as the product of the rest (if more than
            // one, decrement and take product);

            return output;
        }

        private int getProduct(Integer self) {
            int product = 1;
            for (Integer key : map.keySet()) {
                Integer selfConsideration = key == self ? -1 : 0;
                Integer coefficient = map.get(key) + selfConsideration;
                if (coefficient > 0) {
                    product *= (Math.pow(key, coefficient));
                }
                // System.out.println("self: <"+self+">, key: <"+key+">, selfConsideration:
                // <"+selfConsideration+">, coefficient: <"+coefficient+">, product:
                // <"+product+">");
            }
            return product;
        }
    }

    // ORRRRRR
    /*
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of
     * Array Except Self.
     * Memory Usage: 57.7 MB, less than 53.31% of Java online submissions for
     * Product of Array Except Self.
     */

    class ProductOfArrayExceptSelfOptimized {
        public int[] productExceptSelf(int[] nums) {

            int[] out = new int[nums.length];
            int product = 1;
            int len = nums.length;
            out[0] = product;
            // product of all items to the left of index entry
            for (int i = 1; i < len; i++) {
                out[i] = product * nums[i - 1];
                product = out[i];
            }

            product = 1;

            // product of all items to the right of the index entry multiplied with previous
            for (int i = len - 1; i >= 0; i--) {
                out[i] = product * out[i];
                product = product * nums[i];

            }

            return out;
        }
    }

    /****************************************************** */

    // essentially use linked list. faster than 100%, less memory than 94%
    class MinStack {

        private class Node {
            int val;
            int min;
            Node next;

            private Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }

        private Node n;

        public MinStack() {
        }

        public void push(int val) {
            // !doubles runtime!
            // n = n == null
            // ? new Node(val,val,null)
            // : new Node(val,Math.min(val,n.min),n)
            // ;
            if (n == null)
                n = new Node(val, val, null);
            else
                n = new Node(val, Math.min(val, n.min), n);

        }

        public void pop() {
            n = n.next;
        }

        public int top() {
            return n.val;
        }

        public int getMin() {
            return n.min;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    /****************************************************** */
    /*
     * Runtime: 1 ms, faster than 61.12% of Java online submissions for Validate
     * Binary Search Tree.
     * Memory Usage: 44.2 MB, less than 28.56% of Java online submissions for
     * Validate Binary Search Tree.
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class ValidBinarySearchTreeMineWithSyntaxHelp {

        // consider
        /*
         * .......4
         * ..../....\
         * ...2......6
         * ../ \..../ \
         * .1...3..5...7
         */

        public boolean isValidBST(TreeNode root) {
            return helper(root, null, null);
        }

        private Boolean helper(TreeNode root, Integer min, Integer max) {
            if (root == null)
                return true;

            // if min or max is null, then dealing with only left or right child. Otherwise,
            // needs to be within min and max
            if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
                return false;
            }

            return helper(root.left, min, root.val) && helper(root.right, root.val, max);
        }
    }

    // ORRRRRR

    class ValidBinarySearchTreeFast {
        public boolean isValidBST(TreeNode root) {
            return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean helper(TreeNode root, long min, long max) {
            if (root == null)
                return true;
            boolean isLeftValid = helper(root.left, min, root.val);
            boolean isRightValid = helper(root.right, root.val, max);
            return root.val < max && root.val > min && isLeftValid && isRightValid;
        }
    }

    // OOORRRRRRR

    class ValidBinarySearchTreeTester {
        public boolean isValidBST(TreeNode root) {
            List<Integer> arr = new ArrayList<>();

            helper(root, arr);

            for (int i = 1, size = arr.size(); i < size; i++) {
                if (arr.get(i) <= arr.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }

        public void helper(TreeNode node, List<Integer> arr) {
            if (node == null)
                return;

            helper(node.left, arr);
            arr.add(node.val);
            helper(node.right, arr);
        }
    }

    /****************************************************** */

    public class NumberOfIslandsRecursiveNotMine {
        char[][] g;

        public int numIslands(char[][] grid) {
            int islands = 0;
            g = grid;
            for (int i = 0; i < g.length; i++)
                for (int j = 0; j < g[i].length; j++)
                    islands += sink(i, j);
            return islands;
        }

        int sink(int i, int j) {
            if (i < 0 || i == g.length || j < 0 || j == g[i].length || g[i][j] == '0')
                return 0;
            g[i][j] = '0';
            sink(i + 1, j);
            sink(i - 1, j);
            sink(i, j + 1);
            sink(i, j - 1);
            return 1;
        }
    }

    class NumberOfIslandsRecursiveNotMineII {
        int islands = 0;

        public int numIslands(char[][] grid) {
            // first find the 1
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        islands++;
                        dfs(grid, m, n, i, j);
                    }
                }
            }
            return islands;
        }

        public void dfs(char[][] grid, int m, int n, int i, int j) {
            if (i >= m || j >= n || i < 0 || j < 0) {
                return;
            }
            if (grid[i][j] != '1') {
                return;
            }
            grid[i][j] = '0';
            dfs(grid, m, n, i + 1, j);
            dfs(grid, m, n, i - 1, j);
            dfs(grid, m, n, i, j - 1);
            dfs(grid, m, n, i, j + 1);
        }
    }

    /****************************************************** */
    // taking new approach. going to learn ideas rather than puzzle through it

    class RottenOranges {
        public int orangesRotting(int[][] grid) {
            if (grid == null) {
                return 0;
            }
            int rows = grid.length;
            int cols = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            int freshCount = 0;
            // Put the position of all rotten oranges in queue
            // count the number of fresh oranges
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // find and add rotten oranges to queue;
                    if (grid[i][j] == 2) {
                        queue.offer(new int[] { i, j });

                    } else if (grid[i][j] == 1) {
                        // else if add fresh orange to count (make sure the
                        // count is zero at the end of making oranges rotten
                        // otherwise return -1)
                        freshCount++;
                    }
                }
            }
            // already at required case, return 0;
            if (freshCount == 0) {
                return 0;
            }
            int seconds = 0;
            int[] north = { 0, -1 };
            int[] south = { 0, 1 };
            int[] east = { 1, 0 };
            int[] west = { -1, 0 };
            int[][] directions = { north, south, east, west };
            // work through all rotten oranges (adding newly transitioned
            // fresh ones as needed)
            while (!queue.isEmpty()) {
                // new iteration, increment duration
                seconds++;
                for (int i = 0, size = queue.size(); i < size; i++) {
                    int[] point = queue.poll();
                    for (int[] direction : directions) {
                        int x = point[0] + direction[0];
                        int y = point[1] + direction[1];
                        // we do nothing if x or y is out of bounds, (x , y) is already rotten, or (x ,
                        // y) is empty
                        if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) {
                            continue;
                        }
                        // mark (x , y) as rotten
                        grid[x][y] = 2;
                        // put (x , y) in queue, only case is the fresh orange is now rotten and must be
                        // taken into consideration
                        queue.offer(new int[] { x, y });
                        // decrease the count of fresh oranges by 1
                        freshCount--;
                    }
                }
            }

            return freshCount == 0 ? seconds - 1 : -1;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 1 ms, faster than 74.47% of Java online submissions for Search in
     * Rotated Sorted Array.
     * Memory Usage: 42.8 MB, less than 30.59% of Java online submissions for Search
     * in Rotated Sorted Array.
     */
    class SearchInRotatedSortedArray {
        public int search(int[] nums, int target) {
            // don't have to consider nums == null, constraints guarantee length of no less
            // than one.
            int len = nums.length;
            int low = 0;
            int high = len - 1;

            // find the index of the smallest value using binary search.
            // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
            // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would
            // have been terminated.
            while (low < high) {
                // get mid without overflow;
                int mid = low / 2 + high / 2 + (low % 2 + high % 2) / 2;
                if (nums[mid] > nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            // low == high is the index of the smallest value and also the number of places
            // rotated.
            int rotations = low;
            low = 0;
            high = len - 1;

            // lo==hi is the index of the smallest value and also the number of places
            // rotated.
            while (low <= high) {
                int mid = low / 2 + high / 2 + (low % 2 + high % 2) / 2;
                /*
                 * If we want to find realmid for array [4,5,6,7,0,1,2], you can shift the part
                 * before the rotating point to the end of the array (after 2) so that the
                 * sorted array is "recovered" from the rotating point but only longer, like
                 * this: [4, 5, 6, 7, 0, 1, 2, 4, 5, 6, 7]. The real mid in this longer array is
                 * the middle point between the rotating point and the last element: (rot +
                 * (hi+rot)) / 2. (hi + rot) is the index of the last element. And of course,
                 * this result is larger than the real middle. So you just need to wrap around
                 * and get the remainder: ((rot + (hi + rot)) / 2) % n. And this expression is
                 * effectively (rot + hi/2) % n, which is (rot+mid) % n.
                 */

                // this one is snazzy, but not something I would expect to figure out in an
                // interview.
                int realmid = (mid + rotations) % len;
                if (nums[realmid] == target) {
                    return realmid;
                }
                if (nums[realmid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return -1;
        }
    }

    // ORRRRR

    /*
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in
     * Rotated Sorted Array.
     * Memory Usage: 41.6 MB, less than 95.81% of Java online submissions for Search
     * in Rotated Sorted Array.
     */
    public class SearchInRotatedSortedArray2 {
        private int len;

        public int search(int[] nums, int target) {
            len = nums.length;
            int minIdx = findMinIdx(nums);
            if (target == nums[minIdx]) {
                return minIdx;
            }
            int start = (target <= nums[len - 1]) ? minIdx : 0;
            int end = (target > nums[len - 1]) ? minIdx : len - 1;

            while (start <= end) {
                int mid = getMidpointWithoutOverflow(start, end);
                if (nums[mid] == target) {
                    return mid;
                } else if (target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return -1;
        }

        public int findMinIdx(int[] nums) {
            int start = 0, end = len - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] > nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return start;
        }

        private int getMidpointWithoutOverflow(int a, int b) {
            return a + (b - a) / 2;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 4 ms, faster than 86.16% of Java online submissions for Combination
     * Sum.
     * Memory Usage: 45.3 MB, less than 47.99% of Java online submissions for
     * Combination Sum.
     */
    public class CombinationSum {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            return getSum(new ArrayList<List<Integer>>(), new ArrayList<Integer>(), candidates, target, 0);
        }

        private List<List<Integer>> getSum(List<List<Integer>> combinations, List<Integer> current, int candidates[],
                int target, int index) {
            // constraints for base case and target >= candidates[i] guarantees target >= 0
            if (target > 0) {
                for (int i = index, len = candidates.length; i < len && target >= candidates[i]; i++) {
                    current.add(candidates[i]);
                    // either the sum of all is equal to target, or sum of recursion is less than
                    // and then starting next one
                    getSum(combinations, current, candidates, target - candidates[i], i);
                    // get ready for next one, if the candidate was right then the called getSum
                    // would add to the combinations
                    current.remove(current.size() - 1);
                }
            } else if (target == 0) {
                // we have a valid sum of current candidate ints, add to combinations
                combinations.add(new ArrayList<Integer>(current));
            }

            return combinations;
        }
    }

    // ORRRRRRRR
    /*
     * Runtime: 3 ms, faster than 95.20% of Java online submissions for Combination
     * Sum.
     * Memory Usage: 45.9 MB, less than 24.24% of Java online submissions for
     * Combination Sum.
     */
    class CombinationSum2 {
        private int[] candidates;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            this.candidates = candidates;
            return getCombinations(new ArrayList<List<Integer>>(), new ArrayList<>(), target, 0);
        }

        public List<List<Integer>> getCombinations(List<List<Integer>> combinations,
                ArrayList<Integer> currentCombination, int target, int index) {
            if (index == candidates.length) {
                if (target == 0) {
                    combinations.add(new ArrayList<>(currentCombination));
                }
                return combinations;
            }

            getCombinations(combinations, currentCombination, target, index + 1);
            int candidate = candidates[index];
            if (candidate <= target) {
                currentCombination.add(candidate);
                getCombinations(combinations, currentCombination, target - candidate, index);
                currentCombination.remove(currentCombination.size() - 1);
            }

            return combinations;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 1 ms, faster than 98.16% of Java online submissions for
     * Permutations.
     * Memory Usage: 42.6 MB, less than 87.45% of Java online submissions for
     * Permutations.
     */
    class Permutaions {
        private int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            this.nums = nums;
            return permuteRecur(new ArrayList<List<Integer>>(), new ArrayList<>(), new boolean[nums.length]);
        }

        private List<List<Integer>> permuteRecur(List<List<Integer>> finalResult, List<Integer> currResult,
                boolean[] used) {

            if (currResult.size() == nums.length) {
                finalResult.add(new ArrayList<>(currResult));
                return finalResult;
            }

            for (int i = 0, len = nums.length; i < len; i++) {
                if (used[i]) {
                    continue;
                }
                currResult.add(nums[i]);
                used[i] = true;
                permuteRecur(finalResult, currResult, used);
                used[i] = false;
                currResult.remove(currResult.size() - 1);
            }
            return finalResult;
        }
    }

    // ORRRRRRRRR

    class PermutationsSolutionCloserToMyLogic {
        private int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            this.nums = nums;
            return permute(0, new ArrayList<>());
        }

        public List<List<Integer>> permute(int index, List<List<Integer>> out) {
            int len = nums.length;
            if (index >= len) {
                ArrayList<Integer> arr = new ArrayList<>();
                for (int iteration = 0; iteration < len; iteration++) {
                    arr.add(nums[iteration]);
                }
                out.add(arr);
                return out;
            }
            for (int iteration = index; iteration < len; iteration++) {
                swap(index, iteration);
                permute(index + 1, out);
                swap(index, iteration);
            }
            return out;
        }

        public void swap(int index, int iteration) {
            int temp = nums[index];
            nums[index] = nums[iteration];
            nums[iteration] = temp;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 6 ms, faster than 99.59% of Java online submissions for Merge
     * Intervals.
     * Memory Usage: 56.4 MB, less than 5.56% of Java online submissions for Merge
     * Intervals.
     */
    class MergeIntervals {
        private int len;

        public int[][] merge(int[][] intervals) {
            len = intervals.length;
            if (len == 1) {
                return intervals;
            }

            int[] starts = new int[len];
            int[] ends = new int[len];

            for (int i = 0; i < len; i++) {
                starts[i] = intervals[i][0];
                ends[i] = intervals[i][1];
            }
            // arrange in order (even if there is an intersection, order will not matter)
            Arrays.sort(starts);
            Arrays.sort(ends);

            List<int[]> out = new ArrayList<int[]>();
            for (int i = 0, j = 0; i < len; i++) {
                // if it's the last one, or if the start exceeds the end
                // get to the next end that applies to the current
                if (i == len - 1 || starts[i + 1] > ends[i]) {
                    out.add(new int[] { starts[j], ends[i] });
                    j = i + 1; // increment start to next highest
                }
            }

            return out.toArray(new int[out.size()][2]);
        }
    }

    /****************************************************** */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class LowestCommonAncestorOfBinaryTree {
        TreeNode res = null;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            helper(root, p, q);
            return res;
        }

        private boolean helper(TreeNode root, TreeNode p, TreeNode q) {
            // stop recursion if nulll
            if (root == null)
                return false;
            // boolean logic,
            int curr = root == p || root == q ? 1 : 0;
            int left = helper(root.left, p, q) ? 1 : 0;
            int right = helper(root.right, p, q) ? 1 : 0;
            int sum = right + left + curr;
            if (sum == 2)
                res = root;
            return sum == 1;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 591 ms, faster than 5.07% of Java online submissions for Time Based
     * Key-Value Store.
     * Memory Usage: 269 MB, less than 5.05% of Java online submissions for Time
     * Based Key-Value Store.
     */
    class TimeMapMine {
        private HashMap<String, String> timeMap;

        public void TimeMap() {
            this.timeMap = new HashMap<String, String>();

        }

        public void set(String key, String value, int timestamp) {
            timeMap.put(key + "|" + timestamp, value);
        }

        public String get(String key, int timestamp) {
            var value = timeMap.get(key + "|" + timestamp);
            while (value == null && timestamp > 0) {
                value = timeMap.get(key + "|" + (--timestamp));
            }
            return value == null ? "" : value;
        }
    }

    class LargerSolutionForTimeMap {
        class TimeValue {
            String val;
            int timestamp;

            public TimeValue(String val, int time) {
                this.val = val;
                timestamp = time;
            }

            @Override
            public String toString() {
                return new StringBuilder("{").append("val=").append(val).append(",timestamp=").append(timestamp)
                        .append("}").toString();
            }
        }

        class TimeMap {
            Map<String, List<TimeValue>> map;

            public TimeMap() {
                map = new HashMap<>();
            }

            public void set(String key, String value, int timestamp) {
                System.out.println("map: <" + map + ">");
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimeValue(value, timestamp));
            }

            public String get(String key, int timestamp) {
                List<TimeValue> list = map.getOrDefault(key, null);
                if (list == null) {
                    return "";
                }
                int low = 0, high = list.size() - 1;
                if (list.get(low).timestamp > timestamp) {
                    return "";
                }
                if (list.get(high).timestamp <= timestamp) {
                    return list.get(high).val;
                }
                // binary search for timestamp in ArrayList at key (faster as the size of each
                // entry increases)
                while (low < high) {
                    int mid = (low + high) / 2;
                    if (list.get(mid).timestamp == timestamp) {
                        return list.get(mid).val;
                    }
                    if (list.get(mid).timestamp < timestamp) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }

                }
                return list.get(low - 1).val;

            }
        }

        /**
         * Your TimeMap object will be instantiated and called as such:
         * TimeMap obj = new TimeMap();
         * obj.set(key,value,timestamp);
         * String param_2 = obj.get(key,timestamp);
         */
    }

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */
    /****************************************************** */

    class DisjointSetUnion {
        int representative[];
        int size[];

        DisjointSetUnion(int givenSize) {
            representative = new int[givenSize];
            size = new int[givenSize];

            for (int i = 0; i < givenSize; ++i) {
                // Initially each group is its own representative
                representative[i] = i;
                // Intialize the size of all groups to 1
                size[i] = 1;
            }
        }

        // Finds the representative of group x
        public int findRepresentative(int x) {
            if (x == representative[x]) {
                return x;
            }

            // This is path compression
            return representative[x] = findRepresentative(representative[x]);
        }

        // Unite the group that contains "a" with the group that contains "b"
        public void unionBySize(int a, int b) {
            int repA = findRepresentative(a);
            int repB = findRepresentative(b);

            // If nodes a and b already belong to the same group, do nothing.
            if (repA == repB) {
                return;
            }

            // Union by size: point the representative of the smaller
            // group to the representative of the larger group.
            if (size[repA] >= size[repB]) {
                size[repA] += size[repB];
                representative[repB] = repA;
            } else {
                size[repB] += size[repA];
                representative[repA] = repB;
            }
        }
    }

    class MergeAccounts {
        public List<List<String>> accountsMerge(List<List<String>> accountList) {
            int accountListSize = accountList.size();
            DisjointSetUnion dsu = new DisjointSetUnion(accountListSize);

            // Maps email to their component index
            Map<String, Integer> emailGroup = new HashMap<>();

            for (int i = 0; i < accountListSize; i++) {

                for (int j = 1, accountSize = accountList.get(i).size(); j < accountSize; j++) {
                    String email = accountList.get(i).get(j);
                    String accountName = accountList.get(i).get(0);

                    // If this is the first time seeing this email then
                    // assign component group as the account index
                    if (!emailGroup.containsKey(email)) {
                        emailGroup.put(email, i);
                    } else {
                        // If we have seen this email before then union this
                        // group with the previous group of the email
                        dsu.unionBySize(i, emailGroup.get(email));
                    }
                }
            }

            // Store emails corresponding to the component's representative
            Map<Integer, List<String>> components = new HashMap<Integer, List<String>>();
            for (String email : emailGroup.keySet()) {
                int group = emailGroup.get(email);
                int groupRep = dsu.findRepresentative(group);

                if (!components.containsKey(groupRep)) {
                    components.put(groupRep, new ArrayList<String>());
                }

                components.get(groupRep).add(email);
            }

            // Sort the components and add the account name
            List<List<String>> mergedAccounts = new ArrayList<>();
            for (int group : components.keySet()) {
                List<String> component = components.get(group);
                Collections.sort(component);
                component.add(0, accountList.get(group).get(0));
                mergedAccounts.add(component);
            }

            return mergedAccounts;
        }
    }

    // ORRRRRRRR
    class AccountsMergeFast {
        int[] parents;
        List<String>[] emailLists;

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();

            parents = new int[n];
            Arrays.fill(parents, -1);

            emailLists = new ArrayList[n];

            Map<String, Integer> emailToAccountMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                List<String> accountData = accounts.get(i);

                Set<Integer> associatedAccounts = addAccount(i, accountData, emailToAccountMap);

                for (int accountToMerge : associatedAccounts) {
                    merge(accountToMerge, i);
                }
            }

            List<List<String>> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (parents[i] == i) {
                    List<String> accountData = new ArrayList<>();
                    accountData.add(accounts.get(i).get(0));

                    Collections.sort(emailLists[i]);
                    accountData.addAll(emailLists[i]);

                    result.add(accountData);
                }
            }

            return result;
        }

        private Set<Integer> addAccount(int id, List<String> data,
                Map<String, Integer> emailToAccountMap) {
            Set<Integer> associatedSet = new HashSet<>();

            if (parents[id] != -1) {
                return associatedSet;
            }

            parents[id] = id;

            emailLists[id] = new ArrayList<>();

            for (int i = 1; i < data.size(); i++) {
                String email = data.get(i);

                if (emailToAccountMap.containsKey(email)) {
                    associatedSet.add(emailToAccountMap.get(email));
                } else {
                    emailLists[id].add(email);
                    emailToAccountMap.put(email, id);
                }
            }

            return associatedSet;
        }

        private void merge(int a, int b) {
            int aRoot = findRoot(a);
            int bRoot = findRoot(b);

            if (aRoot != bRoot) {
                int aSize = emailLists[aRoot].size();
                int bSize = emailLists[bRoot].size();

                int largerRoot = aSize >= bSize ? aRoot : bRoot;
                int smallerRoot = largerRoot == aRoot ? bRoot : aRoot;

                parents[smallerRoot] = largerRoot;

                emailLists[largerRoot].addAll(emailLists[smallerRoot]);
                emailLists[smallerRoot] = null;
            }
        }

        private int findRoot(int id) {
            int root = id;

            while (root != parents[root]) {
                root = parents[root];
            }

            while (id != root) {
                int originalParent = parents[id];
                parents[id] = root;
                id = originalParent;
            }

            return root;
        }
    }

    // ORRRRRR
    class MergeAccountsLowMemory {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int[] parents = new int[accounts.size()];
            for (int i = 0; i < parents.length; i++)
                parents[i] = i;

            // emailToParent map which stores the union'd graphs
            // there can be a maximum of (accounts.size()) parents
            Map<String, Integer> emailToParentMap = new HashMap();
            for (int i = 0; i < accounts.size(); i++) {
                for (int j = 1; j < accounts.get(i).size(); j++) {
                    String email = accounts.get(i).get(j);
                    if (emailToParentMap.containsKey(email)) {
                        int parent = emailToParentMap.get(email);
                        int p1 = find(parents, i);
                        int p2 = find(parents, parent);
                        if (p1 == p2)
                            continue;
                        else
                            parents[p1] = p2;// if graphs are not combined then combine
                    } else
                        emailToParentMap.put(email, i);
                }
            }

            // another hashmap to store parent - emails
            HashMap<Integer, Set<String>> parenToEmailsMap = new HashMap();
            for (int i = 0; i < accounts.size(); i++) {
                int parent = find(parents, i);
                List<String> emails = accounts.get(i);// index 0 is username
                if (!parenToEmailsMap.containsKey(parent))
                    parenToEmailsMap.put(parent, new TreeSet());
                parenToEmailsMap.get(parent).addAll(emails.subList(1, emails.size()));// 0th is username to skip it
            }

            // build result
            List<List<String>> result = new ArrayList();
            for (int parent : parenToEmailsMap.keySet()) {
                String username = accounts.get(parent).get(0);// 0th is username
                List<String> emails = new ArrayList(parenToEmailsMap.get(parent));
                emails.add(0, username);
                result.add(emails);
            }

            return result;
        }

        private int find(int[] parents, int i) {
            int original = i;
            while (i != parents[i])
                i = parents[i];
            parents[original] = i;
            return i;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 2 ms, faster than 11.27% of Java online submissions for Sort Colors.
     * Memory Usage: 41.9 MB, less than 81.02% of Java online submissions for Sort
     * Colors.
     */
    class SortColorsMineSSSS {
        public void sortColors(int[] nums) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                int num = nums[i];
                var numCount = map.get(num);
                if (numCount != null) {
                    map.put(num, numCount + 1);
                } else {
                    map.put(num, 1);
                }
            }
            int i = 0;
            for (Integer num : map.keySet()) {
                Integer count = map.get(num);
                // System.out.println("sanity check: <"+num+">, count: <"+count+">");
                while (count > 0) {
                    java.lang.reflect.Array.set(nums, i, num);
                    i++;
                    count--;
                }
            }
        }
    }

    // ORRRRRRRR
    class SortColors {
        public void sortColors(int[] nums) {
            int c0 = 0;
            int c1 = 0;
            int c2 = 0;
            for (int i = 0; i < nums.length; i++) {
                switch (nums[i]) {
                    case 0:
                        c0++;
                        break;
                    case 1:
                        c1++;
                        break;
                    case 2:
                        c2++;
                        break;
                }
            }
            int i = 0;
            while (c0 > 0) {
                nums[i] = 0;
                i++;
                c0--;
            }
            while (c1 > 0) {
                nums[i] = 1;
                i++;
                c1--;
            }
            while (c2 > 0) {
                nums[i] = 2;
                i++;
                c2--;
            }
        }
    }

    /****************************************************** */
    /*
     * Runtime: 3 ms, faster than 92.73% of Java online submissions for Word Break.
     * Memory Usage: 40.3 MB, less than 99.55% of Java online submissions for Word
     * Break.
     */
    class SordBreakDynamicProgramming {
        public boolean wordBreak(String s, List<String> wordDict) {
            Integer len = s.length();
            boolean[] f = new boolean[len + 1];

            f[0] = true;

            // First DP
            for (int i = 1; i <= len; i++) {
                for (String str : wordDict) {
                    Integer strlen = str.length();
                    if (strlen <= i && f[i - strlen] && s.substring(i - strlen, i).equals(str)) {
                        f[i] = true;
                        break;
                    }
                }
            }

            /*
             * //Second DP
             * for(int i=1; i <= len; i++){
             * for(int j=0; j < i; j++){
             * if(f[j] && wordDict.contains(s.substring(j, i))){
             * f[i] = true;
             * break;
             * }
             * }
             * }
             */
            return f[s.length()];
        }
    }

    // ORRRRRRRR
    /*
     * Runtime: 1 ms, faster than 99.87% of Java online submissions for Word Break.
     * 
     * Memory Usage: 42 MB, less than 93.37% of Java online submissions for Word
     * Break.
     */
    class WordBreakFAST {
        public boolean wordBreak(String s, List<String> wordDict) {
            return wordBreak(s, wordDict, 0, new boolean[s.length()]);
        }

        private boolean wordBreak(String s, List<String> wordDict, int startIndex, boolean[] visited) {
            if (s.length() == startIndex) {
                return true;
            }
            if (visited[startIndex]) {
                return false;
            }
            visited[startIndex] = true;
            for (var word : wordDict) {
                if (s.startsWith(word, startIndex) && wordBreak(s, wordDict, startIndex + word.length(), visited)) {
                    return true;
                }
            }
            return false;
        }
        // public boolean wordBreak2(String s, List<String> wordDict) {
        // var wordDictSet = new HashSet<>(wordDict);
        // var segmentationIndices = new int[s.length() + 1];
        // var lastSegmentationIndexArrayIndex = 0;
        // for (var i = 1; i < segmentationIndices.length; ++i) {
        // for (var j = 0; j <= lastSegmentationIndexArrayIndex; ++j) {
        // if (wordDictSet.contains(s.substring(segmentationIndices[j], i))) {
        // segmentationIndices[++lastSegmentationIndexArrayIndex] = i;
        // break;
        // }
        // }
        // }
        // return segmentationIndices[lastSegmentationIndexArrayIndex] == s.length();
        // }
    }

    /****************************************************** */

    public class PartitionEqualSubsetSum {
        public boolean canPartition(int[] nums) {
            // constraints guarantee nums != null && nums.length >= 1
            if (nums.length == 1 || (nums.length == 2 && nums[0] != nums[1])) {
                return false;
            }

            int val = 0;
            for (int num : nums) {
                val += num;
            }
            if ((val & 1) == 1) { // logically equivalent to (val % 2) == 1
                /*
                 * number is odd, no numbers summing to an odd number can partition to zero
                 * proof: assume nums is partitioned by two positive integer arrays, a & b such
                 * that sum(A) = sum(b) and nums = A ∪ B. but sum(A)+sum(B)=sum(nums).
                 * so, because sum(A) = sum(B) and sum(A) + sum(B) = sum(nums), we have 2 *
                 * sum(A) = sum(nums) OR sum(A) = sum(nums) / 2. Same for proof of B. ∵
                 */
                return false;
            }

            val /= 2;
            boolean[] vals = new boolean[val + 1];
            // while there is no case for vals[0], if is referenced for others and were that
            // case here, it would in fact be true.
            vals[0] = true;

            for (int i = 1, len = nums.length; i <= len; i++) {
                for (int j = val, iLen = nums[i - 1]; j >= iLen; j--) {
                    vals[j] = vals[j] || vals[j - nums[i - 1]];
                }
            }

            return vals[val];
        }
    }

    /****************************************************** */
    /*
     * Runtime: 20 ms, faster than 7.26% of Java online submissions for String to
     * Integer (atoi).
     * Memory Usage: 47.1 MB, less than 5.43% of Java online submissions for String
     * to Integer (atoi).
     */
    class MyATOIAttempt {
        public int myAtoi(String s) {
            s = s.trim();
            if (s.length() == 0) {
                return 0;
            }
            char firstChar = s.charAt(0);
            String sign = new String();
            String val = new String();

            if (isInt(firstChar) || firstChar == '+' || firstChar == '-') {
                val += firstChar;
            } else {
                return 0;
            }

            for (int i = 1, len = s.length(); i < len; i++) {
                char c = s.charAt(i);
                if (isInt(c)) {
                    val += c;
                } else {
                    break;
                }
            }

            if (!isNumber(val)) {
                return 0;
            }

            Double dub = Double.parseDouble(val);

            if (dub < Integer.MIN_VALUE) {
                val = Integer.MIN_VALUE + "";
                return Integer.parseInt(val);
            } else if (dub > Integer.MAX_VALUE) {
                val = Integer.MAX_VALUE + "";
                return Integer.parseInt(val);
            }

            return Integer.parseInt(sign + val);

        }

        private Boolean isInt(Character c) {
            try {
                Integer.parseInt(c + "");
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        private Boolean isNumber(String s) {
            try {
                Double.parseDouble(s);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    // ORRRRRRRRR
    /*
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for String to
     * Integer (atoi).
     * Memory Usage: 42 MB, less than 97.71% of Java online submissions for String
     * to Integer (atoi).
     */
    class MyATOIOprimized {
        public int myAtoi(String s) {
            int num = 0;
            boolean isPositive = true, numeric = false;

            for (int i = 0, len = s.length(); i < len; i++) {
                char ch = s.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    int digit = ch - '0';
                    if (num > (Integer.MAX_VALUE - digit) / 10)
                        return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    num = num * 10 + digit;
                    numeric = true;
                } else if (numeric) {
                    break;
                } else if (ch == '-' || ch == '+') {
                    isPositive = ch == '+' ? true : false;
                    numeric = true;
                } else if (ch != ' ') {
                    break;
                }
            }

            if (!isPositive)
                return -num;
            return num;
        }
    }

    /****************************************************** */
    /*
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral
     * Matrix.
     * Memory Usage: 42.3 MB, less than 37.10% of Java online submissions for Spiral
     * Matrix.
     */
    public class SpiralOrderOptimized {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> list = new ArrayList<Integer>();
            if (matrix.length == 0 || matrix[0].length == 0) {
                return list;
            }

            int top = 0;
            int bottom = matrix.length - 1;
            int left = 0;
            int right = matrix[0].length - 1;

            while (true) {
                for (int i = left; i <= right; i++) {
                    list.add(matrix[top][i]);
                }
                top++;
                if (left > right || top > bottom) {
                    break;
                }

                for (int i = top; i <= bottom; i++) {
                    list.add(matrix[i][right]);
                }
                right--;
                if (left > right || top > bottom) {
                    break;
                }

                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
                if (left > right || top > bottom) {
                    break;
                }

                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
                if (left > right || top > bottom) {
                    break;
                }
            }

            return list;
        }

    }
    /****************************************************** */
}