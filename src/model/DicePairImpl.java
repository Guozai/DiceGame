package model;

import model.interfaces.DicePair;

public class DicePairImpl implements DicePair {
    private int dice1; // value of dice 1 (1 to 6 for standard casino dice)
    private int dice2; // value of dice 2 (1 to 6)
    private int numFaces; // number of dice faces (6 for standard casino dice)

    public DicePairImpl(int dice1, int dice2, int numFaces) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.numFaces = numFaces;
    }

    public int getDice1() {
        return this.dice1;
    }

    public int getDice2() {
        return this.dice2;
    }

    public int getNumFaces() {
        return this.numFaces;
    }

    @Override
    public String toString() {
        return " Dice 1: " + this.getDice1() + ", Dice 2: " + this.getDice2() +
                " .. Total: " + (this.getDice1() + this.getDice2());
    }
}
