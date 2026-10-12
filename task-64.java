import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        int[][] directions = {
            {-1, 0},   
            {1, 0},  
            {0, -1}, 
            {0, 1}   
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    mat[newRow][newCol] == -1) {

                    mat[newRow][newCol] = mat[row][col] + 1;

                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return mat;
    }
}
