class Solution {

    class NodeInfo {
        TreeNode node;
        int row;
        int col;

        NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        List<NodeInfo> nodes = new ArrayList<>();

        dfs(root, 0, 0, nodes);

        Collections.sort(nodes, (a, b) -> {

            if (a.col != b.col) {
                return a.col - b.col;
            }

            if (a.row != b.row) {
                return a.row - b.row;
            }

            return a.node.val - b.node.val;
        });

        List<List<Integer>> result = new ArrayList<>();

        int previousColumn = Integer.MIN_VALUE;

        for (NodeInfo info : nodes) {

            if (info.col != previousColumn) {
                result.add(new ArrayList<>());
                previousColumn = info.col;
            }

            result.get(result.size() - 1).add(info.node.val);
        }

        return result;
    }

    private void dfs(TreeNode root, int row, int col,
                     List<NodeInfo> nodes) {

        if (root == null) {
            return;
        }

        nodes.add(new NodeInfo(root, row, col));

        dfs(root.left, row + 1, col - 1, nodes);

        dfs(root.right, row + 1, col + 1, nodes);
    }
}
