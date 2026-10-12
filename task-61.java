import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];

            graph.get(prerequisiteCourse).add(course);
        }

        int[] state = new int[numCourses];

        int[] order = new int[numCourses];
        int[] index = {numCourses - 1};

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, state, order, index)) {
                return new int[0];
            }
        }

        return order;
    }

    private boolean dfs(
        int course,
        List<List<Integer>> graph,
        int[] state,
        int[] order,
        int[] index
    ) {

        if (state[course] == 1) {
            return false;
        }

        if (state[course] == 2) {
            return true;
        }

        state[course] = 1;

        for (int next : graph.get(course)) {
            if (!dfs(next, graph, state, order, index)) {
                return false;
            }
        }

        state[course] = 2;

        order[index[0]] = course;
        index[0]--;

        return true;
    }
}
