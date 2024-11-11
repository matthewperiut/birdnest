package com.matthewperiut.birdnest.config;

import dev.architectury.platform.Platform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class BirdNestConfig {
    private static final String CONFIG_FOLDER_NAME = "birdnest";
    private static final String CONFIG_FILE_NAME = "config.txt";
    private static final String DEFAULT_CONTENTS = "leaves=250";
    private static final String LEAVES_KEY = "leaves";

    public static void init() {
        try {
            Path configFolderPath = Platform.getConfigFolder().resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);

            if (!Files.exists(configFolderPath)) {
                Files.createDirectories(configFolderPath);
            }

            if (!Files.exists(configFilePath)) {
                Files.writeString(configFilePath, DEFAULT_CONTENTS, StandardOpenOption.CREATE_NEW);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLeaves() {
        try {
            Path configFolderPath = Platform.getConfigFolder().resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);
            return readLeavesFromFile(configFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Integer.parseInt(DEFAULT_CONTENTS.split("=")[1]); // Fallback to default
        }
    }

    public static void setLeaves(int leaves) {
        try {
            Path configFolderPath = Platform.getConfigFolder().resolve(CONFIG_FOLDER_NAME);
            Path configFilePath = configFolderPath.resolve(CONFIG_FILE_NAME);

            // Write the new value to the file
            Files.writeString(configFilePath, LEAVES_KEY + "=" + leaves, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int readLeavesFromFile(Path configFilePath) throws IOException {
        Optional<String> line = Files.lines(configFilePath).filter(l -> l.startsWith(LEAVES_KEY + "=")).findFirst();
        if (line.isPresent()) {
            String value = line.get().split("=")[1].trim();
            return Integer.parseInt(value);
        } else {
            // If the file format is incorrect or value is missing, fallback to default.
            return Integer.parseInt(DEFAULT_CONTENTS.split("=")[1]);
        }
    }
}
