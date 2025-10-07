package employee.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {

    Splash() {
        // Load and scale the GIF image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.gif"));
        Image i2 = i1.getImage().getScaledInstance(1100, 650, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 1100, 650);
        add(imageLabel);

        // Frame properties
        setSize(1100, 650);
        setLocation(100, 50);
        setLayout(null);
         //setUndecorated(true); // Optional: remove window borders for splash screen
        setVisible(true);

        // Swing Timer to close splash after 5 seconds without freezing GUI
        Timer timer = new Timer(5000, e -> {
            setVisible(false);  // Hide splash
            dispose();          // Free resources
            new Home();         // Open Home screen (or new Login())
        });
        timer.setRepeats(false);  // Only run once
        timer.start();
    }

    public static void main(String[] args) {
        try {
            // Cross-platform look and feel
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start splash screen on Event Dispatch Thread
        SwingUtilities.invokeLater(Splash::new);
    }
}
