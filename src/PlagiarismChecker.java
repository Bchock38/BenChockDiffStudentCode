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
        // Create Array to hold previous shared substrings
        int[][] sharedStrings = new int[doc1.length()+1][doc2.length()+1];
        // Iterate through both Strings
        for (int i = 0; i < doc1.length(); i++){
            for (int j = 0; j < doc2.length(); j++){
                // If the character is the same take the value from its top left in the array and ass 1
                if (doc1.charAt(i) == doc2.charAt(j)){
                    sharedStrings[i+1][j+1] = sharedStrings[i][j]+1;
                }
                // Otherwise take the largest value between the amount of substrings above and to the left of it
                else {
                    sharedStrings[i+1][j+1] = Math.max(sharedStrings[i][j+1], sharedStrings[i+1][j]);
                }

            }
        }
        // Return the amount of substrings in the bottom right
        return sharedStrings[doc1.length()][doc2.length()];
    }


}
