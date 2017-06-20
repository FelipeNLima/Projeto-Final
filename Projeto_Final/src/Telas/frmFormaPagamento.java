package Telas;

import Modelos.FormaDePagamento;
import javax.swing.JTable;

public class frmFormaPagamento extends javax.swing.JFrame {

    public JTable tabela;
    private boolean cadastrar;
    private int id;

    public frmFormaPagamento() {
        initComponents();
        cadastrar = true;
        setTitle("Cadastrar");
    }

    public frmFormaPagamento(int id) {
        initComponents();
        this.id = id;
        carregarDados();
        cadastrar = false;
        setTitle("Editar");
    }

    private void fechar() {
        setVisible(false);
        dispose();
    }

    private void carregarTabela() {
        frmFormaPagamentoPrincipal.carregarDados(tabela, "");
    }

    private void carregarDados() {
        FormaDePagamento f = new FormaDePagamento();
        f.carregarPorId(id);

        tbDescricao.setText(f.getDescricao());
    }

    private FormaDePagamento converteParaObj() {
        FormaDePagamento f = new FormaDePagamento();
        f.setId(id);
        f.setDescricao(tbDescricao.getText());
        f.setAtivo(true);

        return f;
    }

    private void mantemDados() {
        FormaDePagamento f = converteParaObj();

        if (cadastrar) {
            f.inserir();
        } else {
            f.atualizar();
        }
    }

    private boolean validar() {
        if (tbDescricao.getText().isEmpty()) {
            Validacoes.Mensagens.campoNaoPreenchido("Descrição");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tbDescricao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setType(java.awt.Window.Type.UTILITY);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/icons/Save_16x16.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setActionCommand("      Novo");
        btnSalvar.setMaximumSize(new java.awt.Dimension(95, 25));
        btnSalvar.setMinimumSize(new java.awt.Dimension(95, 25));
        btnSalvar.setPreferredSize(new java.awt.Dimension(95, 25));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/icons/Cancel_16x16.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setActionCommand("      Novo");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Descrição:");

        tbDescricao.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tbDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(tbDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (validar()) {
            mantemDados();
            carregarTabela();
            fechar();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmFormaPagamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnCancelar;
    protected javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tbDescricao;
    // End of variables declaration//GEN-END:variables
}
