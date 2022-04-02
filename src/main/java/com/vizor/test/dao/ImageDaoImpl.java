package com.vizor.test.dao;

import javax.swing.ImageIcon;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            imageIcons.add(imageIcon);
        }
        return imageIcons;
    }

    @Override
    public int getImagesAmount() {

        final File[] files = sourceDirectory.listFiles();
        return files != null ? files.length : 0;
    }
}
