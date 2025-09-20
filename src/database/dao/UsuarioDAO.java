package database.dao;

import database.model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection conexao;

    private String selectByUsuarioESenha = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
    private PreparedStatement pstSelectByUsuarioESenha;

    public UsuarioDAO(Connection conexao) throws SQLException {
        this.conexao = conexao;
        pstSelectByUsuarioESenha = this.conexao.prepareStatement(selectByUsuarioESenha);
    }

    public boolean selectByUsuarioESenha(UsuarioModel usuarioModel) throws SQLException {
        pstSelectByUsuarioESenha.setString(1, usuarioModel.getUsuario());
        pstSelectByUsuarioESenha.setString(2, usuarioModel.getSenha());
        ResultSet resultado = pstSelectByUsuarioESenha.executeQuery();
        return resultado != null && resultado.next();
    }

}
