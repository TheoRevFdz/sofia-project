package com.educibertec.sofiaproject.controllers;

import com.educibertec.sofiaproject.entity.Operacion;
import com.educibertec.sofiaproject.entity.Product;
import com.educibertec.sofiaproject.entity.PastillaProducto;
import com.educibertec.sofiaproject.services.INumbersService;
import com.educibertec.sofiaproject.services.IOperacionesService;
import com.educibertec.sofiaproject.services.IProductosService;
import com.educibertec.sofiaproject.services.IProveedoresService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class DirectorComprasController {
    List<PastillaProducto> pastilla = new ArrayList<PastillaProducto>();

    @Autowired
    private IProductosService SProducto;
    @Autowired
    private IOperacionesService SOperacion;
    @Autowired
    private IProveedoresService SProveedor;
    @Autowired
    private INumbersService SNumber;


    @GetMapping("listar_Ctrl_Reposicion")
    public ModelAndView listarCtrlReposicion() {
        ModelAndView mav = new ModelAndView("listarCtrlReposiciones");
        List<Product> paquete = SProducto.listar();
        mav.addObject("paqueteP", paquete);
        return mav;
    }

    @GetMapping("reponer_producto")
    public ModelAndView obtenerProducto(@RequestParam(name = "idpro") Long idprod) {
        ModelAndView mav = new ModelAndView("reponerProducto");
        mav.addObject("productoP", SProducto.buscarProducto(idprod));
        return mav;

    }

    @PostMapping("reponerproducto/actualizar")
    public String reponerProducto(@ModelAttribute(name = "producto") Product obj) {
        LocalDate date = LocalDate.now();
        Operacion opr = Operacion.builder()
                .proceso("REPO")
                .tipo(1)
                .producto(obj)
                .cantidad(obj.getStock_min())
                .preciocpa(obj.getPrecio())
                .fechacpa(date)
                .estado(1)
                .build();
        SOperacion.crearOperaciones(opr);
        return "redirect:/listar_Ctrl_Reposicion";
    }

    @GetMapping("comprasProductos")
    public ModelAndView comprarProducto() {
        ModelAndView mav = new ModelAndView("compraProductos");
        mav.addObject("productolist", SProducto.listar());
        mav.addObject("producto",  PastillaProducto.builder().build());
        mav.addObject("capsul", pastilla);
        return mav;
    }

    @PostMapping("comprasProductos/almacenar")
    public String almacenarProducto(@ModelAttribute(name = "capsula") PastillaProducto obj) {
        obj.setName(SProducto.buscarProducto(obj.getIdProducto()).getDescripcion());
        pastilla.add(obj);

        return "redirect:/comprasProductos";
    }

    @GetMapping("preguardadoCompraseliminar")
    public String eliminarpreguardado(@ModelAttribute(name = "idp") int obj) {
        Iterator<PastillaProducto> it = pastilla.iterator();
        while (it.hasNext()) {
            if (it.next().getIdProducto() == obj) {
                it.remove();
            }
        }
        return "redirect:/comprasProductos";
    }

    @PostMapping("comprasProductos/guardar")
    public String guardarProducto() {
        LocalDate date = LocalDate.now();
        List<Operacion> paquete = new ArrayList<>();
        Operacion ope;
        for (PastillaProducto cap : pastilla) {
            Product p = SProducto.buscarProducto(cap.getIdProducto());
            int cant = cap.getCantidad();
            ope = Operacion.builder()
                    .proceso("COM")
                    .tipo(2)
                    .producto(p)
                    .cantidad(cant)
                    .preciocpa(cap.getCosto())
                    .fechacpa(date)
                    .estado(1)
                    .build();
            paquete.add(ope);
        }
        pastilla = new ArrayList<>();
        SOperacion.crearMultiplesOperaciones(paquete);
        return "redirect:/listar_Ctrl_Reposicion";
    }

    @GetMapping("/reporteReposicion")
    public void exportarPDF(HttpServletResponse response) throws JRException, IOException {
        response.addHeader("Content-disposition", "inline: filename" + "vendedor.pdf");
        response.setContentType("application/pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(SProducto.exportReport("classpath:reports/report_reponer.jrxml"), outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
