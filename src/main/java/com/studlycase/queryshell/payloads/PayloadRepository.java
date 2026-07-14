package com.studlycase.queryshell.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public final class PayloadRepository {

    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private List<Payload> payloads;

    public void loadFrom(File payloadFile) throws IOException {
        payloads = mapper.readValue(payloadFile, mapper.getTypeFactory()
                .constructCollectionType(List.class, Payload.class));
    }

    public List<Payload> forStrategy(String strategyName) {
        if (payloads == null) {
            return List.of();
        }
        return payloads.stream()
                .filter(p -> p.strategyName().equals(strategyName))
                .collect(Collectors.toList());
    }
}
