package com.vizor.test.view;

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
            imageLabels.add(jLabel);
        }
    }

    public void uploadImages(List<ImageIcon> images) {

        final int pageSize = imageLabels.size();
        if (images.size() > pageSize) {
            images = images.subList(0, pageSize);
        }
        for (int i = 0; i < images.size(); i++) {
            final ImageIcon imageIcon = images.get(i);
            final JLabel imageLabel = imageLabels.get(i);
            imageLabel.setIcon(imageIcon);
        }
    }
}
