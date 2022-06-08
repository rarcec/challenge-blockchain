package com.challenge.domain.usecase.impl;

import com.challenge.domain.usecase.LogService;
import com.challenge.domain.usecase.model.Block;
import com.challenge.domain.usecase.model.Blockchain;
import com.challenge.utils.CSVUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
@AllArgsConstructor
public class LogServiceImpl implements LogService {

    private final CSVUtils csvUtils;

    @Override
    public void linkedLineCommonLog() {

        // Create Blockchain
        Blockchain blockchain = new Blockchain();
        // Adding New Blocks
        blockchain.addBlock(new Block(2, "Hola", ""));
        blockchain.addBlock(new Block(4, "Chao", ""));
        blockchain.addBlock(new Block(2, "Bienvenido", ""));
        blockchain.addBlock(new Block(7, "Vuelve Pronto", ""));
        // Create List to save into CSV file
        List<String> data = new ArrayList<>();
        blockchain.getBlockList().forEach(block -> {
            StringBuilder chain = new StringBuilder()
                    .append(block.getHash())
                    .append(",")
                    .append(block.getMessage())
                    .append(",")
                    .append(String.format("%d", block.getNonce()));
            data.add(String.valueOf(chain));
        });
        // Print all the blocks
        blockchain.getBlockList().forEach(block -> log.info(block.toString()));
        try {
            // Save Hash + message + nonce
            csvUtils.writeMessage(data);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when is trying to save the data into csv file");
        }
    }
}
