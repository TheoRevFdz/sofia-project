package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.CapsulaProveedor;
import com.educibertec.sofiaproject.repositories.IProveedoresRepository;
import com.educibertec.sofiaproject.services.IProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresServiceImpl implements IProveedoresService {
    @Autowired
    IProveedoresRepository rp;

    @Override
    public List<CapsulaProveedor> listar() {
        return rp.findByEstado(1);
    }

    @Override
    public CapsulaProveedor buscarProveedor(Long codigo) {
        return rp.findById(codigo).orElse(null);
    }

    @Override
    public void eliminarProveedor(CapsulaProveedor obj) {
        CapsulaProveedor prov = rp.findById(obj.getIdproveedor()).orElse(null);
        prov.setEstado(0);
        rp.save(prov);
    }

    @Override
    public void modificarProveedor(CapsulaProveedor obj) {
        CapsulaProveedor prov = rp.findById(obj.getIdproveedor()).orElse(null);
        prov.setIdproveedor(obj.getIdproveedor());
        prov.setCelular(obj.getCelular());
        prov.setCorreo(obj.getCorreo());
        prov.setRazonsocial(obj.getRazonsocial());
        prov.setRucdni(obj.getRucdni());
        prov.setEstado(1);
        rp.save(prov);
    }

    @Override
    public void crearProveedor(CapsulaProveedor obj) {
        rp.save(obj);
    }
}
