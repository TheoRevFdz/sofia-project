package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;

public interface IUsuariosService {
	CapsulaUsuario validarLogin (String username, String password);

	CapsulaUsuario signUp(CapsulaUsuario user) throws Exception;
}
