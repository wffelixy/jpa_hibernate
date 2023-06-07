package br.com.crudhibernate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.crudhibernate.bean.ClienteJpaDAO;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteForm extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7963654248214680925L;
	private JTextField jTextFieldCpf;
    private JTextField jTextFieldID;
    private JTextField jTextFieldNome;
    private JTextField jTextFieldRg;

    public ClienteForm() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Formulário de Cliente");

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel jLabelCpf = new JLabel("CPF:");
        panel.add(jLabelCpf, gbc);

        jTextFieldCpf = new JTextField(20);
        gbc.gridx++;
        panel.add(jTextFieldCpf, gbc);

        JLabel jLabelID = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(jLabelID, gbc);

        jTextFieldID = new JTextField(10);
        gbc.gridx++;
        panel.add(jTextFieldID, gbc);

        JLabel jLabelNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(jLabelNome, gbc);

        jTextFieldNome = new JTextField(50);
        gbc.gridx++;
        panel.add(jTextFieldNome, gbc);

        JLabel jLabelRg = new JLabel("RG:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(jLabelRg, gbc);

        jTextFieldRg = new JTextField(20);
        gbc.gridx++;
        panel.add(jTextFieldRg, gbc);

        JButton jButtonSalvar = new JButton("Salvar");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(jButtonSalvar, gbc);

        JButton jButtonRemover = new JButton("Remover");
        gbc.gridx++;
        panel.add(jButtonRemover, gbc);

        JButton jButtonBuscar = new JButton("Buscar");
        gbc.gridx++;
        panel.add(jButtonBuscar, gbc);

        setContentPane(panel);
        pack();

        jButtonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
    }

    private void jButtonSalvarActionPerformed(ActionEvent evt) {
        Cliente cliente = new Cliente();
        cliente.setCpf(jTextFieldCpf.getText());
        cliente.setId(Integer.parseInt(jTextFieldID.getText()));
        cliente.setNome(jTextFieldNome.getText());
        cliente.setRg(jTextFieldRg.getText());
        ClienteJpaDAO.getInstance().merge(cliente);
        clearFields();
        JOptionPane.showMessageDialog(this, "Salvo com sucesso !");
    }

    private void jButtonRemoverActionPerformed(ActionEvent evt) {
        ClienteJpaDAO.getInstance().removeById(Integer.parseInt(jTextFieldID.getText()));
        clearFields();
        JOptionPane.showMessageDialog(this, "Removido com sucesso !");
    }

    private void jButtonBuscarActionPerformed(ActionEvent evt) {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do cliente"));
        Cliente cliente = ClienteJpaDAO.getInstance().getById(id);
        jTextFieldCpf.setText(cliente.getCpf());
        jTextFieldID.setText(String.valueOf(cliente.getId()));
        jTextFieldNome.setText(cliente.getNome());
        jTextFieldRg.setText(cliente.getRg());
        }
        		private void clearFields() {
            jTextFieldCpf.setText("");
            jTextFieldID.setText("");
            jTextFieldNome.setText("");
            jTextFieldRg.setText("");
        }

        public static void main(String[] args) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new ClienteForm().setVisible(true);
                }
            });
        }
    }
