package org.example.processador_encargos.adapters.inbound.batch;


import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
@StepScope
public class FilePartioner implements Partitioner {

    private final String filePath;

    public FilePartioner(@Value("#{jobParameters['filePath']}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        if (filePath == null) {
            throw new IllegalStateException("ERRO: O parâmetro 'filePath' não chegou ao Partitioner. Verifique o Controller.");
        }

        Map<String, ExecutionContext> map = new HashMap<>();
        try {
            var path = Paths.get(filePath);
            if (!Files.exists(path)) {
                throw new IOException("Arquivo não encontrado no caminho: " + filePath);
            }

            long fileSize = Files.size(path);
            long partitionSize = fileSize / gridSize;

            for (int i = 0; i < gridSize; i++) {
                long start = i * partitionSize;
                long end = (i == gridSize - 1) ? fileSize : (start + partitionSize);

                ExecutionContext ctx = new ExecutionContext();
                ctx.putLong("startByte", start);
                ctx.putLong("endByte", end);
                ctx.putString("filePath", filePath);

                map.put("partition-" + i, ctx);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o arquivo para partição", e);
        }
        return map;
    }
}