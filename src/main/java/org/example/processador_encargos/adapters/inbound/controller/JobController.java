package org.example.processador_encargos.adapters.inbound.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobLauncher asyncJobLauncher;
    private final Job processarEncargosJob;

    @PostMapping("/processar")
    public ResponseEntity<String> processar(@RequestParam String path) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("filePath", path)
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();
        asyncJobLauncher.run(processarEncargosJob, params);

        return ResponseEntity.ok("Job disparado com sucesso.");
    }
}