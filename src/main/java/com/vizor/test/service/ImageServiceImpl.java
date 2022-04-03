package com.vizor.test.service;

import com.vizor.test.dao.ImageDao;
import com.vizor.test.dao.ImageDaoImpl;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    public List<ImageIcon> readPaginated(int page, int size, String filter) {

        int firstIndex = (page - 1) * size;
        int lastIndex = firstIndex + size - 1;
        return filter.isEmpty() ?
                imageDao.readRange(firstIndex, lastIndex) :
                imageDao.readWithFilter(firstIndex, lastIndex, filter);
    }

    @Override
    public ImageIcon readByName(String name) {

        return imageDao.readByName(name);
    }

    @Override
    public void deleteByName(String name) {

        imageDao.delete(name);
    }

    @Override
    public void save(String imagePath) throws IOException {

        final File file = new File(imagePath);
        final FileInputStream inputStream = new FileInputStream(file);
        final String fileName = file.getName();
        imageDao.save(inputStream, fileName);
    }

    @Override
    public boolean checkNextPage(int page, int size, String filter) {

        int imagesAmount = filter.isEmpty() ?
                imageDao.getImagesAmount() :
                imageDao.getImagesAmountWithFilter(filter);
        return (page - 1) * size < imagesAmount;
    }

    @Override
    public ImageIcon scale(ImageIcon imageIcon, int maxWidth, int maxHeight) {

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
