package modelo;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

//import dao.IDInterface;

public class Imagem /*implements IDInterface */ {

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private String nome;
	private byte[] bytesimagem;
	Usuario usuario;
	private Tema tema;
	
	public Imagem(){}

	public Imagem(BufferedImage bf, String nome) {
		super();
		this.nome = nome;
		setBufferedImage(bf);
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		 this.tema = tema;
	}
   
	public BufferedImage getBufferedImage() throws Exception{
		try {
			InputStream in = new ByteArrayInputStream(this.bytesimagem);
			BufferedImage bf = ImageIO.read(in);
			return bf;
		} catch (IOException e) {
			throw new Exception("leitura de arquivo invalida");
		}

	}

	public void setBufferedImage(BufferedImage bf) {
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bf, "jpg", baos );		// tipo de gravacao
			baos.flush();
			baos.close();
			this.bytesimagem = baos.toByteArray();	
		
		}
		catch(Exception e ){
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Imagem [nome= " + nome + ", usuario=" + usuario.getNome() + ", tema=" + tema + "]";
	}


}

