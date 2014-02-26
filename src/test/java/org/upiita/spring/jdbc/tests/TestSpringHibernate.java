package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");
		//nos traemos el bean HibernateUsuarioDAO
		UsuarioDAO usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(3);
		usuario.setNombre("ismael");
		usuario.setEmail("ismael@email.com");
		usuario.setPassword("123");
		
		usuarioDAO.creaUsuario(usuario);
		
		usuario.setPassword("1234");
		usuarioDAO.creaUsuario(usuario);
		
		System.out.println("datos guardados");
		
		Usuario usuarioBD = usuarioDAO.buscaUsuarioPorId(3);
		System.out.println("usuario encontrado es:" + usuarioBD);
		
		//PROBANDO EL CRITERIO DE HIBERNATE
		Usuario usuarioCriterio = usuarioDAO.buscaPorEmailYPassword("ismael@email.com", "55555");
		System.out.println("usuario por email y password es:" + usuarioCriterio);
		
		//PRUEBA DEL CRITERIO LIKE
		List<Usuario> usuarios = usuarioDAO.buscaPorNombre("z");
		System.out.println("usuarios por nombre:" + usuarios);
	}

}






