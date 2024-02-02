package me.arthurmeade12.comparer;
import javax.swing.*;
public class gui {
  public static void main() {
    // Creating instance of JFrame
    JFrame frame = new JFrame();
    // Creating instance of JButton
    JButton button = new JButton("Compare");
    // x axis, y axis, width, height
    button.setBounds(150, 200, 220, 50);
    // adding button in JFrame
    frame.add(button);
    // 400 width and 500 height
    frame.setSize(500, 600);
    // using no layout managers
    frame.setLayout(new CardLayout());
    // making the frame visible
    frame.setVisible(true);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
