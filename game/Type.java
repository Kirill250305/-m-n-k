package game;

import java.util.Scanner;

public class Type {
    public int m, n, k, d;
    public Type(Scanner in) {
        this.m = in.nextInt();
        this.n = in.nextInt();
        this.k = in.nextInt();
    }

    public Type(int d, int k) {
        this.d = d;
        this.n = d;
        this.m = d;
        this.k = k;
    }

    public Type(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
    }
}
