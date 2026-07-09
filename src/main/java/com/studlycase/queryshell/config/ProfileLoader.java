package com.studlycase.queryshell.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public final class ProfileLoader {

    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public TargetProfile load(File profileFile) throws IOException {
        return mapper.readValue(profileFile, TargetProfile.class);
    }
}
