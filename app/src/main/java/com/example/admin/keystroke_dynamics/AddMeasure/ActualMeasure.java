package com.example.admin.keystroke_dynamics.AddMeasure;

public class ActualMeasure {

    public int getDigit9() { return digit9; }
    public void setDigit9(int digit9) { this.digit9 = digit9; }

    public int getR() { return R; }
    public void setR(int r) { R = r; }

    public int getJ() { return J; }
    public void setJ(int j) { J = j; }

    public int getH() { return h; }
    public void setH(int h) { this.h = h; }

    public int getL() { return l; }
    public void setL(int l) { this.l = l; }

    public int getDigit6() { return digit6; }
    public void setDigit6(int digit6) { this.digit6 = digit6; }

    public int getA() { return a; }
    public void setA(int a) { this.a = a; }

    public int getDigit0() { return digit0; }
    public void setDigit0(int digit0) { this.digit0 = digit0; }

    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) {this.userId = userId; }

    public static ActualMeasure getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ActualMeasure();
        return INSTANCE;
    }

    private int userId;
    private int digit9;
    private int R;
    private int J;
    private int h;
    private int l;
    private int digit6;
    private int a;
    private int digit0;
    private int n;

    private static ActualMeasure INSTANCE;
}
