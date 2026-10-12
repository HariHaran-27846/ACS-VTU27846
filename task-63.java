import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[][] graph = new ArrayList[2][n];

        for (int color = 0; color < 2; color++) {
            for (int i = 0; i < n; i++) {
                graph[color][i] = new ArrayList<>();
            }
        }

        for (int[] edge : redEdges) {
            graph[0][edge[0]].add(edge[1]);
        }

        for (int[] edge : blueEdges) {
            graph[1][edge[0]].add(edge[1]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int node = current[0];
                int previousColor = current[1];

                if (answer[node] == -1) {
                    answer[node] = distance;
                }

                int nextColor = 1 - previousColor;

                for (int nextNode : graph[nextColor][node]) {

                    if (!visited[nextNode][nextColor]) {

                        visited[nextNode][nextColor] = true;

                        queue.offer(new int[]{
                            nextNode,
                            nextColor
                        });
                    }
                }
            }

            distance++;
        }

        return answer;
    }
}
