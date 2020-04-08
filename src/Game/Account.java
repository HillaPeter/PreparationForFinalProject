package Game;

import java.util.ArrayList;

public class Account {
    private String name;
    private ArrayList<Transaction> transactions;
    private double amountOfTeam;

    public Account(String name, ArrayList<Transaction> transactions, double amountOfTeam) {
        this.name = name;
        this.transactions = transactions;
        this.amountOfTeam = amountOfTeam;
    }

    public void setAmountOfTeam(double amountOfTeam) {
        this.amountOfTeam = amountOfTeam;
    }

    //todo!
    public Account() {
        this.name = "";
        this.transactions = new ArrayList<Transaction>();
        amountOfTeam=0; //default->need to update!
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getAmountOfTeam() {
        return amountOfTeam;
    }
}
