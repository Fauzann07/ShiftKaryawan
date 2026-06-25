package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * @author Adess
 */
public class SplashScreen extends JWindow {

    private int progressValue = 0;
    private final Timer progressTimer;
    private float pulseAlpha = 0.0f;
    private boolean pulseGrowing = true;

    public SplashScreen() {
        int width = 500;
        int height = 320;

        JPanel content = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                int w = getWidth();
                int h = getHeight();

                GradientPaint bgGradient = new GradientPaint(
                        0, 0, new Color(10, 60, 120),
                        w, h, new Color(30, 130, 220));
                g2.setPaint(bgGradient);
                g2.fill(new RoundRectangle2D.Float(0, 0, w, h, 20, 20));

                g2.setColor(new Color(255, 255, 255, 15));
                g2.fillOval(-60, -60, 250, 250);
                g2.fillOval(w - 120, h - 150, 200, 200);

                int glowAlpha = (int) (pulseAlpha * 30);
                g2.setColor(new Color(100, 200, 255, glowAlpha));
                g2.fillOval(w / 2 - 100, 30, 200, 200);

                g2.setFont(new Font("Segoe UI", Font.BOLD, 42));
                String enterprise = "Enterprise";
                FontMetrics fm = g2.getFontMetrics();
                int enterpriseWidth = fm.stringWidth(enterprise);

                g2.setFont(new Font("Verdana", Font.BOLD, 42));
                FontMetrics fmHr = g2.getFontMetrics();
                String hr = " HR";
                int hrWidth = fmHr.stringWidth(hr);

                int totalWidth = enterpriseWidth + hrWidth;
                int startX = (w - totalWidth) / 2;
                int titleY = 120;

                g2.setFont(new Font("Segoe UI", Font.BOLD, 42));
                g2.setColor(Color.WHITE);
                g2.drawString(enterprise, startX, titleY);

                // "HR" biru terang
                g2.setFont(new Font("Verdana", Font.BOLD, 42));
                g2.setColor(new Color(100, 180, 255));
                g2.drawString(hr, startX + enterpriseWidth, titleY);

                // Tagline
                g2.setFont(new Font("Segoe UI", Font.ITALIC, 13));
                g2.setColor(new Color(180, 220, 255));
                String tagline = "Employee Management System";
                int tagWidth = g2.getFontMetrics().stringWidth(tagline);
                g2.drawString(tagline, (w - tagWidth) / 2, titleY + 28);

                // Garis dekoratif
                g2.setColor(new Color(100, 180, 255, 80));
                g2.setStroke(new BasicStroke(2));
                int lineY = titleY + 45;
                g2.drawLine(w / 2 - 80, lineY, w / 2 + 80, lineY);

                // Progress bar background
                int barX = 60;
                int barY = h - 70;
                int barW = w - 120;
                int barH = 6;

                g2.setColor(new Color(255, 255, 255, 30));
                g2.fill(new RoundRectangle2D.Float(barX, barY, barW, barH, barH, barH));

                // Progress bar fill dengan gradient
                if (progressValue > 0) {
                    int fillW = (int) (barW * (progressValue / 100.0));
                    GradientPaint barGradient = new GradientPaint(
                            barX, barY, new Color(80, 200, 255),
                            barX + fillW, barY, new Color(150, 230, 255));
                    g2.setPaint(barGradient);
                    g2.fill(new RoundRectangle2D.Float(barX, barY, fillW, barH, barH, barH));

                    // Glow pada progress bar
                    g2.setColor(new Color(150, 230, 255, 60));
                    g2.fill(new RoundRectangle2D.Float(barX, barY - 2, fillW, barH + 4, barH + 4, barH + 4));
                }

                // Status text
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                g2.setColor(new Color(180, 220, 255));
                String statusText = getStatusText();
                int statusWidth = g2.getFontMetrics().stringWidth(statusText);
                g2.drawString(statusText, (w - statusWidth) / 2, barY + 25);

                // Version text
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                g2.setColor(new Color(130, 170, 210));
                String version = "v1.0 © 2026 EnterpriseHR";
                int verWidth = g2.getFontMetrics().stringWidth(version);
                g2.drawString(version, (w - verWidth) / 2, h - 15);

                g2.dispose();
            }
        };

        content.setPreferredSize(new Dimension(width, height));
        setContentPane(content);
        pack();
        setLocationRelativeTo(null);

        // Timer untuk animasi progress + pulse
        progressTimer = new Timer(30, (ActionEvent e) -> {
            // Update progress
            if (progressValue < 100) {
                progressValue += 1;
            }

            // Update pulse glow
            if (pulseGrowing) {
                pulseAlpha += 0.03f;
                if (pulseAlpha >= 1.0f) {
                    pulseAlpha = 1.0f;
                    pulseGrowing = false;
                }
            } else {
                pulseAlpha -= 0.03f;
                if (pulseAlpha <= 0.0f) {
                    pulseAlpha = 0.0f;
                    pulseGrowing = true;
                }
            }

            content.repaint();

            if (progressValue >= 100) {
                ((Timer) e.getSource()).stop();
                // Tunggu 300ms lagi lalu tutup
                Timer closeTimer = new Timer(300, (ActionEvent e2) -> {
                    dispose();
                    // Buka LoginFrame
                    SwingUtilities.invokeLater(() -> {
                        view.LoginFrame login = new view.LoginFrame();
                        login.setLocationRelativeTo(null);
                        login.setVisible(true);
                    });
                });
                closeTimer.setRepeats(false);
                closeTimer.start();
            }
        });
    }

    private String getStatusText() {
        if (progressValue < 25) {
            return "Menginisialisasi sistem...";
        } else if (progressValue < 50) {
            return "Menghubungkan ke database...";
        } else if (progressValue < 75) {
            return "Memuat komponen...";
        } else if (progressValue < 95) {
            return "Mempersiapkan antarmuka...";
        } else {
            return "Selesai!";
        }
    }

    public void showSplash() {
        setVisible(true);
        progressTimer.start();
    }
}
