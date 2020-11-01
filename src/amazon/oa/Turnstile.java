package amazon.oa;/*
Question - 
    https://leetcode.com/discuss/interview-question/853053/
    https://leetcode.com/discuss/interview-question/699973/Goldman-Sachs-or-OA-or-Turnstile

Solution - 
https://leetcode.com/discuss/interview-question/853053/Amazon-or-OA-2020-or-Turnstile/705719

Test - 
https://aonecode.com/amazon-online-assessment-turnstile

Procedure -
1. Create a map to hold arrival times and respective {customer, direction} at that time
2. Create a Queue {customer, direction} for entry -> entryQueue
3. Create a Queue {customer, direction} for exit -> exitQueue
4. For all the customers (while(customers > 0)), and arrival time starting from 0
    a. Get the {customer, direction} for that arrival time
    b. add the {customer, direction} tuple to the respective queue based on direction
    c. If both the queues are empty, increment i and continue
    d. If both the queues are not empty, check for tursntile direction
        . If it is -1, then it was not used. Poll from exit queue, i++ and continue
        . If it is 1, then last was exit. Poll from exit queue and, i++ continue
        . If it is 0, then last was entry. Poll from entry queue and, i++ continue
    e. Else poll from whichever queue that is not empty, i++ and continue

Variables
    int[] result
    Map<Integer, List<int[]>>
    Queue<int[]> entry
    Queue<int[]> exit
    int i
    int turnstileDirection = 1/0
    int numCustomers--
    int[] current = queue.poll()
    
Time and Space - O(N)   
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Turnstile {

  public static int[] getTimes(int numCustomers, int[] arrTime, int[] direction) {

    int[] result = new int[numCustomers];

    Map<Integer, List<int[]>> map = new HashMap();

    Queue<int[]> entryQ = new LinkedList<int[]>();
    Queue<int[]> exitQ = new LinkedList<int[]>();

    for (int i = 0; i < arrTime.length; i++) {
      map.putIfAbsent(arrTime[i], new ArrayList<>());
      map.get(arrTime[i]).add(new int[]{i, direction[i]});
    }

    int i = 0; // used to determine timeStamp
    int turnstileDirection = -1; // default unused = -1

    while (numCustomers > 0) {

      if (map.containsKey(i)) {
        List<int[]> customers = map.get(i);

        for (int[] customer : customers) {
          if (customer[1] == 1) {
            exitQ.add(customer);
          } else {
            entryQ.add(customer);
          }
        }
      }

      if (entryQ.isEmpty() && exitQ.isEmpty()) {
        turnstileDirection = -1;
        i++;
        continue;
      }

      int[] current;
      if (!entryQ.isEmpty() && !exitQ.isEmpty()) {

        boolean isExit = false;
        if (turnstileDirection == -1 || turnstileDirection == 1) {
          isExit = true;
        }

        current = isExit ? exitQ.poll() : entryQ.poll();
        result[current[0]] = i;
        numCustomers--;
        turnstileDirection = isExit ? 1 : 0;
        i++;
        continue;
      }

      if (!entryQ.isEmpty()) {
        current = entryQ.poll();
        result[current[0]] = i;
        numCustomers--;
        turnstileDirection = 0;
        i++;
        continue;
      }

      if (!exitQ.isEmpty()) {
        current = exitQ.poll();
        result[current[0]] = i;
        numCustomers--;
        turnstileDirection = 1;
        i++;
        continue;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println("************** Test **************");
    int numCustomers1 = 4;
    int[] arrTime1 = {0, 0, 1, 5};
    int[] direction1 = {0, 1, 1, 0};
    System.out.println("Customers = " + numCustomers1);
    System.out.println("Arrivals = " + Arrays.toString(arrTime1));
    System.out.println("Directions = " + Arrays.toString(direction1));
    System.out.println(
        "Turnstile Pass = " + Arrays.toString(getTimes(numCustomers1, arrTime1, direction1)));
    System.out.println("************** Test **************");

    System.out.println();

    System.out.println("************** Test **************");
    int numCustomers2 = 5;
    int[] arrTime2 = {0, 1, 1, 3, 3};
    int[] direction2 = {0, 1, 0, 0, 1};
    System.out.println("Customers = " + numCustomers2);
    System.out.println("Arrivals = " + Arrays.toString(arrTime2));
    System.out.println("Directions = " + Arrays.toString(direction2));
    System.out.println(
        "Turnstile Pass = " + Arrays.toString(getTimes(numCustomers2, arrTime2, direction2)));
    System.out.println("************** Test **************");
  }

}
