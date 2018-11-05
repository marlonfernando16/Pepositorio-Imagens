package aplicacao;


import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

import fachada.Fachada;
import modelo.Imagem;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class TelaImportExport {

	private JFrame frmPrincipal;
	private JLabel lbl1;
	private JLabel lblArquivo;
	private JTextField textFieldArq;
	private JButton btnExportar;
	private JLabel label;
	private JTextField textSaidajpg;
	private JLabel lblmensagem;
	private Imagem imagemAtual;
	String path;

	/**
	 * Launch the application.
	 */
	public static void TelaImport() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaImportExport window = new TelaImportExport();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaImportExport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Fachada.inicializar();
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Gravar e ler imagens do banco");
		frmPrincipal.setBounds(100, 100, 605, 298);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);


		lbl1 = new JLabel("visualização");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setSize(300, 151);
		lbl1.setLocation(261, 47);
		frmPrincipal.getContentPane().add(lbl1);

		JButton btnGravar = new JButton("Importar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					JFileChooser fc = new JFileChooser(new File("C:"));
					FileFilter ft = new FileFilter() {
						@Override
						public boolean accept(File f) {	
							String file = f.getName();
							return file.endsWith(".jpg") | f.isDirectory();          
						}
						@Override
						public String getDescription() {
							return "teste";
						}
					};
					fc.setFileFilter(ft);
					fc.showOpenDialog(null);
					File f = fc.getSelectedFile();
					path = fc.getSelectedFile().getAbsolutePath();
					textFieldArq.setText(f.getName());
					textSaidajpg.setText("");
					lblmensagem.setText("");

					BufferedImage bf = ImageIO.read(f);
					imagemAtual = Fachada.cadastrarImagemUsuario(bf, f.getName(),Fachada.getLogado().getNome());
					lblmensagem.setText("importado para bd");
					
					
					
					ImageIcon icon = new  ImageIcon(bf.getScaledInstance(300, 200,Image.SCALE_DEFAULT));
					lbl1.setIcon(icon);

				}catch(Exception e){
					lblmensagem.setText(e.getMessage());
				}
			}
		});
		btnGravar.setBounds(10, 22, 75, 23);
		frmPrincipal.getContentPane().add(btnGravar);

		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try{
					
					String nome = textFieldArq.getText();
					Imagem imagem = Fachada.obterImagem(nome);
					imagemAtual=null;
					if(imagem==null)
						throw new Exception("inexistente");
					BufferedImage bf = imagem.getBufferedImage();
					ImageIcon icon = new  ImageIcon(bf.getScaledInstance(300, 200,Image.SCALE_DEFAULT));
					lbl1.setIcon(icon);  //mostrar imagemAtual
					imagemAtual = imagem;
					textFieldArq.setText("");
				textSaidajpg.setText(imagem.getNome());
				lblmensagem.setText("");
			}catch(Exception ec){
				lblmensagem.setText(ec.getMessage());
				lbl1.setIcon(null);
				}
			}
		});
		btnVisualizar.setBounds(352, 11, 94, 23);
		frmPrincipal.getContentPane().add(btnVisualizar);

		lblArquivo = new JLabel("arquivo");
		lblArquivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArquivo.setBounds(95, 28, 46, 14);
		frmPrincipal.getContentPane().add(lblArquivo);

		textFieldArq = new JTextField();
		textFieldArq.setEditable(false);
		textFieldArq.setColumns(10);
		textFieldArq.setBounds(151, 23, 75, 20);
		frmPrincipal.getContentPane().add(textFieldArq);

		btnExportar = new JButton("exportar");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textSaidajpg.getText().isEmpty())
						throw new Exception("arquivo vazio");
					if(imagemAtual==null)
						throw new Exception("nao ha imagemAtual");
					ImageIO.write(imagemAtual.getBufferedImage(), "jpg", new File(textSaidajpg.getText()) );
					lblmensagem.setText("exportado para arquivo");
				} catch (Exception e1) {
					lblmensagem.setText(e1.getMessage());
				}	
			}
		});
		btnExportar.setBounds(10, 163, 75, 23);
		frmPrincipal.getContentPane().add(btnExportar);

		label = new JLabel("arquivo");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(97, 167, 46, 14);
		frmPrincipal.getContentPane().add(label);

		textSaidajpg = new JTextField();
		textSaidajpg.setColumns(10);
		textSaidajpg.setBounds(153, 164, 86, 20);
		frmPrincipal.getContentPane().add(textSaidajpg);

		lblmensagem = new JLabel("");
		lblmensagem.setBounds(10, 234, 246, 14);
		frmPrincipal.getContentPane().add(lblmensagem);
		
		JComboBox cb_tema = new JComboBox();
		cb_tema.setModel(new DefaultComboBoxModel(new String[] {"Paisagem", "Pessoal", "Aleatorio", "Animais", "Memes", "Todos"}));
		cb_tema.setBounds(125, 66, 101, 20);
		frmPrincipal.getContentPane().add(cb_tema);
		
		cb_tema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tema = cb_tema.getSelectedItem().toString();
				if(!lblmensagem.getText().equals(""))
					Fachada.inserirTema(Fachada.getLogado().getNome(),tema);
				}
		});
		
		
		
		JLabel lblEscolherTema = new JLabel("Escolher Tema");
		lblEscolherTema.setFont(new Font("Century", Font.BOLD, 13));
		lblEscolherTema.setBounds(10, 65, 116, 20);
		frmPrincipal.getContentPane().add(lblEscolherTema);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(512, 225, 49, 23);
		frmPrincipal.getContentPane().add(label_2);
		//Fachada.finalizar();
	}
}
