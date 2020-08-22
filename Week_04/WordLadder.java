//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 408 👎 0


package leetcode.editor.cn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {
    public static void main(String[] args) {
        Solution solution = new WordLadder().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int count = Integer.MAX_VALUE;
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // O(n!) - O(n)
            Set<String> visited = new HashSet<>();
            dfs(0, beginWord, endWord, wordList, visited);
            return count == Integer.MAX_VALUE ? 0 : count + 2;
        }

        private void dfs(int minCount, String beginWord, String endWord, List<String> wordList, Set<String> visited) {
            // terminator
            if (diffLetter(beginWord, endWord)) {
                count = Math.min(count, minCount);
                return;
            }
            // process
            for (String s : wordList) {
                if (diffLetter(beginWord, s) && !visited.contains(s)) {
                    visited.add(s);
                    // drill down
                    dfs(minCount + 1, s, endWord, wordList, visited);
                    // restore states
                    visited.remove(s);
                }
            }
        }

        private boolean diffLetter(String beginWord, String endWord) {
            int diff = 0;
            for (int i = 0; i < beginWord.length(); i++) {
                if (beginWord.charAt(i) != endWord.charAt(i)) diff ++;
                if (diff > 1) return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}