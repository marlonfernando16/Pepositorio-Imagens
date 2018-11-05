package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import fachada.Fachada;
import modelo.Usuario;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class TelaLogin {

	private JFrame frame;
	private JTextField tf_login;
	private JTextField tf_senha;
	private JPasswordField tf_senha2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
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
	public TelaLogin() {
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

		JButton btnLogin = new JButton("Login");

		btnLogin.setFont(new Font("Century", Font.BOLD, 13));
		btnLogin.setBounds(203, 181, 89, 23);
		frame.getContentPane().add(btnLogin);
	

		tf_login = new JTextField();
		tf_login.setBounds(179, 76, 136, 20);
		frame.getContentPane().add(tf_login);
		tf_login.setColumns(10);
		
		tf_senha = new JPasswordField();
		tf_senha.setBounds(179, 125, 136, 20);
		frame.getContentPane().add(tf_senha);

		JLabel lb_login = new JLabel("User");
		lb_login.setFont(new Font("Century", Font.BOLD, 13));
		lb_login.setBounds(123, 78, 46, 14);
		frame.getContentPane().add(lb_login);

		JLabel lb_senha = new JLabel("Password");
		lb_senha.setFont(new Font("Century", Font.BOLD, 13));
		lb_senha.setBounds(92, 127, 65, 14);
		frame.getContentPane().add(lb_senha);

		JLabel lb_invalido = new JLabel("");
		lb_invalido.setBounds(123, 221, 225, 29);
		frame.getContentPane().add(lb_invalido);
		
	
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					
					String login = tf_login.getText();
					String senha = tf_senha.getText();
					Fachada.inicializar();
					Usuario usuario = Fachada.validaLogin(login, senha);
					if(usuario != null) {
						TelaPrincipal tela = new TelaPrincipal();
						frame.dispose();
						tela.Principal();
						Fachada.finalizar();
					} 		
				} catch (Exception e) {
					lb_invalido.setText(e.getMessage());
				}

			}
		});
	}
}
