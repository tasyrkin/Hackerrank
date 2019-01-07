import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * Wrong and times outb
 */
public class PasswordCracker2 {

    static boolean solve(char[] attempt, int pos, TrieNode root, TrieNode prevNode, LinkedList<String> results) {
        if (pos >= attempt.length) {
            return true;
        }
        TrieNode currNode = prevNode.next.get(attempt[pos]);
        if (currNode == null) {
            return false;
        }
        boolean result;
        if (currNode.isTerminal) {
            result = solve(attempt, pos + 1, root, root, results);
            if (result) {
                results.add(currNode.fullString);
                return true;
            }
        }
        result = solve(attempt, pos + 1, root, currNode, results);
        if (result) {
            if (currNode.isTerminal) {
                results.add(currNode.fullString);
            }
            return true;
        }
        return false;
    }

    static String passwordCracker(String[] passes, String attempt) {

        if (passes == null || attempt == null) throw new IllegalArgumentException("Missing argument");
        if (passes.length == 0 || attempt.length() == 0) return "WRONG PASSWORD";

        TrieNode root = new TrieNode();
        for (String pass : passes) {
            TrieNode curr = root;
            for (int idx = 0; idx < pass.length(); idx++) {
                boolean isLastChar = idx == pass.length() - 1;
                curr = curr.addNext(pass.charAt(idx), isLastChar, isLastChar ? pass : null);
            }
        }

        LinkedList<String> results = new LinkedList<>();
        boolean crackable = solve(attempt.toCharArray(), 0, root, root, results);
        if (crackable) {
            final StringBuilder passwords = new StringBuilder();
            while (!results.isEmpty()) {
                if (passwords.length() > 0) {
                    passwords.append(" ");
                }
                passwords.append(results.removeLast());
            }
            return passwords.toString();
        }
        return "WRONG PASSWORD";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            String[] pass = new String[n];
            for (int pass_i = 0; pass_i < n; pass_i++) {
                pass[pass_i] = in.next();
            }
            String attempt = in.next();
            String result = passwordCracker(pass, attempt);
            System.out.println(result);
        }
        in.close();
    }

    static class TrieNode {
        final Map<Character, TrieNode> next = new HashMap<>();
        boolean isTerminal;
        String fullString;

        TrieNode() {
            this(false, null);
        }

        TrieNode(boolean isTerminal, String fullString) {
            this.isTerminal = isTerminal;
            this.fullString = fullString;
        }

        TrieNode addNext(Character ch, boolean isTerminal, String fullString) {
            TrieNode nextNode = next.get(ch);
            if (nextNode == null) {
                nextNode = new TrieNode(isTerminal, fullString);
                next.put(ch, nextNode);
            } else {
                if (isTerminal) {
                    nextNode.markTerminal(fullString);
                }
            }
            return nextNode;
        }

        void markTerminal(String fullString) {
            isTerminal = true;
            fullString = fullString;
        }
    }
}
