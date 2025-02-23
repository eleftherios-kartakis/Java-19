import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

public class AddNewCloseContofStPosCase extends JFrame {

  private JPanel contentPane;
  
  private JTextField textField;
  
  public static String AM3;

  /**
  * Launch the application.
  */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AddNewCloseContofStPosCase frame = new AddNewCloseContofStPosCase();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
  * Create the frame.
  */
  /*The university administration adds a new close contact
   *  of a staff member positive case.
   */
  public AddNewCloseContofStPosCase() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1280, 730);
    contentPane = new JPanel();
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    //This is where you insert the academic id of the close contact.
    textField = new JTextField();
    textField.setFont(new Font("Bookman Old Style", Font.PLAIN, 25));
    textField.setBackground(SystemColor.menu);
    textField.setBounds(295, 247, 657, 84);
    contentPane.add(textField);
    textField.setColumns(10);
    
    JLabel lblInsertIDofclosecontact = new JLabel("Please insert "
        + "the academic id of the close contact:");
    lblInsertIDofclosecontact.setFont(new Font("Bookman "
        + "Old Style", Font.PLAIN, 27));
    lblInsertIDofclosecontact.setBounds(37, 28, 722, 86);
    contentPane.add(lblInsertIDofclosecontact);
    //Creating and displaying the submit button.
    JButton btnSubmitButton = new JButton("Submit");
    btnSubmitButton.setFont(new Font("Bookman Old Style", Font.BOLD, 35));
    btnSubmitButton.addActionListener(new ActionListener() {
      /*Checking if the academic id is correct and updates the database
      in case it is.
      If the id is incorrect, a message is displayed at the screen
      to warn the user.*/
      public void actionPerformed(ActionEvent arg0) {
        int tempAm = 0;
        String result = " ";
        int pointer = 0;
        do {
          try {
            AM3 = textField.getText();
            tempAm = Integer.parseInt(AM3);
          } catch (Exception exc) {
            pointer = 1;
            JOptionPane.showMessageDialog(contentPane, 
                "You have inserted a wrong ID.");
          }
        } while (result != " ");
        result = UpdateTheRecords.checkIfAmExist(tempAm);
        if (pointer == 0) {
          if (result.equals("true")) {
            String mail = UpdateTheRecords.sendEmailToStudent(tempAm);
            UpdateTheRecords.changeCloseCont(tempAm, "true", "student");
            setVisible(true);
            JOptionPane.showMessageDialog(contentPane, "The student has "
                + "been added successfully as a close contact. "
                + "An email has been sent at: " + mail);
            setVisible(false);
            AnotherStCloseCont o = new AnotherStCloseCont();
            o.setVisible(true);
          } else if (result.equals("false")) {
            JOptionPane.showMessageDialog(contentPane, 
                "You have inserted a wrong ID.");
          }
        }
      }
    });

    btnSubmitButton.setBounds(999, 529, 215, 71);
    contentPane.add(btnSubmitButton);
    //Creating and displaying the back button.
    //The back button returns to the NewPosStCaseMenu class.
    JButton btnBackButton = new JButton("Back");
    btnBackButton.setFont(new Font("Bookman Old Style", Font.BOLD, 35));
    btnBackButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        NewPosStCaseMenu uniadmenu = new NewPosStCaseMenu();
        uniadmenu.setVisible(true);
      }
    });
    btnBackButton.setBounds(47, 529, 215, 71);
    contentPane.add(btnBackButton);

    JLabel lblID = new JLabel("ID");
    lblID.setFont(new Font("Bookman Old Style", Font.BOLD, 35));
    lblID.setBounds(167, 247, 95, 84);
    contentPane.add(lblID);
    //Creating and displaying all the images in labels and buttons. 
    JLabel lblImage10 = new JLabel("");
    Image img10 = new ImageIcon(this.getClass().getResource("/Pics/135464593_"
        + "1077636602678989_8400056473973577538_n.png")).getImage();
    lblImage10.setIcon(new ImageIcon(img10));
    lblImage10.setBounds(981, 0, 285, 233);
    contentPane.add(lblImage10);

    Image img4 = new ImageIcon(this.getClass().getResource(
        "/Pics/left-arrow (1).png")).getImage();
    btnBackButton.setIcon(new ImageIcon(img4));
    btnBackButton.setFont(new Font("Tahoma", Font.BOLD, 24));
    contentPane.add(btnBackButton);

    Image img3 = new ImageIcon(this.getClass().getResource(
        "/Pics/ok.png")).getImage();
    btnSubmitButton.setIcon(new ImageIcon(img3));
    btnSubmitButton.setFont(new Font("Tahoma", Font.BOLD, 24));
    contentPane.add(btnSubmitButton);

    JLabel lblImage12 = new JLabel("");
    Image img12 = new ImageIcon(this.getClass().getResource(
        "/Pics/lock.png")).getImage();
    lblImage12.setIcon(new ImageIcon(img12));
    lblImage12.setBounds(564, 387, 138, 141);
    contentPane.add(lblImage12);
  }
}
