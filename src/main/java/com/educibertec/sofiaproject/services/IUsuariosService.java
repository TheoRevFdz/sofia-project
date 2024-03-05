package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.Users;

public interface IUsuariosService {
	Users validarLogin (String username, String password);

	Users signUp(Users user) throws Exception;
}
