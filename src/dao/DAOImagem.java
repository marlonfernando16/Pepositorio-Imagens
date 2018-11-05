
package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Imagem;
import modelo.Tema;


public class DAOImagem  extends DAO<Imagem>{

	public Imagem read (Object chave){
		int id = (Integer) chave;
		Query q = manager.query();
		q.constrain(Imagem.class);
		q.descend("id").constrain(id);
		List<Imagem> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	public Imagem readByNome (String nome){
		Query q = manager.query();
		q.constrain(Imagem.class);
		q.descend("nome").constrain(nome);
		List<Imagem> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	/* Consultas */
	
//	public List<Imagem> ConsultarImagensPorTema(String tema) {
//		Query q = manager.query();
//		q.constrain(Tema.class);
//		q.descend("tema").constrain(tema);
//		List<Imagem> imagens = q.execute();
//		if(imagens.size()>0)
//			return imagens;
//		else
//			return null;
//			
//	}
	
	public List<Imagem> ConsultarImagensPorTema(String tema) {
	Query q = manager.query();
	q.constrain(Imagem.class);
	q.descend("tema").descend("tema").constrain(tema);
	List<Imagem> imagens = q.execute();
	if(imagens.size()>0)
		return imagens;
	else
		return null;
		
}

}

