package com.vizor.test.dao;

import javax.swing.ImageIcon;
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
     * Reads image from storage by given id
     *
     * @param id image id
     * @return image with given id
     */
    ImageIcon readById(int id);

    /**
     * Returns amount of images at storage
     *
     * @return images amount
     */
    int getImagesAmount();
}
