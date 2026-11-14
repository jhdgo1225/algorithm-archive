import java.util.Scanner;
import java.util.Arrays;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int[] nums = new int[N];
            int sum = 0;
            for (int i=0; i<N; i++) {
                nums[i] = sc.nextInt();
                sum += nums[i];
            }
            Arrays.sort(nums);
            int s = 0, e = N-1, eqNum = sum / N, result = 0;
            while (s < e && nums[s] != nums[e]) {
                if (nums[e] > eqNum && nums[s] < eqNum) {
                    int tmp = eqNum - nums[s];
                    if (eqNum < nums[e] - tmp) {
                        result += tmp;
                        nums[s++] += tmp;
                        nums[e] -= tmp;
                    } else {
                        result += nums[e] - eqNum;
                        nums[s] += nums[e] - eqNum;
                        nums[e--] = eqNum;
                    }
                } else {
                    if (eqNum == nums[s]) s++;
                    if (eqNum == nums[e]) e--;
                }
            }
            System.out.println("#" + test_case + " " + result);
		}
	}
}