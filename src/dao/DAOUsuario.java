package dao;

import java.util.List;

import com.db4o.query.Query;
import modelo.Usuario;

public class DAOUsuario extends DAO<Usuario> {
	
	public Usuario login(String login,String senha) {
		
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("login").constrain(login);
		q.descend("senha").constrain(senha);
		
		List<Usuario> usuarios = q.execute();
		
		if(usuarios.size() > 0)
			return usuarios.get(0);
		else
			return null;
	}
	
	
		public  Usuario readByNome (String nome) {
			Query q = manager.query();
			q.constrain(Usuario.class);
			q.descend("nome").constrain(nome);
			List<Usuario> usuarios = q.execute();
			if (usuarios.size() > 0)
				return usuarios.get(0);
			return null;
		}
		
		public List<Usuario> listAll(){
			System.out.println("\nListagem dos Livros:");
			Query q = manager.query();
			q.constrain(Usuario.class);
			q.descend("nome").orderAscending();
			List<Usuario> usuarios = q.execute();
			if(usuarios.size()>0)
				return usuarios;
			else 
				return null;
		}
		
//		public static String temaImagensDoUsuario(String nome) {
//			Query q = manager.query();
//			q.constrain(Tema)
//		}
		
//		public static Usuario updateUsuario(Usuario user) {
//			
//		}
		
}
