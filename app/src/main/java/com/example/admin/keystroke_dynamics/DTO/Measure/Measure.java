package com.example.admin.keystroke_dynamics.DTO.Measure;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.admin.keystroke_dynamics.DTO.User.User;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "measures", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "Id", childColumns = "UserId", onDelete = CASCADE))
public class Measure {

    public Measure(int userId, int digit9, int R, int J, int h, int l, int digit6, int a, int digit0, int n){
        this.userId = userId;
        this.digit9 = digit9;
        this.R = R;
        this.J = J;
        this.h = h;
        this.l = l;
        this.digit6 = digit6;
        this.a = a;
        this.digit0 = digit0;
        this.n = n;
    }

    public Measure() { }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name ="UserId")
    private int userId;

    @ColumnInfo(name = "9")
    private int digit9;

    @ColumnInfo(name = "R")
    private int R;

    @ColumnInfo(name = "J")
    private int J;

    @ColumnInfo(name = "h")
    private int h;

    @ColumnInfo(name = "l")
    private int l;

    @ColumnInfo(name = "6")
    private int digit6;

    @ColumnInfo(name = "a")
    private int a;

    @ColumnInfo(name = "0")
    private int digit0;

    @ColumnInfo(name = "n")
    private int n;

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) {this.userId = userId; }
}
