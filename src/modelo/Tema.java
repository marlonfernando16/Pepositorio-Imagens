package modelo;

import java.util.ArrayList;

public class Tema {	
	String tema;
	private ArrayList<Imagem> imagensTema = new ArrayList<>();
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public ArrayList<Imagem> getImagens() {
		return imagensTema;
	}
	public void setImagem(Imagem imagem) {
		this.imagensTema.add(imagem);
	}
	@Override
	public String toString() {
		String texto =  "Tema [tema=" + tema + ", imagens= ";
		for (Imagem i : imagensTema) {
			texto+=" "+i.getNome()+", ";
		}
		texto+="]";
		return texto;
	}
}
