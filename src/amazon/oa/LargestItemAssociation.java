/*
Question - https://leetcode.com/discuss/interview-question/782606/
Approach - 
    1. Build graph from input
    2. For each node in the graph.keySet()
        i. Do a DFS to visit its connected neighbours
        ii. Add these to a list and return
        iii. Sort the list
        iv. Add this list of connected components to connectedComponents list
    3. Sort the connectedItems list in descending order of size and lexicographical order if two lists are of same size
    
Time - 
    1. To build graph - O(N) where N = number of PairString items in input
    2. To perform DFS - O(M) where M = number of unique items in input
    2. To sort the output list - O(L Log L) where L = number of connected components
    Total = O(N + M + L Log L) time

Space - 
    1. To hold graph - O(M) where M = number of unique items in input
    2. To hold seen set - O(M) where M = number of unique items in input
    Total = O(M) space
    
*/
public class LargestItemAssociation {
    public static List<String> largestItemAssociation(List<PairString> itemAssociation) {
        Map<String, Set<String>> graph = buildGraph(itemAssociation);
        Set<String> seen = new HashSet();
        List<List<String>> connectedComponents = buildConnectedComponentsList(graph, seen);
        sort(connectedComponents);
        return connectedComponents.get(0);
    }
    
    public static Map<String, Set<String>> buildGraph(List<PairString> itemAssociation) {
        Map<String, Set<String>> graph = new HashMap();
        
        for(PairString pair : itemAssociation) {
            graph.putIfAbsent(pair.first, new HashSet());
            graph.get(pair.first).add(pair.second);
            graph.putIfAbsent(pair.second, new HashSet());
            graph.get(pair.second).add(pair.first);
        }
        
        return graph;
    }
    
    public static List<List<String>> buildConnectedComponentsList(Map<String, Set<String>> graph, Set<String> seen) {
        List<List<String>> connectedComponents = new ArrayList();
        
        for(String node : graph.keySet()) {
            List<String> list = new ArrayList();
            dfs(node, graph, seen, list);
            if(!list.isEmpty()) {
                Collections.sort(list);
                connectedComponents.add(list);
            }
        }
        
        return connectedComponents;
    }
    
    public static void dfs(String node, Map<String, Set<String>> graph, Set<String> seen, List<String> list) {
        if(seen.contains(node)) return;
        seen.add(node);
        list.add(node);
        
        for(String neighbour : graph.get(node))
            dfs(neighbour, graph, seen, list);
    }
    
    public static void sort(List<List<String>> connectedComponents) {
        Collections.sort(connectedComponents, new Comparator<List<String>>() {
           @Override
            public int compare(List<String> l1, List<String> l2) {
                if(l1.size() != l2.size()) return l2.size() - l1.size();
                else {
                    for(int i = 0 ; i < l1.size() ; i++) {
                        if(l1.get(i).equals(l2.get(i))) continue;
                        else return l1.get(i).compareTo(l2.get(i));
                    }
                }
                return 0;
            }
        });
    }

    public static void main(String[] args) {
        List<PairString> itemAssociation1 = new ArrayList(){
            {
                add(new PairString("item1", "item2"));
                add(new PairString("item3", "item4"));
                add(new PairString("item4", "item5"));
            }
        };
        List<PairString> itemAssociation2 = new ArrayList(){
            {
                add(new PairString("item1", "item2"));
                add(new PairString("item3", "item4"));
                add(new PairString("item4", "item5"));
                add(new PairString("item6", "item7"));
                add(new PairString("item6", "item8"));
            }
        };
        List<PairString> itemAssociation3 = new ArrayList(){
            {
                add(new PairString("item1", "item2"));
                add(new PairString("item4", "item5"));
                add(new PairString("item3", "item4"));
                add(new PairString("item1", "item4"));
            }
        };
        
        System.out.println(largestItemAssociation(itemAssociation1)); // Output: [item3, item4, item5]
        System.out.println(largestItemAssociation(itemAssociation2)); // Output: [item3, item4, item5], here we got same size, so have to sort lexicographical.
        System.out.println(largestItemAssociation(itemAssociation3)); // Output: [item1, item2, item3, item4, item5]
    }
}

class PairString {
    String first;
    String second;

    public PairString(String first, String second) {
        this.first = first;
        this.second = second;
    }
}
