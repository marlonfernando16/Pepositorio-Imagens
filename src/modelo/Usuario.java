package modelo;

import java.util.ArrayList;

public class Usuario {

	private String nome;
	private String login;
	private String senha;
	//private ArrayList<Imagem> imagens;
    private ArrayList<Imagem> imagens = new ArrayList<>();
	public Usuario(String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public ArrayList<Imagem> getImagens() {
		return imagens;
	}
	public void adicionarImagem(Imagem imagem) {
		imagens.add(imagem);
	}
	
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", login=" + login + ", imagens=" + imagens + "]";
	}
	
}
