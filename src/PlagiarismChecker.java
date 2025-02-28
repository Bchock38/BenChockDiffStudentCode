import java.util.ArrayList;

/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author Benjamin Chock
 */
public class PlagiarismChecker {

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {

        // TODO Complete this function to return the length of the longest shared substring.
        // HELLOOOOOO
        return 0;
    }


    public static int longestSharedSubstring1 (String doc1, String doc2){
        // Create array
        if (doc1.length() > doc2.length()){
            int[] doc1chars = makePaths(doc1,doc2);
            return longestTrend(doc1chars);
        }
        int[] doc2chars = makePaths(doc2, doc1);
        return longestTrend(doc2chars);
    }

    public static int[] makePaths (String doc1, String doc2){
        int[] doc1chars = new int[doc1.length()];
        int counter = 0;
        int loc = 0;
        // save location of last found letter exp. if a is found at index 3 start searhing for next a at index 4
        int[] numVis = new int[8218];
        //save position of doc1 char in doc2
        for (int i = 0; i < doc1.length(); i++) {
            counter = numVis[doc1.charAt(i)];
            while (counter < doc2.length()-1 && doc1.charAt(i) != doc2.charAt(counter)) {
                counter++;


                if (counter >= doc2.length()) {
                    break;
                }
            }
            if (counter < doc2.length() && doc1.charAt(i) != doc2.charAt(counter)) {
                if (numVis[doc1.charAt(i)] >= 0){
                    doc1chars[i] = numVis[doc1.charAt(i)]-1;
                }
                else{
                    doc1chars[i] = -2;
                }
            } else {
                doc1chars[i] = counter;
            }
            numVis[doc1.charAt(i)] = counter+1;
        }
        return doc1chars;
    }
    public static int longestTrend(int[] doc1Chars) {
        // Create Arrays and int for longest trend
        int longest = 0;
        int[] greatestLength = new int[doc1Chars.length];
        ArrayList<Integer>[] adjList = new ArrayList[doc1Chars.length];

        // Add all the paths from each number to that numbers array
        for (int i = 0; i < doc1Chars.length; i++) {
            adjList[i] = new ArrayList<Integer>();
            for (int j = 0; j < i; j++){
                if (doc1Chars[i] > doc1Chars[j] && doc1Chars[j] >= 0){
                    adjList[i].add(j);
                }
            }
        }

        // For all the tempatures check their path and save the longest one
        for (int i = 0; i< doc1Chars.length; i++){
            longest = Math.max(longest,LongestPathTo(i,adjList,greatestLength));
        }

        // Return the longest path
        return longest;
    }

    public static int LongestPathTo(int vertices, ArrayList<Integer>[] adjList, int[] greatestLen){
        int len = 0;
        // If we've visted this path already return the paths longest path
        if (greatestLen[vertices] != 0){
            return greatestLen[vertices];
        }
        // For all the connecting numbers visted their paths and save the longest one
        for (int i = 0; i < adjList[vertices].size(); i++){
            len = Math.max(len, LongestPathTo(adjList[vertices].get(i),adjList, greatestLen));
            greatestLen[vertices] = len + 1;
        }
        // Return the longest path
        return len + 1;

    }

}
