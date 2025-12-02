import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AgricultureExportCost {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] productTypes = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            productTypes[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] shortestDistances = new int[n + 1];
        Arrays.fill(shortestDistances, -1); 

        Queue<Integer> q = new LinkedList<>();

        shortestDistances[1] = 0;
        q.offer(1);

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj.get(u)) {
                if (shortestDistances[v] == -1) {
                    shortestDistances[v] = shortestDistances[u] + 1;
                    q.offer(v);
                }
            }
        }
        
        int[] maxShortestDistance = new int[k + 1];
        
        for (int i = 1; i <= n; i++) {
            int productType = productTypes[i];
            int distance = shortestDistances[i];
            
            if (distance != -1) { 
                maxShortestDistance[productType] = Math.max(maxShortestDistance[productType], distance);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= k; i++) {
            sb.append(maxShortestDistance[i]);
            if (i < k) {
                sb.append(" ");
            }
        }
        
        pw.println(sb.toString());
        pw.flush();
    }
}
