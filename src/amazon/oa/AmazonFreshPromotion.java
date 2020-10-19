/*
Question - Amazon Fresh Promotion | Secret Fruit List
Link - https://leetcode.com/discuss/interview-question/762546/
Test - https://aonecode.com/amazon-online-assessment-secret-fruits
Similar - https://leetcode.com/problems/is-subsequence/

Use two pointers i and j respectively for an element in two given lists. For a specific code codeList[i], check the subarray with the same length as of codeList[i] starting from j in shoppingCart.

See commented code in the second method.

Time - O(M*N), where M is number of total words in codes and N is number of number of items in the cart

Space - O(1)

*/
public class AmazonFreshPromotion {
    
    public static boolean matchSecretLists(List<List<String>> codeList, List<String> shoppingCart) {
        
        if(codeList == null || codeList.size() == 0) return true;
        
        if(shoppingCart == null || shoppingCart.size() == 0) return false;
        
        int i = 0, j = 0;
        
        while (i < codeList.size() && j + codeList.get(i).size() <= shoppingCart.size()) {
            boolean match = true;
            
            for (int k = 0; k < codeList.get(i).size(); k++) {
                if (!codeList.get(i).get(k).equals("anything") && !codeList.get(i).get(k).equals(shoppingCart.get(j + k))) {
                    match = false;
                    break;
                }
            }
            if (match) {
                j += codeList.get(i).size();
                i++;
            } else
                j++;
        }
        return (i == codeList.size()) ? true: false;
    }
    
    public boolean matchSecretListsCommented(List<List<String>> secretFruitList, List<String> customerPurchasedList) {
        
        /*
        * If <code>secretFruitList</code> is empty, then any combination of fruits in the <code>shoppingCart</code> 
        * is eligible for the offer, hence return <code>true</code>
        */
		if(secretFruitList == null || secretFruitList.size() == 0) return true;
		
		/*
		* If the <code>shoppingCart</code> is empty (and ofcourse the <code>secretFruitList</code> isn't empty),
		* then we can straight-away return <code>false</code>
		*/
		if(customerPurchasedList == null || customerPurchasedList.size() == 0) return false;
		
		int i = 0, j = 0;
		
		/*
		* We define two pointers <code>i</code> and <code>j</code> and search for a sub-array of the size of a specific
		* element in the <code>codeList</code> list (of size <code>codeList.get(i).size()</code>) 
		* starting from the index <code>j</code> in the <code>shoppingCart</code>
		*
		* If the entire sub-array of size <code>codeList.get(i).size()</code> is found in <code>shoppingCart</code>, 
		* then we increment <code>j</code> by the size <code>codeList.get(i).size()</code>, and continue with the search.
		*
		* If at any point in time, the sub-array search fails, i.e., 
		* the element in the <code>codeList.get(i).get(k)</code> isn't "anything", or
		* the element in the <code>codeList.get(i).get(k)</code> is not equal to the element in <code>shoppingCart.get(j + k)</code>,
		* we halt the search, and simply increment <code>j</code> so that the sub-array search continues
		* from the next element in <code>shoppingCart</code>
		*
		* Finally, we return if <code>i</code> has reached <code>codeList.get(i).size()</code>, because if it has,
		* then we were successful in finding the exact order of the fruits & respective groups in the <code>shoppingCart</code>.
		*/
		
		while(i < secretFruitList.size() && j + secretFruitList.get(i).size() <= customerPurchasedList.size()) {
		    boolean match = true;
		    for(int k = 0 ; k < secretFruitList.get(i).size() ; k++) {
		        if(!secretFruitList.get(i).get(k).equals("anything") && !secretFruitList.get(i).get(k).equals(customerPurchasedList.get(j + k))) {
		            match = false;
		            break;
		        }
		        if(match) {
		            j += secretFruitList.get(i).size();
		            i++;
		        } else {
		            j++;
		        }
		    }
		}
		return i == secretFruitList.size() ? true : false;
    }
    
    public static void main(String[] args) {
        List<List<String>> codeList1 = Arrays.asList( Arrays.asList( "apple", "apple" ), Arrays.asList( "banana", "anything", "banana" ) );
        List<String> shoppingCart1 = Arrays.asList("orange", "apple", "apple", "banana", "orange", "banana");
        
        List<List<String>> codeList2 = Arrays.asList( Arrays.asList( "apple", "apple" ), Arrays.asList( "banana", "anything", "banana" ) );
        List<String> shoppingCart2 =  Arrays.asList("banana", "orange", "banana", "apple", "apple");
        
        List<List<String>> codeList3 = Arrays.asList( Arrays.asList( "apple", "apple" ), Arrays.asList( "banana", "anything", "banana" ) );
        List<String> shoppingCart3 =  Arrays.asList("apple", "banana", "apple", "banana", "orange", "banana");
        
        List<List<String>> codeList4 = Arrays.asList( Arrays.asList( "apple", "apple" ), Arrays.asList( "apple", "apple", "banana" ) );
        List<String> shoppingCart4 =  Arrays.asList("apple", "apple", "apple", "banana");
        
        List<List<String>> codeList5 = Arrays.asList( Arrays.asList( "apple", "apple" ), Arrays.asList( "banana", "anything", "banana" ) );
        List<String> shoppingCart5 =  Arrays.asList("orange", "apple", "apple", "banana", "orange", "banana");
        
        List<List<String>> codeList6 = Arrays.asList( Arrays.asList( "apple", "apple" ), Arrays.asList( "banana", "anything", "banana" )  );
        List<String> shoppingCart6 =  Arrays.asList("apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana");
        
        List<List<String>> codeList7= Arrays.asList( Arrays.asList( "anything", "apple" ), Arrays.asList( "banana", "anything", "banana" )  );
        List<String> shoppingCart7 =  Arrays.asList("orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana");
        
        List<List<String>> codeList8 = Arrays.asList( Arrays.asList("apple", "orange"), Arrays.asList("orange", "banana", "orange"));
        List<String> shoppingCart8 =  Arrays.asList("apple", "orange", "banana", "orange", "orange", "banana", "orange", "grape");
        
        List<List<String>> codeList9= Arrays.asList( Arrays.asList( "anything", "anything", "anything", "apple" ), Arrays.asList( "banana", "anything", "banana" )  );
        List<String> shoppingCart9 =  Arrays.asList("orange", "apple", "banana", "orange", "apple", "orange", "orange", "banana", "apple", "banana");
        
        List<List<String>> codeList10 = Arrays.asList( Arrays.asList( "apple", "apple" ));
        List<String> shoppingCart10 =  Arrays.asList( "apple", "apple" );

        // test
        test(codeList1, shoppingCart1, true);
        test(codeList2, shoppingCart2, false);
        test(codeList3, shoppingCart3, false);
        test(codeList4, shoppingCart4, false);
        test(codeList5, shoppingCart5, true);
        test(codeList6, shoppingCart6, true);
        test(codeList7, shoppingCart7, true);
        test(codeList8, shoppingCart8, true);
        test(codeList9, shoppingCart9, true);
        test(codeList10, shoppingCart10, true);
    }
    
     public static void test(List<List<String>> codeList, List<String> shoppingCart, boolean expect) {
        System.out.println(matchSecretLists(codeList, shoppingCart) == expect);
    }
}
