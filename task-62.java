import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        int[] itemIndegree = new int[n];

        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] groupIndegree = new int[m];

        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {

            for (int before : beforeItems.get(i)) {

                itemGraph.get(before).add(i);
                itemIndegree[i]++;

                if (group[before] != group[i]) {
                    groupGraph.get(group[before]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topoSort(
            itemGraph,
            itemIndegree
        );

        if (itemOrder.size() != n) {
            return new int[0];
        }

        List<Integer> groupOrder = topoSort(
            groupGraph,
            groupIndegree
        );

        if (groupOrder.size() != m) {
            return new int[0];
        }

        List<List<Integer>> itemsInGroup = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            itemsInGroup.add(new ArrayList<>());
        }

        for (int item : itemOrder) {
            itemsInGroup.get(group[item]).add(item);
        }

        int[] answer = new int[n];
        int index = 0;

        for (int g : groupOrder) {
            for (int item : itemsInGroup.get(g)) {
                answer[index++] = item;
            }
        }

        return answer;
    }

    private List<Integer> topoSort(
        List<List<Integer>> graph,
        int[] indegree
    ) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();

            order.add(current);

            for (int next : graph.get(current)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return order;
    }
}
