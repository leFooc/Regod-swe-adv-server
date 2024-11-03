package com.regod.app.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = this.findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(
                1,
                "Monday morning",
                LocalDateTime.now(),
                LocalDateTime.now(),
                2,
                Location.INDOOR
        ));
        runs.add(new Run(
                2,
                "Tuesday morning",
                LocalDateTime.now(),
                LocalDateTime.now(),
                2,
                Location.INDOOR
        ));
    }
}
