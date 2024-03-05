package com.educibertec.sofiaproject.controllers;

import com.educibertec.sofiaproject.entity.Customer;
import com.educibertec.sofiaproject.entity.Product;
import com.educibertec.sofiaproject.entity.Proveedor;
import com.educibertec.sofiaproject.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class DirectorCrudsController {
    @Autowired
    private IProductosService SProducto;
    @Autowired
    private ITipoProductosService STipo;
    @Autowired
    private IProveedoresService SProveedor;
    @Autowired
    private INumbersService SNumber;
    @Autowired
    private IClientesService SCliente;


    @GetMapping("crud_Productos")
    public ModelAndView crudListProductos() {
        ModelAndView mav = new ModelAndView("crudProductos");
        final List<Product> productos = SProducto.listar();
        mav.addObject("paqueteP", productos);
        return mav;
    }

    @GetMapping("crud_Clientes")
    public ModelAndView crudListClientes() {
        ModelAndView mav = new ModelAndView("crudClientes");
        mav.addObject("paqueteC", SCliente.listar());
        return mav;
    }

    @GetMapping("crud_Proveedores")
    public ModelAndView crudListProveedores() {
        ModelAndView mav = new ModelAndView("crudProveedores");
        mav.addObject("paquetePv", SProveedor.listar());
        return mav;
    }

    @GetMapping("crud_eliminar_Producto")
    public ModelAndView crudEliminarProducto(@RequestParam(name = "idprod") Long idprod) {
        ModelAndView mav = new ModelAndView("crudProductos");
        Product p = SProducto.buscarProducto(idprod);
        SProducto.eliminarProducto(p);
        mav.addObject("paqueteP", SProducto.listar());
        return mav;
    }

    @GetMapping("crud_eliminar_Cliente")
    public ModelAndView crudEliminarCliente(@RequestParam(name = "idcli") Long idcli) {
        ModelAndView mav = new ModelAndView("crudClientes");
        Customer c = SCliente.buscarCliente(idcli);
        SCliente.eliminarCliente(c);
        mav.addObject("paqueteC", SCliente.listar());
        return mav;
    }

    @GetMapping("crud_eliminar_Proveedor")
    public ModelAndView crudEliminarProveedor(@RequestParam(name = "idpro") Long idpro) {
        ModelAndView mav = new ModelAndView("crudProveedores");
        Proveedor pv = SProveedor.buscarProveedor(idpro);
        SProveedor.eliminarProveedor(pv);
        mav.addObject("paquetePv", SProveedor.listar());
        return mav;
    }


    @GetMapping("crud_invocar_edit_Producto")
    public ModelAndView crudInvocarEditProducto(@RequestParam(name = "idprod") Long idprod) {
        ModelAndView mav = new ModelAndView("crudEditProducto");
        Product p = SProducto.buscarProducto(idprod);

        mav.addObject("PaqueteP", p);
        mav.addObject("paquetetp", STipo.listar());
        mav.addObject("PaquetePv", SProveedor.listar());
        return mav;
    }

    @GetMapping("crud_invocar_edit_Cliente")
    public ModelAndView crudInvocarEditCliente(@RequestParam(name = "idcli") Long idcli) {
        ModelAndView mav = new ModelAndView("crudEditCliente");
        mav.addObject("PaqueteC", SCliente.buscarCliente(idcli));
        return mav;
    }

    @GetMapping("crud_invocar_edit_Proveedor")
    public ModelAndView crudInvocarEditProveedor(@RequestParam(name = "idpro") Long idpro) {
        ModelAndView mav = new ModelAndView("crudEditProveedor");
        mav.addObject("PaquetePv", SProveedor.buscarProveedor(idpro));
        return mav;
    }

    @PostMapping("/crud_ejecutar_edit_Producto")
    public String crudEjecutarEditProducto(@ModelAttribute(name = "producto") Product objProducto) {
        SProducto.modificarProducto(objProducto);
        return "redirect:/crud_Productos";
    }

    @PostMapping("/crud_ejecutar_edit_Cliente")
    public String crudEjecutarEditCliente(@ModelAttribute(name = "paqueteC") Customer obj2) {
        SCliente.modificarCliente(obj2);
        return "redirect:/crud_Clientes";
    }

    @PostMapping("/crud_ejecutar_edit_Proveedor")
    public String crudEjecutarEditProveedor(@ModelAttribute(name = "paquetePv") Proveedor obj) {
        SProveedor.modificarProveedor(obj);
        return "redirect:/crud_Proveedores";
    }


    @GetMapping("crud_Crear_Producto")
    public ModelAndView crudCrearProducto() {
        ModelAndView mav = new ModelAndView("crudNewProducto");
        mav.addObject("PaqueteP", new Product());
        mav.addObject("paquetetp", STipo.listar());
        mav.addObject("PaquetePv", SProveedor.listar());
        return mav;
    }

    @GetMapping("crud_Crear_Cliente")
    public ModelAndView crudCrearCliente() {
        ModelAndView mav = new ModelAndView("crudNewCliente");
        mav.addObject("PaqueteC", new Customer());
        return mav;
    }

    @GetMapping("crud_Crear_Proveedor")
    public ModelAndView crudCrearProveedor() {
        ModelAndView mav = new ModelAndView("crudNewProveedor");
        mav.addObject("PaquetePv", new Proveedor());
        return mav;
    }


    @PostMapping("/crud_ejecutar_crear_producto")
    public String crusEjecutarCrearProducto(@ModelAttribute(name = "PaqueteP") Product obj) {
        obj.setIdprod(null);
        obj.setEstado(1);
        SProducto.crearProducto(obj);

        return "redirect:/crud_Productos";
    }

    @PostMapping("/crud_ejecutar_crear_cliente")
    public String crusEjecutarCrearCliente(@Valid @ModelAttribute(name = "PaqueteC") Customer obj, BindingResult result) {
        if (result.hasErrors()) {
            return "crudNewCliente";
        }


        obj.setEstado(1);
        SCliente.crearCliente(obj);

        return "redirect:/crud_Clientes";
    }

    @PostMapping("/crud_ejecutar_crear_proveedor")
    public String crusEjecutarCrearProveedor(@ModelAttribute(name = "PaquetePv") Proveedor obj) {
        obj.setEstado(1);
        SProveedor.crearProveedor(obj);

        return "redirect:/crud_Proveedores";
    }
}
