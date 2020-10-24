package amazon.oa;

import java.util.PriorityQueue;


public class FiveStarSellers {

  public static int fiveStarReviews(int[][] productRatings, int ratingsThreshold) {

    int numProducts = productRatings.length;
    double threshold = (double) ratingsThreshold / 100;
    double currentRating = 0.0d;

    PriorityQueue<double[]> queue = new PriorityQueue<>(
        (d1, d2) -> {
          double d1Diff = ((d1[0] + 1) / (d1[1] + 1)) - (d1[0] / d1[1]);
          double d2Diff = ((d2[0] + 1) / (d2[1] + 1)) - (d2[0] / d2[1]);
          return d1Diff >= d2Diff ? -1 : 1;
        }
    );

    for (int[] rating : productRatings) {
      currentRating += (double) rating[0] / (double) rating[1];
      queue.offer(new double[]{rating[0], rating[1]});
    }

    currentRating /= numProducts;

    if (currentRating >= threshold) {
      return 0;
    }

    int count = 0;

    while (!queue.isEmpty() && currentRating < threshold) {
      double[] cur = queue.poll();
      double[] plusOneToCur = {cur[0] + 1, cur[1] + 1};

      currentRating = (currentRating * numProducts)
          - (cur[0] / cur[1])
          + (plusOneToCur[0] / plusOneToCur[1]);

      currentRating /= numProducts;
      queue.offer(plusOneToCur);
      count++;
    }

    return count;
  }

  public static void main(String[] args) {
    int[][] ratings = {
        {4, 4},
        {1, 2},
        {3, 6}
    };
    int threshold = 77;
    int count = fiveStarReviews(ratings, threshold);
    System.out.println(count);
  }
}
