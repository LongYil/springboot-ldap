package com.xiaozhuge.springbootldap.cursor;

import java.awt.*;
import java.awt.geom.*;
import java.time.LocalDateTime;
import javax.swing.*;

public class AnalogClockWithNumbers extends JPanel {
    private int padding = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int clockRadius = Math.min(width, height) / 2 - padding;

        // Draw clock face
        g2d.setColor(Color.BLACK);
        g2d.drawOval(padding, padding, clockRadius * 2, clockRadius * 2);

        // Draw clock numbers
        g2d.setFont(new Font("Arial", Font.PLAIN, clockRadius / 6));
        FontMetrics fm = g2d.getFontMetrics();
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30 - 90);
            int numberX = (int) (clockRadius * Math.cos(angle) + width / 2);
            int numberY = (int) (clockRadius * Math.sin(angle) + height / 2);
            String number = Integer.toString(i);
            int stringWidth = fm.stringWidth(number);
            int stringHeight = fm.getAscent();
            g2d.drawString(number, numberX - stringWidth / 2, numberY + stringHeight / 2);
        }

        // Draw clock hands
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        double hourAngle = Math.toRadians((hour % 12) * 30 - 90);
        double minuteAngle = Math.toRadians(minute * 6 - 90);
        double secondAngle = Math.toRadians(second * 6 - 90);

        drawHand(g2d, clockRadius * 0.5, 6, hourAngle, Color.BLACK);
        drawHand(g2d, clockRadius * 0.8, 4, minuteAngle, Color.BLACK);
        drawHand(g2d, clockRadius * 0.9, 2, secondAngle, Color.RED);
    }

    private void drawHand(Graphics2D g2d, double length, int thickness, double angle, Color color) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int endX = centerX + (int) (length * Math.cos(angle));
        int endY = centerY + (int) (length * Math.sin(angle));

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(centerX, centerY, endX, endY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("带数字的指针形状时钟");
        AnalogClockWithNumbers clock = new AnalogClockWithNumbers();
        frame.add(clock);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Timer timer = new Timer(1000, e -> clock.repaint());
        timer.start();
    }
}