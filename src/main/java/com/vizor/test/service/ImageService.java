package com.vizor.test.service;

import javax.swing.ImageIcon;
import java.util.List;

/**
 * Service for interaction with images
 */
public interface ImageService {

    /**
     * Reads images according to given page and size
     *
     * @param page page number
     * @param size amount of images on page
     * @return list of {@link ImageIcon}
     */
    List<ImageIcon> readPaginated(int page, int size);

    /**
     * Reads image by id
     *
     * @param id image id
     * @return image with given id
     */
    ImageIcon readById(int id);

    /**
     * Scales image according to limitations
     *
     * @param imageIcon image to scale
     * @param maxWidth  maximal width
     * @param maxHeight maximal height
     * @return scaled image
     */
    ImageIcon scaleImage(ImageIcon imageIcon, int maxWidth, int maxHeight);

    /**
     * Checks that next page is available
     *
     * @param page page number
     * @param size amount of images on page
     * @return true if next page is available, else - false
     */
    boolean checkNextPage(int page, int size);
}
