package graphic;

import database.ConnectionFactory;
import database.dao.UsuarioDAO;
import database.model.UsuarioModel;
import util.HashUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginWindow extends JFrame {

    private JLabel lblTitulo, lblUsuario, lblSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public LoginWindow() {
        setTitle("Tela de Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        componentesCriar();
        setVisible(true);
    }

    private void componentesCriar() {

        lblTitulo = new JLabel("Sistema Vinhos");
        lblTitulo.setBounds(35, 20, 280, 30);
        lblTitulo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
        getContentPane().add(lblTitulo);

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(10, 70, 80, 30);
        getContentPane().add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(80, 70, 200, 30);
        getContentPane().add(txtUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 100, 80, 30);
        getContentPane().add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(80, 100, 200, 30);
        getContentPane().add(txtSenha);

        btnEntrar = new JButton(new AbstractAction("ENTRAR") {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = txtUsuario.getText();
                String senha = txtSenha.getText();

                if (usuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo <Usuário> obrigatório!");
                    txtUsuario.requestFocus();
                    return;
                }

                if (senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo <Senha> obrigatório!");
                    txtSenha.requestFocus();
                    return;
                }

                try {
                    Connection conexao = ConnectionFactory.getConnection("localhost", 5432, "controlevinhos", "postgres", "manager");
                    if (conexao != null) {

                        UsuarioModel usuarioModel = new UsuarioModel();
                        usuarioModel.setUsuario(usuario);
                        usuarioModel.setSenha(HashUtils.criarMD5(senha));

                        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
                        if (!usuarioDAO.selectByUsuarioESenha(usuarioModel)) {
                            JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos!");
                            return;
                        }

                        new MenuPrincipal().setVisible(true);
                        dispose();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Não foi possível conectar no banco de dados: "+ex.getMessage());
                }
            }
        });
        btnEntrar.setBounds(80, 130, 200, 30);
        getContentPane().add(btnEntrar);
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}