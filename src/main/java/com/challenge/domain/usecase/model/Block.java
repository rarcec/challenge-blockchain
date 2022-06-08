package com.challenge.domain.usecase.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class Block {

    private Integer nonce;
    private String message;
    private String prevHash;
    private String hash;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public Block(Integer nonce, String message, String prevHash) {
        this.prevHash = prevHash;
        this.message = message;
        this.nonce = nonce;
        this.hash = createHash();
    }

    public String createHash() {
        Map<String, Object> map = new HashMap<>();
        map.put("prevHash", this.prevHash);
        map.put("message", this.message);
        map.put("nonce", this.nonce);
        return DigestUtils.sha256Hex(map.toString());
    }

    public void mineBlock(Integer difficult) {
        String zeros = StringUtils.leftPad("", difficult, "0");
        while (!this.getHash().substring(0, difficult).equals(zeros)) {
            this.nonce += 1;
            this.setHash(createHash());
        }
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
