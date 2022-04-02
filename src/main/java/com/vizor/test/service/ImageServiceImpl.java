package com.vizor.test.service;

import com.vizor.test.dao.ImageDao;
import com.vizor.test.dao.ImageDaoImpl;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    private static ImageServiceImpl instance;

    private final ImageDao imageDao;

    private ImageServiceImpl() {

        imageDao = ImageDaoImpl.getInstance();
    }

    public static ImageServiceImpl getInstance() {

        if (instance == null) {
            instance = new ImageServiceImpl();
        }
        return instance;
    }

    @Override
    public List<ImageIcon> readPaginated(int page, int size) {

        int firstIndex = (page - 1) * size;
        int lastIndex = firstIndex + size - 1;
        return imageDao.readRange(firstIndex, lastIndex);
    }

    @Override
    public boolean checkNextPage(int page, int size) {

        final int imagesAmount = imageDao.getImagesAmount();
        return (page - 1) * size < imagesAmount;
    }

    @Override
    public ImageIcon scaleImage(ImageIcon imageIcon, int maxWidth, int maxHeight) {

        int iconWidth = imageIcon.getIconWidth();
        int iconHeight = imageIcon.getIconHeight();
        int newWidth = iconWidth;
        int newHeight = iconHeight;
        if (iconWidth > maxWidth) {
            newWidth = maxWidth;
            double widthScale = maxWidth / (double) iconWidth;
            newHeight = (int) (widthScale * iconHeight);
        }
        if (newHeight > maxHeight) {
            newHeight = maxHeight;
            double heightScale = maxHeight / (double) iconHeight;
            newWidth = (int) (heightScale * iconWidth);
        }
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
