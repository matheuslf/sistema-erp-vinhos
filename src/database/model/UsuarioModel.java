package database.model;

public class UsuarioModel {

    private Long id;
    private String usuario;
    private String senha;
    private String perfil;

    public UsuarioModel() {
    }

    public UsuarioModel(Long id, String usuario, String senha, String perfil) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
