package org.example.processador_encargos.adapters.inbound.batch;


import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class FilePartioner implements Partitioner {

    @Value("${batch.file.path}")
    private String filePath;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>();
        try {
            long fileSize = Files.size(Paths.get(filePath));
            long partitionSize = fileSize / gridSize;

            for (int i = 0; i < gridSize; i++) {
                long start = i * partitionSize;
                long end = (i == gridSize - 1) ? fileSize : start + partitionSize;

                ExecutionContext ctx = new ExecutionContext();
                ctx.putLong("startByte", start);
                ctx.putLong("endByte", end);
                ctx.putString("filePath", filePath);

                map.put("partition-" + i, ctx);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao particionar arquivo", e);
        }
        return map;
    }
}