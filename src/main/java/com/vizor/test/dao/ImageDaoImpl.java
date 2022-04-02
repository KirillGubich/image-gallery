package com.vizor.test.dao;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ImageDaoImpl implements ImageDao {

    private static final String SOURCE_DIRECTORY_PATH = "src/main/resources/assets";
    private static ImageDaoImpl instance;

    private final File sourceDirectory;

    private ImageDaoImpl() {

        sourceDirectory = new File(SOURCE_DIRECTORY_PATH);
    }

    public static ImageDaoImpl getInstance() {

        if (instance == null) {
            instance = new ImageDaoImpl();
        }
        return instance;
    }

    @Override
    public List<ImageIcon> readRange(int from, int to) {

        final File[] files = sourceDirectory.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        List<ImageIcon> imageIcons = new ArrayList<>();
        if (to >= files.length) {
            to = files.length - 1;
        }
        for (int i = from; i <= to; i++) {
            final String fileName = files[i].getName();
            final Path filePath = Paths.get(SOURCE_DIRECTORY_PATH, fileName);
            ImageIcon imageIcon = new ImageIcon(filePath.toString());
            imageIcon.setDescription(fileName);
            imageIcons.add(imageIcon);
        }
        return imageIcons;
    }

    @Override
    public ImageIcon readByName(String name) {

        final File[] files = sourceDirectory.listFiles();
        if (files == null) {
            return null;
        }
        Optional<File> optionalFile = Arrays.stream(files)
                .filter(file -> file.getName().equals(name))
                .findFirst();
        if (!optionalFile.isPresent()) {
            return null;
        }
        String fileName = optionalFile.get().getName();
        Path filePath = Paths.get(SOURCE_DIRECTORY_PATH, fileName);
        return new ImageIcon(filePath.toString());
    }

    @Override
    public int getImagesAmount() {

        final File[] files = sourceDirectory.listFiles();
        return files != null ? files.length : 0;
    }

    @Override
    public void save(InputStream inputStream, String imageName) throws IOException {

        final Path path = Paths.get(SOURCE_DIRECTORY_PATH, imageName);
        Files.copy(inputStream, path);
        inputStream.close();
    }
}
