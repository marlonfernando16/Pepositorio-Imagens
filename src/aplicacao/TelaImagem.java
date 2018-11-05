package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class TelaImagem {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void Imagem() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaImagem window = new TelaImagem();
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
	public TelaImagem() {
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Paisagem", "Pessoal", "Aleatorio", "Animais", "Memes", "Todos"}));
		comboBox.setBounds(175, 24, 112, 20);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("Excluir");
		button.setBounds(312, 227, 112, 23);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("Vizualiza\u00E7\u00E3o");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 55, 375, 161);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal tela = new TelaPrincipal();
				frame.dispose();
				tela.Principal();
			}
		});
		btnNewButton.setBounds(346, 11, 78, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar Tema");
		lblNewLabel_1.setFont(new Font("Century", Font.BOLD, 13));
		lblNewLabel_1.setBounds(23, 26, 131, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("Quantidade de imagens do filtro");
		label.setFont(new Font("Century", Font.BOLD, 13));
		label.setBounds(11, 230, 246, 20);
		frame.getContentPane().add(label);
	}
}
