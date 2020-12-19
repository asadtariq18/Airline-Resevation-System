import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App {

  public static void main(String[] args) {
    Admin admin = new Admin();
    User user = new User();
    loginWindow(admin, user);
  }

  public static void loginWindow(Admin admin, User user) {
    JFrame frame = new JFrame("Airline Reservation System");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(58, 58, 60));
    frame.add(panel);
    panel.setLayout(null);

    JLabel label = new JLabel("Airline Reservation System");
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.BOLD, 50));
    label.setBounds(400, 100, 840, 100);
    panel.add(label);

    label = new JLabel("Login");
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.BOLD, 35));
    label.setBounds(630, 250, 270, 100);
    panel.add(label);

    JButton b1 = new JButton("Admin");
    b1.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          try {
            admin.mainWindow();
            frame.dispose();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      }
    );
    b1.setBounds(460, 400, 200, 75);
    b1.setFont(new Font("Serif", Font.BOLD, 30));
    b1.setBackground(new java.awt.Color(238, 186, 76));
    b1.setForeground(new java.awt.Color(58, 58, 60));

    panel.add(b1);

    JButton b2 = new JButton("User");
    b2.setBackground(new java.awt.Color(169, 221, 217));
    b2.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          try {
            user.mainWindow();
            frame.dispose();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      }
    );
    b2.setBounds(700, 400, 200, 75);
    b2.setFont(new Font("Serif", Font.BOLD, 30));
    b2.setBackground(new java.awt.Color(238, 186, 76));
    b2.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(b2);

    frame.setVisible(true);
  }
}
