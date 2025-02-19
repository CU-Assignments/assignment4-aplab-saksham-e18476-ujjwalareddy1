import java.util.ArrayList;
import java.util.List;

public class BeautifulArray {
    public static int[] beautifulArray(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(1); // Base case
        
        while (result.size() < n) {
            List<Integer> temp = new ArrayList<>();
            // Generate odd elements
            for (int num : result) {
                if (2 * num - 1 <= n) temp.add(2 * num - 1);
            }
            // Generate even elements
            for (int num : result) {
                if (2 * num <= n) temp.add(2 * num);
            }
            result = temp;
        }

        // Convert list to array
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int n = 5;
        int[] beautifulArray = beautifulArray(n);
        for (int num : beautifulArray) {
            System.out.print(num + " ");
        }
    }
}
