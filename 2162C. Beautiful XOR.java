import java.util.*;
import java.io.*;

public class BeautifulXOR {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if (a == b) {
                sb.append("0\n");
            } else if ((a ^ b) <= a) {
                sb.append("1\n");
                sb.append((a ^ b) + "\n");
            } else {
                // Try to find a 2-operation sequence
                boolean found = false;
                int x1 = -1, x2 = -1;
                
                // Try all possible first operations
                for (int firstOp = 0; firstOp <= a; firstOp++) {
                    int intermediate = a ^ firstOp;
                    if (intermediate == 0) continue; // Can't proceed from 0
                    
                    int secondOp = intermediate ^ b;
                    if (secondOp <= intermediate) {
                        x1 = firstOp;
                        x2 = secondOp;
                        found = true;
                        break;
                    }
                }
                
                if (found) {
                    sb.append("2\n");
                    sb.append(x1 + " " + x2 + "\n");
                } else {
                    sb.append("-1\n");
                }
            }
        }
        
        System.out.print(sb);
    }
}
