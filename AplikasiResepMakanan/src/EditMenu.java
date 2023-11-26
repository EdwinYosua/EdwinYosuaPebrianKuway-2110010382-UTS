import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditMenu extends JFrame implements ActionListener {

    JTextArea txtArea;
    JButton btnSave;
    JButton btnBack;
    JScrollPane scrollPane;


    EditMenu() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Edit Resep");
        this.setSize(500, 550);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        txtArea = new JTextArea();
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setFont(new Font("Arial",Font.PLAIN,20));

        scrollPane = new JScrollPane(txtArea);
        scrollPane.setPreferredSize(new Dimension(450,450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        btnSave = new JButton("SAVE");
        btnSave.addActionListener(this);

        btnBack = new JButton("BACK");
        btnBack.addActionListener(this);

        this.add(btnSave);
        this.add(btnBack);
        this.add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            saveFile(txtArea);
        }
        if(e.getSource() == btnBack) {
            new MainMenu();
            EditMenu.this.dispose();
        }
    }

    private void saveFile(JTextArea txtArea) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("D:\\MY FILE\\Documents\\KULIAH\\Semester 5\\PBO2\\AplikasiResepMakanan\\resepFile"));

        int response = fc.showSaveDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            File file;
            PrintWriter fileOut = null;

            file = new File(fc.getSelectedFile().getAbsolutePath());
            try {
                fileOut = new PrintWriter(file);
                fileOut.println(txtArea.getText());
            }catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }finally {
                fileOut.close();
            }
        }
    }

    private void readFile(String dir) {
        try (FileReader fr = new FileReader(dir);
             BufferedReader bfr = new BufferedReader(fr)
        ) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = bfr.readLine()) != null) {
                content.append(line).append("\n"); // Append each line to the StringBuilder
            }

            txtArea.setText(content.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getDirectory(String directory) {
        readFile(directory);
    }

}
