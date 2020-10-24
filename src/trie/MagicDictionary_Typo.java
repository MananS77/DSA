package trie;

import java.util.Arrays;

/**
 * https://leetcode.com/discuss/interview-question/281470/
 *
 * <p>
 * Design a vocabulary class that allows for a maximum of one typo. It has one method: given a word,
 * it verifies if the word can be found in the vocabulary with at most one character substitution.
 * <p>
 * Example : Vocabulary : ['apple', 'banana', 'orange']
 * <p>
 * atMostOneTypo("banana") -> should return true atMostOneTypo("banena") -> should return true
 * atMostOneTypo("banan") -> should return false atMostOneTypo("banxnn") -> should return false
 */

class Dictionary {

  private static final int R = 26;
  private final TrieNode root = new TrieNode();

  Dictionary(String... words) {
    for (String word : words) {
      add(word);
    }
    System.out.println("Words = " + Arrays.toString(words));
  }

  private void add(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
      int val = c - 'a';
      if (node.next[val] == null) {
        node.next[val] = new TrieNode();
      }
      node = node.next[val];
    }
    node.isWord = true;
  }

  boolean atMostOneTypo(String word) {
    if (word.isEmpty()) {
      return root.isWord;
    }
    return atMostOneTypo(root, word, 0, 0);
  }

  private boolean atMostOneTypo(TrieNode node, String word, int i, int typos) {
    if (typos > 1) {
      return false;
    }
    if (i == word.length()) {
      return node.isWord;
    }

    char letter = word.charAt(i);
    int val = letter - 'a';

    for (int ch = 0; ch < R; ch++) {
      if (node.next[ch] == null) {
        continue;
      }
      if (ch == val) {
        if (atMostOneTypo(node.next[ch], word, i + 1, typos)) {
          return true;
        }
      } else if (atMostOneTypo(node.next[ch], word, i + 1, typos + 1)) {
        return true;
      }
    }

    return false;
  }

  private static class TrieNode {

    TrieNode[] next = new TrieNode[R];
    boolean isWord;
  }
}

public class MagicDictionary_Typo {

  private static Dictionary dict = new Dictionary("apple", "banana", "orange", "");

  public static void main(String[] args) {
    test("apple", true);
    test("apple1", false);
    test("banana", true);
    test("banena", true);
    test("banan", false);
    test("banxnn", false);
    test("tanana", true);
    test("tinana", false);
    test("bpple", true);
    test("", true);
  }

  private static void test(String input, boolean expected) {
    boolean actual = dict.atMostOneTypo(input);
    if (actual == expected) {
      System.out.println(input + "\t: " + actual);
    } else {
      throw new AssertionError(String.format("Expected %b, but actual %b", expected, actual));
    }
  }
}
