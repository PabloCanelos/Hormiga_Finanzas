/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.model;

/**
 *
 * @author Pavilion X360
 */
public class Transaction {
    private int id;
    private int userId;
    private int accountId;
    private int categoryId;
    private double amount;
    private String transactionType;
    private String description;
    private String transactionDate;

    
    public Transaction() {}

    public Transaction(int userId, int accountId, int categoryId, double amount, String transactionType, String description) {
        this(0, userId, accountId, categoryId, amount, transactionType, description, null);
    }

   
    public Transaction(int id, int userId, int accountId, int categoryId, double amount, String transactionType, String description, String transactionDate) {
        this.id = id;
        this.userId = userId;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionDate = transactionDate;


}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}