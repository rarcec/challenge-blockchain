package com.challenge.domain.usecase.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Transaction {

    private final Integer amount;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public Transaction(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
