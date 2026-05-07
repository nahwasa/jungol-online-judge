import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 1000;
    private int r, c;
    private int[][] arr;
    private boolean[] vert;
    private int response = INF;

    private void solution(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        vert = new boolean[c];

        for (int i = 0; i < r; i++) {
            String s = br.readLine();

            int cnt = 0;
            for (int j = 0; j < c; j++) {
                boolean isStar = s.charAt(j) == '*';
                if (isStar) arr[i][j] = ++cnt;
                else cnt = 0;
            }
        }
        if (r != 5) cnt();
        else caseWork(0);

        System.out.println(response == INF ? -1 : response);
    }

    private void caseWork(int idx) {
        if (idx == c) {
            cnt();
            return;
        }

        boolean canVert = true;
        for (int i = 0; i < r; i++) {
            if (arr[i][idx] == 0) {
                canVert = false;
                break;
            }
        }

        caseWork(idx+1);

        if (!canVert) return;

        vert[idx] = true;
        caseWork(idx+1);
        vert[idx] = false;
    }

    private void cnt() {
        int result = 0;

        for (int i = 0; i < r; i++) {
            int cnt = cntRow(arr[i]);
            if (cnt == INF) return;

            result += cnt;
        }

        for (int i = 0; i < c; i++) if (vert[i]) result++;

        response = Math.min(response, result);
    }

    private int cntRow(int[] row) {
        int result = 0;

        int idx = 0;
        while (idx < c) {
            while (idx < c && (row[idx] == 0 || vert[idx])) idx++;

            if (idx == c) break;

            int limit = idx+5;
            while (idx < c && idx < limit && row[idx] != 0) idx++;

            if (row[idx-1] < 5) return INF;

            result++;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution(new BufferedReader(new InputStreamReader(System.in)));
    }
}
