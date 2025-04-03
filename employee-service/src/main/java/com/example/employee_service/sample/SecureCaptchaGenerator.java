package com.example.employee_service.sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

public class SecureCaptchaGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 80;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        String captchaText = generateCaptchaText();
        BufferedImage captchaImage = generateCaptchaImage(captchaText);

        try {
            File outputFile = new File("secure_captcha.png");
            ImageIO.write(captchaImage, "png", outputFile);
            System.out.println("Secure CAPTCHA saved as " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving CAPTCHA: " + e.getMessage());
        }
    }

    private static String generateCaptchaText() {
        StringBuilder sb = new StringBuilder(CAPTCHA_LENGTH);
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    private static BufferedImage generateCaptchaImage(String captchaText) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // White background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw diagonal "testing" text
        drawDiagonalTestingText(g);

        // Apply random noise and lines
        addNoise(g);
        addRandomLines(g);

        // Draw CAPTCHA text with distortions
        drawCaptchaText(g, captchaText);

        g.dispose();
        return image;
    }

    private static void drawDiagonalTestingText(Graphics2D g) {
        g.setFont(new Font("Arial", Font.ITALIC, 30));
        g.setColor(new Color(120, 120, 120)); // Dark gray for background text 120 120 120

        AffineTransform originalTransform = g.getTransform();
        g.rotate(-Math.PI / 6); // Rotate the graphics context diagonally (-30 degrees)

        // Draw "testing" text in a diagonal pattern
        for (int x = -WIDTH; x < WIDTH * 1.5; x += 100) {
            for (int y = 0; y < HEIGHT * 2; y += 50) {
                g.drawString("testing", x, y);
            }
        }

        g.setTransform(originalTransform); // Reset transformation
    }

    private static void addNoise(Graphics2D g) {
        g.setColor(new Color(150, 150, 150, 100)); // Semi-transparent gray
        for (int i = 0; i < 500; i++) {
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);
            g.fillRect(x, y, 2, 2);
        }
    }

    private static void addRandomLines(Graphics2D g) {
        g.setColor(new Color(150, 150, 150, 180)); // Darker gray for interference lines
        for (int i = 0; i < 20; i++) {
            int x1 = RANDOM.nextInt(WIDTH);
            int y1 = RANDOM.nextInt(HEIGHT);
            int x2 = RANDOM.nextInt(WIDTH);
            int y2 = RANDOM.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    private static void drawCaptchaText(Graphics2D g, String text) {
        g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 35));
        int xOffset = 20;

        for (char c : text.toCharArray()) {
            g.setColor(new Color(RANDOM.nextInt(100), RANDOM.nextInt(100), RANDOM.nextInt(100))); // Random dark colors

            AffineTransform originalTransform = g.getTransform();
            double rotation = (RANDOM.nextDouble() - 0.5) * 0.4; // Random slight rotation
            g.rotate(rotation, xOffset + 10, HEIGHT / 2);

            g.drawString(String.valueOf(c), xOffset, HEIGHT / 2 + RANDOM.nextInt(10) - 5);
            g.setTransform(originalTransform); // Reset transform

            xOffset += 30;
        }
    }
}

