package dev.jorvan.testing.exeptions;

import java.util.Map;

public record ErrorsResponse(
        Map<String , String> errors
) {
}
