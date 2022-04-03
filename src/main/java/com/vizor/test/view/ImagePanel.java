package com.vizor.test.view;

import com.vizor.test.service.ImageService;
import com.vizor.test.service.ImageServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ImagePanel extends JPanel {

    private static final int HGAP = 10;
    private static final int VGAP = 10;
    private static final int IMAGE_BORDER_THICKNESS = 10;
    private static final int MAX_IMAGE_WIDTH = 150;
    private static final int MAX_IMAGE_HEIGHT = 110;
    private static final int IMAGE_HEIGHT_BUFFER = 35;

    private final int rowsCount;
    private final int columnsCount;
    private final List<JLabel> imageLabels;
    private final ImageService imageService;
    private final GalleryFrame galleryFrame;

    public ImagePanel(Color backgroundColor, int rowsCount, int columnsCount, GalleryFrame galleryFrame) {

        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        this.galleryFrame = galleryFrame;
        imageService = ImageServiceImpl.getInstance();
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
            jLabel.setHorizontalTextPosition(JLabel.CENTER);
            jLabel.setVerticalTextPosition(JLabel.BOTTOM);
            jLabel.addMouseListener(new ImageListener());
            imageLabels.add(jLabel);
        }
    }

    public void uploadImages(List<ImageIcon> imageIcons) {

        final int pageSize = imageLabels.size();
        if (imageIcons.size() > pageSize) {
            imageIcons = imageIcons.subList(0, pageSize);
        }
        for (int i = 0; i < imageIcons.size(); i++) {
            ImageIcon imageIcon = imageIcons.get(i);
            final String description = imageIcon.getDescription();
            imageIcon = imageService.scale(imageIcon, MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT);
            imageIcon.setDescription(description);
            final JLabel imageLabel = imageLabels.get(i);
            imageLabel.setIcon(imageIcon);
            imageLabel.setText(imageIcon.getDescription());
        }
        for (int i = imageIcons.size(); i < imageLabels.size(); i++) {
            final JLabel imageLabel = imageLabels.get(i);
            imageLabel.setIcon(null);
            imageLabel.setText(null);
        }
    }

    private class ImageListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            Thread clickProcessThread = new Thread(() -> processClick(e));
            clickProcessThread.start();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        private void processClick(MouseEvent e) {

            if (galleryFrame.isDeleteModeEnabled()) {
                deleteImage(e);
                galleryFrame.updateImages();

            } else {
                ImageIcon imageIcon = readImageIcon(e);
                showFullSizeImage(imageIcon);
            }
        }

        private void showFullSizeImage(ImageIcon imageIcon) {

            if (imageIcon == null) {
                return;
            }
            final ImageIcon scaledImage = imageService.scale(imageIcon, 1920, 1080);
            final JLabel imageLabel = new JLabel();
            imageLabel.setIcon(scaledImage);
            final Thread fullSizeImageThread = new Thread(() -> {
                JFrame frame = createFrame(scaledImage.getIconWidth(), scaledImage.getIconHeight());
                frame.add(imageLabel);
                frame.setVisible(true);
            });
            fullSizeImageThread.start();
        }


        private ImageIcon readImageIcon(MouseEvent e) {

            JLabel jLabel = (JLabel) (e.getComponent());
            ImageIcon imageIcon = (ImageIcon) jLabel.getIcon();
            if (imageIcon == null) {
                return null;
            }
            final String description = imageIcon.getDescription();
            return imageService.readByName(description);
        }

        private JFrame createFrame(int width, int height) {

            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(width, height + IMAGE_HEIGHT_BUFFER));
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            return frame;
        }

        private void deleteImage(MouseEvent e) {

            JLabel jLabel = (JLabel) (e.getComponent());
            ImageIcon imageIcon = (ImageIcon) jLabel.getIcon();
            if (imageIcon == null) {
                return;
            }
            String description = imageIcon.getDescription();
            imageService.deleteByName(description);
        }
    }
}
