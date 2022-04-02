package com.vizor.test.service;

import com.vizor.test.dao.ImageDao;
import com.vizor.test.dao.ImageDaoImpl;

import javax.swing.ImageIcon;
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
}
