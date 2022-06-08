package com.challenge.domain.usecase.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Blockchain {

    private final List<Block> blockList = new ArrayList<>();
    // Change the difficulty of PoW
    private Integer difficulty = 2;

    public Blockchain() {
        this.blockList.add(new Block(0, "Hola Mundo", "0000000000000000000000000000000000000000000000000000000000000000"));
    }

    private Block getLastBlock() {
        return this.blockList.get(this.blockList.size() - 1);
    }

    public void addBlock(Block block) {
        block.setPrevHash(getLastBlock().getHash());
        block.mineBlock(this.difficulty);
        this.blockList.add(block);
    }

    public Boolean isValid() {

        for (int i = 1; i < this.getBlockList().size(); i++) {
            Block previous = this.blockList.get(i - 1);
            Block current = this.blockList.get(i);
            if (!current.getHash().equals(current.createHash())) {
                System.out.println("Invalid Block");
                return false;
            }
            if (!previous.getPrevHash().equals(previous.getHash())) {
                System.out.println("Invalid Chain");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blockchain that = (Blockchain) o;
        return Objects.equals(blockList, that.blockList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockList);
    }
}
