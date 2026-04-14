import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class sro extends JFrame {

    JTable table;
    DefaultTableModel model;

    JTextField nameField;
    JTextField ageField;
    JTextField groupField;

    JButton addBtn, deleteBtn, updateBtn;

    public sro() {

        setTitle("Student GUI System");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== TABLE =====
        String[] columns = {"Name","Age","Group"};

        model = new DefaultTableModel(columns,0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // ===== INPUT PANEL =====
        JPanel panel = new JPanel(new GridLayout(2,4,5,5));

        panel.add(new JLabel("Name"));
        panel.add(new JLabel("Age"));
        panel.add(new JLabel("Group"));
        panel.add(new JLabel(""));

        nameField = new JTextField();
        ageField = new JTextField();
        groupField = new JTextField();

        panel.add(nameField);
        panel.add(ageField);
        panel.add(groupField);

        addBtn = new JButton("Add");
        panel.add(addBtn);

        // ===== BUTTON PANEL =====
        JPanel btnPanel = new JPanel();

        deleteBtn = new JButton("Delete");
        updateBtn = new JButton("Update");

        btnPanel.add(deleteBtn);
        btnPanel.add(updateBtn);

        // ===== LAYOUT =====
        add(scrollPane,BorderLayout.CENTER);
        add(panel,BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);

        // ===== ADD STUDENT =====
        addBtn.addActionListener(e -> {

            String name = nameField.getText();
            String age = ageField.getText();
            String group = groupField.getText();

            if(name.isEmpty() || age.isEmpty() || group.isEmpty()){
                JOptionPane.showMessageDialog(this,"Fill all fields!");
                return;
            }

            model.addRow(new Object[]{name,age,group});

            nameField.setText("");
            ageField.setText("");
            groupField.setText("");
        });

        // ===== DELETE =====
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if(row >= 0)
                model.removeRow(row);
            else
                JOptionPane.showMessageDialog(this,"Select row!");
        });

        // ===== UPDATE =====
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if(row >= 0){
                model.setValueAt(nameField.getText(),row,0);
                model.setValueAt(ageField.getText(),row,1);
                model.setValueAt(groupField.getText(),row,2);
            }else{
                JOptionPane.showMessageDialog(this,"Select row!");
            }
        });

        // ===== TABLE CLICK =====
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();

                nameField.setText(model.getValueAt(row,0).toString());
                ageField.setText(model.getValueAt(row,1).toString());
                groupField.setText(model.getValueAt(row,2).toString());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new sro();
    }
}