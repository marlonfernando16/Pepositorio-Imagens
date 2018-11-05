package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import fachada.Fachada;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAtualizarPerfil {

	private JFrame frame;
	private JTextField tf_nome;
	private JTextField tf_login;

	/**
	 * Launch the application.
	 */
	public static void Atualizar() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAtualizarPerfil window = new TelaAtualizarPerfil();
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
	public TelaAtualizarPerfil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Fachada.inicializar();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lb_nome = new JLabel("Nome ");
		lb_nome.setFont(new Font("Century", Font.BOLD, 12));
		lb_nome.setBounds(10, 81, 46, 14);
		frame.getContentPane().add(lb_nome);

		JLabel lb_user_nome = new JLabel("");
		lb_user_nome.setFont(new Font("Century", Font.BOLD, 12));
		lb_user_nome.setBounds(53, 76, 188, 24);
		frame.getContentPane().add(lb_user_nome);

		JLabel lb_login = new JLabel("Login");
		lb_login.setFont(new Font("Century", Font.BOLD, 12));
		lb_login.setBounds(10, 125, 46, 14);
		frame.getContentPane().add(lb_login);


		JLabel lb_user_login = new JLabel("");
		lb_user_login.setFont(new Font("Century", Font.BOLD, 12));
		lb_user_login.setBounds(53, 120, 183, 24);
		frame.getContentPane().add(lb_user_login);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tf_nome.setEnabled(true);
				tf_nome.setEditable(true);
				tf_login.setEnabled(true);
				tf_login.setEditable(true);;
			}
		});
		btnAtualizar.setBounds(279, 35, 89, 23);
		frame.getContentPane().add(btnAtualizar);

		tf_nome = new JTextField();
		tf_nome.setEnabled(false);
		tf_nome.setEditable(false);
		tf_nome.setBounds(249, 79, 175, 20);
		frame.getContentPane().add(tf_nome);
		tf_nome.setColumns(10);

		tf_login = new JTextField();
		tf_login.setEnabled(false);
		tf_login.setEditable(false);
		tf_login.setBounds(249, 123, 175, 20);
		frame.getContentPane().add(tf_login);
		tf_login.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
					String new_nome = tf_nome.getText();
					String new_login = tf_login.getText();
					String nome = Fachada.getLogado().getNome();
					System.out.println(new_nome.length());
				if(!new_nome.equals(""))
					Fachada.alterarNomeUsuario(nome, new_nome);
				if(!new_login.equals(""))
					Fachada.alteraLogin(new_nome, new_login);
				}
					 catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
			}
		});
		btnSalvar.setBounds(279, 173, 89, 23);
		frame.getContentPane().add(btnSalvar);
		

		lb_user_nome.setText(Fachada.getLogado().getNome());
		lb_user_login.setText(Fachada.getLogado().getLogin());
		
		Fachada.finalizar();
	}
}
