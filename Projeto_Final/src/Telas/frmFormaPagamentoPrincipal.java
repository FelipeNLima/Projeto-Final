package Telas;

import Modelos.FormaDePagamento;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class frmFormaPagamentoPrincipal extends javax.swing.JFrame {

    public frmFormaPagamentoPrincipal() {
        initComponents();
        tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
        carregarDadosTabela();
    }

    public void carregarDadosTabela() {
        carregarDados(tabela, tbPesquisa.getText());
    }

    public static void carregarDados(JTable tabela, String descricao) {
        ArrayList<FormaDePagamento> lista = FormaDePagamento.filtrarPorDescricao(descricao);
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);

        for (FormaDePagamento f : lista) {
            model.addRow(new Object[]{
                f.getId(),
                f.getDescricao()
            });
        }
        tabela.setModel(model);
    }

    private void remover() {
        if (tabela.getSelectedRow() >= 0) {
            int op = Validacoes.Mensagens.mostrarDesejaRemover();

            if (op == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());

                FormaDePagamento f = new FormaDePagamento();
                f.carregarPorId(id);
                f.remover();
                carregarDadosTabela();
            }
        } else {
            Validacoes.Mensagens.linhaNaoSelecionada();
        }
    }

    private void cadastrar() {
        Telas.frmFormaPagamento fPag = new Telas.frmFormaPagamento();
        fPag.tabela = this.tabela;
        fPag.setVisible(true);
    }

    private void editar() {
        int linha = tabela.getSelectedRow();

        if (linha >= 0) {
            int id = Integer.parseInt(tabela.getValueAt(linha, 0).toString());

            Telas.frmFormaPagamento fPag = new Telas.frmFormaPagamento(id);
            fPag.tabela = tabela;
            fPag.setVisible(true);
        } else {
            Validacoes.Mensagens.linhaNaoSelecionada();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        tbPesquisa = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Forma de Pagamento");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar"));

        tbPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPesquisaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/icons/Add_16x16.png"))); // NOI18N
        btnNovo.setText("  Novo");
        btnNovo.setActionCommand("      Novo");
        btnNovo.setMaximumSize(new java.awt.Dimension(95, 25));
        btnNovo.setMinimumSize(new java.awt.Dimension(95, 25));
        btnNovo.setPreferredSize(new java.awt.Dimension(110, 25));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/icons/Edit_16x16.png"))); // NOI18N
        btnEditar.setText("   Editar");
        btnEditar.setMaximumSize(new java.awt.Dimension(95, 25));
        btnEditar.setMinimumSize(new java.awt.Dimension(95, 25));
        btnEditar.setPreferredSize(new java.awt.Dimension(95, 25));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setMinWidth(60);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabela.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPesquisaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            carregarDadosTabela();
        }
    }//GEN-LAST:event_tbPesquisaKeyPressed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        cadastrar();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tabelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            remover();
        }
    }//GEN-LAST:event_tabelaKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmFormaPagamentoPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnEditar;
    protected javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> jComboBox1;
    protected javax.swing.JPanel jPanel1;
    protected javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    public javax.swing.JTextField tbPesquisa;
    // End of variables declaration//GEN-END:variables
}
