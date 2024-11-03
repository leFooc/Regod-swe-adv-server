package com.regod.app.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,

        @NotEmpty
        String tittle,

        LocalDateTime startedOn,
        LocalDateTime completedOn,

        @Positive
        Integer miles,


        Location location
) {};
