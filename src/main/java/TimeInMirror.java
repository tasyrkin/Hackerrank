import java.util.Scanner;

public class TimeInMirror {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int m = in.nextInt();
        in.close();

        h = 12 - h;
        if (h == 12) h = 0;
        m = 60 - m;
        if (m == 60) m = 0;
        System.out.println(h + " " + m);
    }
}
