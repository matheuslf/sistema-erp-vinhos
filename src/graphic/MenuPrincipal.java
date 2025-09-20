package graphic;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JDesktopPane desktopPane;

    public MenuPrincipal() {
        setTitle("Sistema para controle de Vinhos");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new DesktopComFundo("/Users/matheuslf/Documents/Programacao/Java/SistemaVinhoERP/vinhosfundo.jpg");
        setContentPane(desktopPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuSistema = new JMenu("Sistema");
            JMenuItem itemUsuario = new JMenuItem("Usuário");
        menuSistema.add(itemUsuario);

        JMenu menuCadastro = new JMenu("Cadastro");

            JMenuItem itemPaises = new JMenuItem("Países");
            JMenuItem itemRegioes = new JMenuItem("Regiões");
            JMenuItem itemViniculas = new JMenuItem("Viniculas");
            JMenuItem itemUvas = new JMenuItem("Uvas");
            JMenuItem itemVinho = new JMenuItem("Vinhos");

        menuCadastro.add(itemPaises);
        menuCadastro.add(itemRegioes);
        menuCadastro.add(itemViniculas);
        menuCadastro.add(itemUvas);
        menuCadastro.add(itemVinho);

        JMenu menuRelatorio = new JMenu("Relatório");
            JMenuItem itemRelatorioVenda = new JMenuItem("Relatório de Venda");
        menuRelatorio.add(itemRelatorioVenda);

        menuBar.add(menuSistema);
        menuBar.add(menuCadastro);
        menuBar.add(menuRelatorio);

        setJMenuBar(menuBar);
    }

    static class DesktopComFundo extends JDesktopPane {

        private Image imagemFundo;

        public DesktopComFundo(String caminhoImagem) {
            this.imagemFundo = new ImageIcon(caminhoImagem).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

    }

}
