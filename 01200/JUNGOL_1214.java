import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    int[] arr;
    long max;
    int n;

    class Stack<T> {
        private ArrayDeque<T> dq = new ArrayDeque<>();

        public boolean isEmpty() {
            return dq.isEmpty();
        }

        public void push(T x) {
            dq.push(x);
        }

        public T peek() {
            return dq.peek();
        }

        public T pop() {
            return dq.pop();
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new  StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            
            int h = arr[i];
            if (stk.isEmpty() || arr[stk.peek()] <= h) {
                stk.push(i);
                continue;
            }

            popUntil(stk, i);
            stk.push(i);
        }
        popUntil(stk, n);

        System.out.println(max);
    }

    private void popUntil(Stack<Integer> stk, int curIdx) {
        int curH = curIdx == n ? 0 : arr[curIdx];
        while (!stk.isEmpty() && arr[stk.peek()] > curH) {
            int h = arr[stk.pop()];
            int w = stk.isEmpty() ? curIdx : curIdx - stk.peek() - 1;
            max = Math.max(max, 1l*h*w);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
