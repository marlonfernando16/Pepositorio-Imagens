package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import fachada.Fachada;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void Principal() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lb_logado = new JLabel("");
		lb_logado.setFont(new Font("Century", Font.BOLD, 13));
		lb_logado.setBounds(142, 11, 203, 24);
		frame.getContentPane().add(lb_logado);
		lb_logado.setText("Bem Vindo, " + Fachada.getLogado().getNome() + "!");
		
		JButton btnImportar = new JButton("Importar/Exportar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaImportExport tela = new TelaImportExport();
				frame.dispose();
				tela.TelaImport();
			
			}
		});
		btnImportar.setBounds(142, 81, 137, 23);
		frame.getContentPane().add(btnImportar);
		
		JButton btnNewButton = new JButton("Sair");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(142, 162, 137, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnMinhasInagens = new JButton("Minhas imagens");
		btnMinhasInagens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaImagem tela = new TelaImagem();
				frame.dispose();
				tela.Imagem();
				
			}
		});
		btnMinhasInagens.setBounds(142, 107, 137, 24);
		frame.getContentPane().add(btnMinhasInagens);
		
		JButton btnAtualizarPerfil = new JButton("Atualizar Perfil");
		btnAtualizarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAtualizarPerfil tela = new TelaAtualizarPerfil();
				frame.dispose();
				tela.Atualizar();
			}
		});
		btnAtualizarPerfil.setBounds(142, 135, 137, 23);
		frame.getContentPane().add(btnAtualizarPerfil);
	}
}
