package raven.application.form.other;

import raven.data.*;
import raven.application.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.*;

public class FormNonenve extends javax.swing.JPanel {

    private javax.swing.JLabel jLabel1;
    private JTable virusTable;
    private JScrollPane scrollPane;

    public FormNonenve() {
        initComponents();
        initTable();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        // FIX: Corrected the title of the page
        jLabel1.setText("Non-enveloped Viruses");

        scrollPane = new JScrollPane();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void initTable() {
        String[] columnNames = {"Name", "Scientific Name", "Discovered Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (VNE virus : Application.nonenveList) {
            model.addRow(new Object[]{virus.getName(), virus.getSciName(), virus.getDate()});
        }
        virusTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int columnIndex = 0; columnIndex < virusTable.getColumnCount(); columnIndex++) {
            virusTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        virusTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        // FIX: Directly get the virus from the list using the selected row index
                        VNE selectedVirus = Application.nonenveList.get(row);
                        Nonenve nonenveFrame = new Nonenve(selectedVirus);
                        nonenveFrame.setVisible(true);
                    }
                }
            }
        });

        virusTable.setRowHeight(50);
        virusTable.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        JTableHeader header = virusTable.getTableHeader();
        header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        scrollPane.setViewportView(virusTable);
    }
}