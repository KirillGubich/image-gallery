package com.vizor.test.view;

import com.vizor.test.service.ImageServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class ImagePanel extends JPanel {

    private static final int HGAP = 10;
    private static final int VGAP = 10;
    private static final int IMAGE_BORDER_THICKNESS = 10;
    private static final int MAX_IMAGE_WIDTH = 170;
    private static final int MAX_IMAGE_HEIGHT = 130;

    private final int rowsCount;
    private final int columnsCount;
    private final List<JLabel> imageLabels;

    public ImagePanel(Color backgroundColor, int rowsCount, int columnsCount) {

        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        final int initialCapacity = rowsCount * columnsCount;
        imageLabels = new ArrayList<>(initialCapacity);
        setLayout(new GridLayout(rowsCount, columnsCount, HGAP, VGAP));
        setBackground(backgroundColor);
        initImageLabels(backgroundColor);
        imageLabels.forEach(this::add);
    }

    private void initImageLabels(Color backgroundColor) {

        final int capacity = rowsCount * columnsCount;
        LineBorder imageBorder = new LineBorder(backgroundColor, IMAGE_BORDER_THICKNESS);
        for (int i = 0; i < capacity; i++) {
            final JLabel jLabel = new JLabel();
            jLabel.setBorder(imageBorder);
            jLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabels.add(jLabel);
        }
    }

    public void uploadImages(List<ImageIcon> imageIcons) {

        final ImageServiceImpl imageService = ImageServiceImpl.getInstance();
        final int pageSize = imageLabels.size();
        if (imageIcons.size() > pageSize) {
            imageIcons = imageIcons.subList(0, pageSize);
        }
        for (int i = 0; i < imageIcons.size(); i++) {
            ImageIcon imageIcon = imageIcons.get(i);
            imageIcon = imageService.scaleImage(imageIcon, MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT);
            final JLabel imageLabel = imageLabels.get(i);
            imageLabel.setIcon(imageIcon);
        }
        for (int i = imageIcons.size(); i < imageLabels.size(); i++) {
            final JLabel imageLabel = imageLabels.get(i);
            imageLabel.setIcon(null);
        }
    }
}
