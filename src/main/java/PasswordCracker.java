import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PasswordCracker {

    static class PasswordNode {
        PasswordNode parent;
        boolean isTerminal;
        Map<Character, PasswordNode> nextNodes;

        PasswordNode(PasswordNode parent, boolean isTerminal) {
            this.parent = parent;
            this.isTerminal = isTerminal;
            this.nextNodes = new HashMap<>();
        }

        //factory method for building the trie
        static PasswordNode build(String[]pass) {
            return null;
        }
    }

    static class AttemptPartNode {
        AttemptPartNode parent = null;
        int startAttemtIdxInc = -1;
        int endAttemtIdxInc = -1;
        List<AttemptPartNode> furtherParts = new LinkedList<>();

        boolean buildAnswer(List<String> previousPasses, char[] originalAttempt) {

            if(endAttemtIdxInc == originalAttempt.length - 1) {
                //previousPasses.add(new String(startAttemtIdxInc, endAttemtIdxInc));
                return true;
            }

            for(AttemptPartNode part : furtherParts) {
                boolean result = part.buildAnswer(previousPasses, originalAttempt);
                if(result) {
                    if(parent != null) {
                        //previousPasses.add(new String(startAttemtIdxInc, endAttemtIdxInc));
                    }
                    return true;
                }
            }
            return false;
        }
    }

    static String passwordCracker(String[] pass, String attempt) {

        PasswordNode rootPasswordNode = PasswordNode.build(pass);
        AttemptPartNode rootAttemptNode = new AttemptPartNode();

        boolean isValidAttempt = false;//search(rootPasswordNode, rootAttemptNode, 0, attempt.toCharArray());

        if(isValidAttempt) {
            List<String> result = new LinkedList<>();
            rootAttemptNode.buildAnswer(result, attempt.toCharArray());
            StringBuilder finalResult = new StringBuilder();
            for(String resultPart : result) {
                if(finalResult.length() > 0) finalResult.append(" ");
                finalResult.append(resultPart);
            }
            return finalResult.toString();
        } else {
            return "WRONG PASSWORD";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            String[] pass = new String[n];
            for(int pass_i = 0; pass_i < n; pass_i++){
                pass[pass_i] = in.next();
            }
            String attempt = in.next();
            String result = passwordCracker(pass, attempt);
            System.out.println(result);
        }
        in.close();
    }
}
