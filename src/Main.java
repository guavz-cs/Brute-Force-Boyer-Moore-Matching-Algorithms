import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<TextString> textStrings = new ArrayList<>();
    public static ArrayList<TextString> noMatches = new ArrayList<>();
    public static ArrayList<TextString> matches = new ArrayList<>();
    public static String curPattern = "";
    public static void main(String[] args){
        System.out.println();
        readStrings();
        System.out.println("----------Brute Force Matching----------");
        for(int i = 0; i <= 3; i++){
            TextString ts = textStrings.get(i);
            int index = BruteForceMatch(ts.getString(), ts.getPattern());
            display(ts, index);
        }
        System.out.println();

        System.out.println("----------Boyer-Moore Matching----------");
        for(int i = 4; i < textStrings.size(); i++){
            TextString ts = textStrings.get(i);
            int index = BoyerMooreMatch(ts.getString(), ts.getPattern());
            display(ts, index);
        }
        System.out.println();
        System.out.println("The number of patterns P that found a matching" +
                " substring in text T is " + matches.size() + ".");
        System.out.println("The number of patterns P that did not find a" +
                " matching substring in text T is " + noMatches.size() + ".");
    }

    public static void display(TextString ts, int index){
        System.out.print(ts.getID() + " --> ");
        if(index == -1){
            System.out.println("There is no substring of " + ts.getString() +
                    " matching " + ts.getPattern() + ".");
            noMatches.add(ts);
        }
        else{
            System.out.println("The starting index of the first substring" +
                    " of " + ts.getString() + " matching " + ts.getPattern() + " is " +
                    index + ".");
            matches.add(ts);
        }
    }

    public static int BruteForceMatch(String text, String pattern){
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int n = textChars.length;
        int m = patternChars.length;
        for(int i = 0; i <= n - m; i++){
            int j = 0;
            while(j < m && textChars[i + j] == patternChars[j]){
                j += 1;
            }
            if(j == m){
                return i;
            }
        }
        return -1;
    }

    public static int BoyerMooreMatch(String text, String pattern){
        curPattern = pattern;
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int n = textChars.length;
        int m = patternChars.length;
        int i = m - 1;
        int j = m - 1;
        do{
            if(patternChars[j] == textChars[i]){
                if(j ==0)
                    return i;
                i -= 1;
                j -= 1;
            }
            else{
                i = i + m - Math.min(j, 1 + last(textChars[i]));
                j = m - 1;
            }
        }while(i <= n - 1);
        return -1;
    }

    public static int last(char c){
        int last = -1;
        for (int i = 0; i < curPattern.length(); i++) {
            if (curPattern.charAt(i) == c) {
                last = i;
            }
        }
        return last;
    }

    public static void readStrings(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("strings.txt"));
            String line = br.readLine();
            while (line != null) {
                String[] details = line.split(",");
                int ID = Integer.parseInt(details[0]);
                String string = details[1];
                String pattern = details[2];
                TextString cur = new TextString(ID, string, pattern);
                textStrings.add(cur);
                line = br.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}