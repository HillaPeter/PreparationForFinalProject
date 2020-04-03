package Game;

import java.util.ArrayList;

public class Account {
    private String name;
    private ArrayList<Transaction> transactions;

    public Account(String name, ArrayList<Transaction> transactions) {
        this.name = name;
        this.transactions = transactions;
    }



}
