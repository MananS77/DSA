package trie;

/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
class TrieNode {
    char val;
    TrieNode[] children = new TrieNode[26];
    boolean isWord;

    TrieNode() {
    }

    TrieNode(char c) {
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

class WordDictionary {
    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode();
        root.val = ' ';
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode(c);
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int index, TrieNode node) {
        if (index == word.length())
            return node.isWord;

        char c = word.charAt(index);

        if (c == '.') {
            for (int i = 0; i < node.children.length; i++)
                if (node.children[i] != null && search(word, index + 1, node.children[i]))
                    return true;
        } else
            return node.children[c - 'a'] != null && search(word, index + 1, node.children[c - 'a']);

        return false;
    }

}

public class AddAndSearchWordWithRegex {
    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        test(obj.search("pad"), false); //returns false
        test(obj.search("bad"), true); //returns true
        test(obj.search(".ad"), true); //returns true
        test(obj.search("b.."), true); //returns true
        test(obj.search("b..."), false); //returns false
    }

    private static void test(boolean actual, boolean expected) {
        if (actual != expected)
            throw new AssertionError(String.format("Expected %b, but actual %b", expected, actual));
    }
}
