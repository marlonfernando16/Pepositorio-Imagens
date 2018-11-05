package fachada;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import dao.DAO;
import dao.DAOImagem;
import dao.DAOTema;
import dao.DAOUsuario;
import modelo.Imagem;
import modelo.Tema;
import modelo.Usuario;

public class Fachada {
	private static DAOImagem daoimagem = new DAOImagem();
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOTema daotema = new DAOTema();
	private static Usuario logado;

	public static Usuario getLogado() {
		return logado;
	}

	public static void setLogado(Usuario logado) {
		Fachada.logado = logado;
	}

	public static void inicializar(){
		DAO.open();
	}

	public static void finalizar(){
		DAO.close();
	}


	public static Imagem cadastrarImagemUsuario(BufferedImage bf, String nome, String usuario) throws  Exception{
		DAO.begin();
		Usuario user = daousuario.readByNome(usuario);
		for(Imagem img: user.getImagens()) {
			if(img.getNome().equals(nome))
				throw new Exception("nome da imagem ja existe");
		 }
		
		Imagem img_new = new Imagem(bf, nome);
		daoimagem.create(img_new);
	    //user = daousuario.readByNome(usuario);
		user.adicionarImagem(img_new);
		user=daousuario.update(user);
		img_new.setUsuario(user);
		daoimagem.update(img_new);
		System.out.println(user);
		DAO.commit();
		return img_new;
	}
	
	public static Imagem obterImagem(int id) throws  Exception{
		return daoimagem.read(id);
	}

	public static Imagem obterImagem(String nome) throws  Exception{
		return daoimagem.readByNome(nome);
	}
     
	public static ArrayList<Imagem> listarImagens(){
		ArrayList<Imagem> result = new ArrayList<>();
		List<Imagem> imagens = daoimagem.readAll();
		for (Imagem img : imagens) {
		    result.add(img);
		}
		return result;
	}
	
	public static void excluirImagem(String nome) 
			throws  Exception{
		DAO.begin();
		Imagem img = daoimagem.readByNome(nome);
		if (img==null) {
			throw new Exception("imagem inexistente");
		}	
		daoimagem.delete(img);
		DAO.commit();
	}

	public static Usuario validaLogin(String user, String senha) throws Exception{
		Usuario usuario = daousuario.login(user,senha);
		if(usuario==null) 
			throw new Exception("Login ou senha incorretos!");
		 else {
			 logado = usuario;
			 return usuario;
		 }
			
	}

	public static Usuario cadastrarUsuario(String nome,String login,String senha) {
		DAO.begin();
		Usuario usuario = daousuario.readByNome(nome);
		if(usuario == null ) {
			Usuario new_user = new Usuario(nome,login,senha);
			daousuario.create(new_user);
			DAO.commit();
			return new_user;
		}
		
		return null;
	}
	
	public static Tema cadastrarTema(String tema){
		DAO.begin();
		Tema t = daotema.readByNome(tema);
		if (t == null ) {
			Tema new_tema = new Tema();
			new_tema.setTema(tema);
			daotema.create(new_tema);
			DAO.commit();
			return new_tema;
		}
		
		return null;
	}
	
	public static List<Usuario> listarUsuarios(){
		List<Usuario> users = daousuario.readAll();
		return users;
	}
	
	public static List<Tema> listarTemas(){
		List<Tema> tema = daotema.readAll();
		return tema;
	}
	
	public static void alterarNomeUsuario(String nome, String novonome) throws Exception{
		Fachada.inicializar();
		DAO.begin();		
		Usuario user = daousuario.readByNome(nome);	
		if (user==null)
			throw new Exception("alterar nome - nome inexistente:" + nome);
		user.setNome(novonome); 
		Fachada.setLogado(user);
		user = daousuario.update(user); 
		DAO.commit();	
	}
	
	public static void alteraLogin(String nome, String new_login) throws Exception{
		Fachada.inicializar();
		DAO.begin();		
		Usuario user = daousuario.readByNome(nome);	
		if (user==null)
			throw new Exception("Alterar login - nome inexistente:" + nome);

		user.setLogin(new_login);
		Fachada.setLogado(user);
		user = daousuario.update(user);     	
		DAO.commit();	
		
	}
	
	public static void inserirTema(String nome,String new_tema) {
		Fachada.inicializar();
		DAO.begin();
		Usuario user = daousuario.readByNome(nome);
		Tema  tema = daotema.readByNome(new_tema);
		if(user!=null && tema!=null) {
		int tamanho = user.getImagens().size();
		tema.setImagem(user.getImagens().get(tamanho-1));
		user.getImagens().get(tamanho-1).setTema(tema);
	    daotema.update(tema);
		user = daousuario.update(user); 
		DAO.commit();
		}
	 }
	
	/**********************************************************
	 * 
	 * CONSULTAS 
	 * @throws Exception 
	 * 
	 **********************************************************/
	
	public static List<Imagem> imagensPorTema(String tema) throws Exception {
		DAO.begin();
		List<Imagem> result = new ArrayList<>();
		result = daoimagem.ConsultarImagensPorTema(tema);
		
		if(result == null)
			throw new Exception("nenhuma imagem para esse tema");
		return result;
	}
	
	public static int qtdFormatoImagem(String formato) {
		int qtd = 0;
		 List<Imagem> imagens = daoimagem.readAll();
		 for(Imagem i: imagens) {
			 if(i.getNome().contains(formato))
				 qtd++;
		 }
		 
		return qtd;
		
		
	}
	
	public static String TemaMaiorQtqImg() {
		DAO.begin();
		int count = 0;
		Tema tema = new Tema();
		List<Tema> temas = daotema.readAll();
		for(Tema t: temas) {
			   if(t.getImagens().size()>count)
				   count = t.getImagens().size();
			      tema = t;
			}
		  
			return tema.getTema();
		}
	
		

		public static ArrayList<String> temaImagensDoUsuario(String usuario,String tema)throws Exception {
			Usuario user = daousuario.readByNome(usuario);
			if(user == null)
				throw new Exception("Usuario inexistente");
			List<Tema> temas = daotema.temaImagensDoUsuario(tema);
			if(temas == null)
				throw new Exception("Tema inexistente!");
			ArrayList<String> nomes = new ArrayList<>();
			 for(Tema t: temas)
				 nomes.add(t.getTema());  
			 return nomes;
		}
		
	}
	
	
	
	
	
	


