public class UnionFind {
    public static void main(String[] args) {
        int[][]maze = new int[10][10];
        for (int i=0; i<10; i++)
            for (int j=0; j<10; j++)
                maze[i][j] = (int)(Math.random()*10);
    }
}
