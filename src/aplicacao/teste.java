package aplicacao;

import java.util.ArrayList;
import java.util.List;

import fachada.Fachada;
import modelo.Imagem;
import modelo.Tema;
import modelo.Usuario;

public class teste {
	public static void main(String[] args)  {

		Fachada.inicializar();

		System.out.println("\n cadastrando,listando cada tema,e listando todos os temas cadastrados! \n");

//		     		Tema tema1 = Fachada.cadastrarTema("Paisagem");
//		    		Tema tema2 = Fachada.cadastrarTema("Aleatorio");
//		     		
//		     		System.out.println(tema1);
//		     		System.out.println(tema2);
		     		
		    		List<Tema> temas = Fachada.listarTemas();
		    		System.out.println(temas);

		//System.out.println(" \n cadastrando e printando os usuarios");

//					Usuario user = Fachada.cadastrarUsuario("tales1", "talesman", "megaranto");
//			        Usuario user2 = Fachada.cadastrarUsuario("marlon", "mfive", "mfive");
//			        System.out.println(user);
//				    System.out.println(user2);

		System.out.println(" \n listando usuarios \n");
		List<Usuario> users = Fachada.listarUsuarios();
		System.out.println(users);

		//			System.out.println("apagando imagem");
		////			try {
		////				Fachada.excluirImagem("marlonfi.jpg");
		////			} catch (Exception e) {
		////				// TODO Auto-generated catch block
		////				e.printStackTrace();
		////			}

		   		
//		System.out.println(" \n inserindo  o tema Aleatorio no usuario tales1 \n");
//		Fachada.inserirTema("tales1", "Aleatorio");
//		System.out.println(users);
		

////		System.out.println("Atualizando o usuario marlon para Marlon! \n");	
////	   try {
////			Fachada.alterarNomeUsuario("Marlon", "marlon");
////			System.out.println(users);
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//
//
		System.out.println("exibindo imagens com o tema Aleatorio \n");
		List<Imagem> imgs;
		try {
			imgs = Fachada.imagensPorTema("Aleatorio");
			System.out.println(imgs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//
//		
		System.out.println("exibindo a quantidade de  imagens com a extensão jpeg \n");		
		int qtd = Fachada.qtdFormatoImagem("jpg");
		System.out.println(qtd);
		

		System.out.println("exibindo tema com maior quantidade de imagens do usuário \n");
		String t = Fachada.TemaMaiorQtqImg();
		System.out.println(t);

		System.out.println("Exibindo os temas das imagens do usuario marlon \n");
		try {
			ArrayList<String> temasUser = Fachada.temaImagensDoUsuario("marlon","Aleatorio");
			System.out.println(temasUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Fachada.finalizar();		

	}
}
