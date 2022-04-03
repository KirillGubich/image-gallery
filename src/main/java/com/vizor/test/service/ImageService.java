package com.vizor.test.service;

import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.List;

/**
 * Service for interaction with images
 */
public interface ImageService {

    /**
     * Reads images according to given page and size
     *
     * @param page   page number
     * @param size   amount of images on page
     * @param filter optional text filter
     * @return list of {@link ImageIcon}
     */
    List<ImageIcon> readPaginated(int page, int size, String filter);

    /**
     * Reads image by name
     *
     * @param name image name
     * @return image with given name
     */
    ImageIcon readByName(String name);

    /**
     * Scales image according to limitations
     *
     * @param imageIcon image to scale
     * @param maxWidth  maximal width
     * @param maxHeight maximal height
     * @return scaled image
     */
    ImageIcon scale(ImageIcon imageIcon, int maxWidth, int maxHeight);

    /**
     * Checks that next page is available
     *
     * @param page   page number
     * @param size   amount of images on page
     * @param filter optional text filter
     * @return true if next page is available, else - false
     */
    boolean checkNextPage(int page, int size, String filter);

    /**
     * Saves image
     *
     * @param imagePath image file path
     * @throws IOException if file not exist
     */
    void save(String imagePath) throws IOException;

    /**
     * Deletes image by given name
     *
     * @param name image name
     */
    void deleteByName(String name);
}
