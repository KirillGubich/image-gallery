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
     * Scales image according to limitations
     *
     * @param imageIcon image to scale
     * @param maxWidth  maximal width
     * @param maxHeight maximal height
     * @return scaled image
     */
    ImageIcon scaleImage(ImageIcon imageIcon, int maxWidth, int maxHeight);
}
