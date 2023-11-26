import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainMenu extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton btnExit;
    private JTable table1;
    private JButton btnRefresh;
    private JButton btnEdit;
    private JButton btnNew;
    private JButton btnDel;
    private JScrollPane scrollPane;
    private final File file = new File("D:\\MY FILE\\Documents\\KULIAH\\Semester 5\\PBO2\\AplikasiResepMakanan\\resepFile");
    private final File[] files = file.listFiles();
    MainMenu() {
        this.setContentPane(mainPanel);
        this.setTitle("Aplikasi Resep Makanan");
        this.setSize(500, 550);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        btnExit.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnEdit.addActionListener(this);
        btnNew.addActionListener(this);
        btnDel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnExit) {
            System.exit(0);
        }

        if(e.getSource() == btnRefresh) {
            getFilesList();
        }

        if(e.getSource() == btnEdit) {
            File searchedFile = searchFilePath(getFilesName());
            if(searchedFile != null) {
                EditMenu edtMenu = new EditMenu();
                edtMenu.getDirectory(searchedFile.getAbsolutePath());
                System.out.println("File ditemukan di " + searchedFile.getAbsolutePath());
                MainMenu.this.dispose();
            } else {
                System.out.println("File tidak ditemukan");
            }
        }

        if(e.getSource() == btnNew) {
            new EditMenu();
            MainMenu.this.dispose();
        }

        if(e.getSource() == btnDel) {
            File fileToDelete = searchFilePath(getFilesName());
            if(fileToDelete != null) {
                fileToDelete.delete();
                System.out.println("File Berhasil Dihapus");
            }else{
                System.out.println("File Tidak Ditemukan");
            }

            MainMenu.this.dispose();
            new MainMenu();
        }
    }

    private void getFilesList() {
        DefaultTableModel model = (DefaultTableModel)table1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"File Names"});
        if(files != null) {
            for(File file : files) {
                model.addRow(new Object[]{file.getName()});
            }
        }
        table1.setDefaultEditor(Object.class, null);
    }

    private String getFilesName() {
        int selectedRow = table1.getSelectedRow();
        String selectedTxt;
        if(selectedRow != -1) {
            selectedTxt = (String)table1.getValueAt(selectedRow,0);
            return selectedTxt;
        }
        return null;
    }

    private File searchFilePath(String fileName) {
        if(file == null) {
            return null;
        }
        for(File file : files) {
            if(file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }
}
