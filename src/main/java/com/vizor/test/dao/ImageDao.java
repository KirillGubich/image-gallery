package com.vizor.test.dao;

import javax.swing.ImageIcon;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Interface for interaction with image storage
 */
public interface ImageDao {

    /**
     * Reads images from storage in given range
     *
     * @param from first index in range
     * @param to   last index in range
     * @return list of {@link ImageIcon}
     */
    List<ImageIcon> readRange(int from, int to);

    /**
     * Reads image from storage by given name
     *
     * @param name image name
     * @return image with given name
     */
    ImageIcon readByName(String name);

    /**
     * Returns amount of images at storage
     *
     * @return images amount
     */
    int getImagesAmount();

    /**
     * Saves image to storage
     *
     * @param inputStream image file input stream
     * @param imageName   image name
     * @throws IOException if saving will be corrupted
     */
    void save(InputStream inputStream, String imageName) throws IOException;
}
