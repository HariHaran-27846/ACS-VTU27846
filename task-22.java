import java.util.*;

class Solution {
    public int[] finalPrices(int[] prices) {

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {

            while (!stack.isEmpty()
                    && prices[i] <= prices[stack.peek()]) {

                int index = stack.pop();

                prices[index] -= prices[i];
            }

            stack.push(i);
        }

        return prices;
    }
}
