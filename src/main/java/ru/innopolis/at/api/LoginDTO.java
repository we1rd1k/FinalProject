package ru.innopolis.at.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {

    @Builder.Default
    private String userName = "Test";
    @Builder.Default
    private String password = "Q!w2e3r4t5y6";
}
