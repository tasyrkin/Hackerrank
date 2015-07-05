import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.concurrent.SynchronousQueue;
import java.util.regex.*;

public class Sudoku {

    private static class Pair{
        int x;
        int y;
        public Pair(int i, int j){
            x = i;
            y = j;
        }
    }
    
    /* Head ends here */
    static void sudoku_solve(int [][] g){
        
        if(g.length != g[0].length)throw new IllegalArgumentException("Matrix must be square");
        
        int n = g.length;
        
        Set<Integer>[] col = new HashSet[n];
        Set<Integer>[] row = new HashSet[n];
        List<Integer> gen = new LinkedList<Integer>();
        for(int i = 1; i <= n; i++){
            gen.add(i);
        }
        for(int i = 0; i < n; i++){
            row[i] = new HashSet<Integer>(gen);
            col[i] = new HashSet<Integer>(gen);
        }
        LinkedList<Pair> emptyList = new LinkedList<Pair>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(g[i][j] != 0)row[i].remove(g[i][j]);
                else emptyList.add(new Pair(i,j));
            }
        }
        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++){
                if(g[i][j] != 0)col[j].remove(g[i][j]);
            }
        }
        
        while(!emptyList.isEmpty()){
            Pair p = emptyList.removeFirst();
            Set<Integer> rw = row[p.x];
            Set<Integer> cl = col[p.y];
            Set<Integer> allowed = intersect(rw, cl);
            if(allowed.size() == 1){
                int num = allowed.iterator().next(); 
                g[p.x][p.y] = num;
                rw.remove(num);
                cl.remove(num);
            } else {
                if(rw.size() > 2 && cl.size() > 2){
                    int num = allowed.iterator().next();
                    g[p.x][p.y] = num;
                    rw.remove(num);
                    cl.remove(num);
                } else {
                    int num = -1;
                    if(rw.size() == 2){
                        for(int j = 0; j < n; j++){
                            if(j == p.y)continue;
                            if(g[p.x][j] == 0){
                                Set<Integer> tmp = intersect(allowed, col[j]);
                                if(tmp.size() == 1){
                                    for(int num2 : rw){
                                        if(num2 != tmp.iterator().next()){
                                            num = num2;
                                            break;
                                        }
                                    }
                                } else {
                                    num = tmp.iterator().next();
                                }
                            }
                        }
                    } else if(cl.size() == 2){
                        for(int i = 0; i < n; i++){
                            if(i == p.x)continue;
                            if(g[i][p.y] == 0){
                                Set<Integer> tmp = intersect(allowed, row[i]);
                                if(tmp.size() == 1){
                                    for(int num2 : cl){
                                        if(num2 != tmp.iterator().next()){
                                            num = num2;
                                            break;
                                        }
                                    }
                                } else if(tmp.size() > 1) {
                                    num = tmp.iterator().next();
                                }
                                else {
                                    num = tmp.iterator().next();
                                }
                            }
                        }
                    }
                    g[p.x][p.y] = num;
                    rw.remove(num);
                    cl.remove(num);
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < n; j++){
                if(sb.length() > 0)sb.append(" ");
                sb.append(g[i][j]);
            }
            System.out.println(sb.toString());
        }
    }

    private static Set<Integer> intersect(Set<Integer> left, Set<Integer> right){
        Set<Integer> result = new HashSet<Integer>();
        Iterator<Integer> iter = left.iterator();
        while(iter.hasNext()){
            int num = iter.next();
            if(right.contains(num)){
                result.add(num);
            }
        }
        return result;
    }

    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        int N = 4;
        int board[][] = new int[N][N];

        n = in.nextInt();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    board[j][k] = in.nextInt();
                }
            }
            sudoku_solve(board);
        }
    }
}