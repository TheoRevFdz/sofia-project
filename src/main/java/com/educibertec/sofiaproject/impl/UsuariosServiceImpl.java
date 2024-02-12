package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.repositories.IUsuariosRepository;
import com.educibertec.sofiaproject.services.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServiceImpl implements IUsuariosService {
    @Autowired
    IUsuariosRepository ru;

    @Override
    public boolean validarLogin(String User, String Password) {
        int result = ru.findByUsuarioAndClave(User, Password).size();
        return result > 0;
    }

}
