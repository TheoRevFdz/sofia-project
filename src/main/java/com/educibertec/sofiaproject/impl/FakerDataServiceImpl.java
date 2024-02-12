package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.*;
import com.educibertec.sofiaproject.repositories.*;
import com.educibertec.sofiaproject.services.IFakerDataService;
import com.educibertec.sofiaproject.util.MessageUtil;
import lombok.extern.log4j.Log4j2;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class FakerDataServiceImpl implements IFakerDataService {
    @Autowired
    private INumbersRepository numbersRepository;
    @Autowired
    private ITipoProductosRepository tipoProdRepository;
    @Autowired
    private IProveedoresRepository provRepository;
    @Autowired
    private IProductosRepository productosRepository;
    @Autowired
    private IClientesRepository clientesRepository;

    @Override
    public MessageUtil generateFakerData() {
        try {
            String message = "";
            boolean respGenCapNumb = generateCapsulaMumber();
            if (!respGenCapNumb) message = message.concat("Fallo en generateCapsulaMumber");
            boolean respTipoProd = generateTipoProductos();
            if (!respTipoProd) message = message.concat(" | generateTipoProductos");
            boolean respProv = generateProveedor();
            if (!respProv) message = message.concat(" | generateProveedor");
            boolean respProd = generateProductos();
            if (!respProd) message = message.concat(" | generateProductos");
            boolean respCli = generateClients();
            if (!respCli) message = message.concat(" | generateClients");

            final boolean isSuccess = respGenCapNumb && respTipoProd && respProv && respProd && respCli;

            if (isSuccess) {
                return MessageUtil.builder()
                        .message("Los datos de prueba fueron generados correctamente.")
                        .timestamp(new Date())
                        .status(HttpStatus.OK.value())
                        .build();
            }
            return MessageUtil.builder()
                    .message(message)
                    .timestamp(new Date())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        } catch (Exception e) {
            log.error("Error al generar los datos faker de prueba, detail: " + e.getMessage());
            throw e;
        }
    }

    private boolean generateCapsulaMumber() {
        long contNums = numbersRepository.count();
        if (contNums == 0) {
            List<CapsulaNumber> capsulasNumber = new ArrayList<>();
            CapsulaNumber capNum1 = CapsulaNumber.builder().prefij("VTA").numeracion(1).build();
            CapsulaNumber capNum2 = CapsulaNumber.builder().prefij("PDT").numeracion(11).build();
            capsulasNumber.add(capNum1);
            capsulasNumber.add(capNum2);
            numbersRepository.saveAll(capsulasNumber);
            log.trace("Las CapsulaNumber fueron generadas correctamente.");
            return true;
        }
        return false;
    }

    private boolean generateTipoProductos() {
        long countTipoProd = tipoProdRepository.count();
        if (countTipoProd == 0) {
            List<CapsulaTipoProducto> listTipoProd = new ArrayList<>();
            int cont = 0;
            while (cont < 10) {
                Faker faker = new Faker();
                CapsulaTipoProducto tipoProd = CapsulaTipoProducto.builder()
                        .descripcion(faker.name().name())
                        .build();
                listTipoProd.add(tipoProd);
                cont++;
            }
            tipoProdRepository.saveAll(listTipoProd);
            log.trace("Los CapsulaTipoProducto fueron generadas correctamente.");
            return true;
        }
        return false;
    }

    private boolean generateProveedor() {
        long contProv = provRepository.count();
        if (contProv == 0) {
            List<CapsulaProveedor> listProv = new ArrayList<>();
            CapsulaProveedor prov1 = CapsulaProveedor.builder()
                    .rucdni("20070321659")
                    .razonsocial("COMERCIAL LA GOLOCINA E.I.R.L.")
                    .celular("998521445")
                    .correo("ventas@lagolocina.com")
                    .estado(1)
                    .build();
            CapsulaProveedor prov2 = CapsulaProveedor.builder()
                    .rucdni("25544458225")
                    .razonsocial("CORDIAL CORPORACION DISTRIBUIDORA DE ALIMENTOS S.R.L.")
                    .celular("988589452")
                    .correo("ventas@cocodist.com.pe")
                    .estado(1)
                    .build();
            CapsulaProveedor prov3 = CapsulaProveedor.builder()
                    .rucdni("43322581")
                    .razonsocial("JULIO MARIO PEREZ MIRANDA")
                    .celular("999251255")
                    .correo("jperez@gmail.com")
                    .estado(1)
                    .build();
            listProv.add(prov1);
            listProv.add(prov2);
            listProv.add(prov3);
            provRepository.saveAll(listProv);
            log.trace("Los CapsulaProveedor fueron generadas correctamente.");
            return true;
        }
        return false;
    }

    private boolean generateProductos() {
        long contProd = productosRepository.count();
        if (contProd == 0) {
            CapsulaProducto prod1 = CapsulaProducto.builder()
                    .descripcion("COCA COLA 600 ML X UND")
                    .stock_max(50)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("1")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("1")).get())
                    .precio(2.5)
                    .estado(1)
                    .build();
            CapsulaProducto prod2 = CapsulaProducto.builder()
                    .descripcion("INKA KOLA 600 ML X UND")
                    .stock_max(50)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("1")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("1")).get())
                    .precio(2.5)
                    .estado(1)
                    .build();
            CapsulaProducto prod3 = CapsulaProducto.builder()
                    .descripcion("FANTA 500 ML X UND")
                    .stock_max(50)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("1")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("1")).get())
                    .precio(2.0)
                    .estado(1)
                    .build();
            CapsulaProducto prod4 = CapsulaProducto.builder()
                    .descripcion("SPRITE 500 ML X UND")
                    .stock_max(50)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("1")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("1")).get())
                    .precio(2.5)
                    .estado(1)
                    .build();
            CapsulaProducto prod5 = CapsulaProducto.builder()
                    .descripcion("FILETE DE ATUN GLORIA X UND")
                    .stock_max(24)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("2")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("4")).get())
                    .precio(4.5)
                    .estado(1)
                    .build();
            CapsulaProducto prod6 = CapsulaProducto.builder()
                    .descripcion("FILETE DE ATUN SAN FERNANDO X UND")
                    .stock_max(24)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("2")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("4")).get())
                    .precio(5.0)
                    .estado(1)
                    .build();
            CapsulaProducto prod7 = CapsulaProducto.builder()
                    .descripcion("ATUN EN TROZOS ALAMAR X UND")
                    .stock_max(24)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("2")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("4")).get())
                    .precio(4.8)
                    .estado(1)
                    .build();
            CapsulaProducto prod8 = CapsulaProducto.builder()
                    .descripcion("ATUN EN TROZOS PRIMOR X UND")
                    .stock_max(24)
                    .stock_min(6)
                    .proveedor(provRepository.findById(Long.parseLong("2")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("4")).get())
                    .precio(4.7)
                    .estado(1)
                    .build();
            CapsulaProducto prod9 = CapsulaProducto.builder()
                    .descripcion("ARROZ LA CACERITA AÑEJO X KILO")
                    .stock_max(50)
                    .stock_min(20)
                    .proveedor(provRepository.findById(Long.parseLong("3")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("4")).get())
                    .precio(4.2)
                    .estado(1)
                    .build();
            CapsulaProducto prod10 = CapsulaProducto.builder()
                    .descripcion("ARROZ COSTEÑO GRANEADITO X KILO")
                    .stock_max(50)
                    .stock_min(20)
                    .proveedor(provRepository.findById(Long.parseLong("3")).get())
                    .tipo(tipoProdRepository.findById(Long.parseLong("4")).get())
                    .precio(4.5)
                    .estado(1)
                    .build();
            List<CapsulaProducto> listProd = new ArrayList<>();
            listProd.add(prod1);
            listProd.add(prod2);
            listProd.add(prod3);
            listProd.add(prod4);
            listProd.add(prod5);
            listProd.add(prod6);
            listProd.add(prod7);
            listProd.add(prod8);
            listProd.add(prod9);
            listProd.add(prod10);
            productosRepository.saveAll(listProd);
            log.trace("Los CapsulaProducto fueron generadas correctamente.");
            return true;
        }
        return false;
    }

    private boolean generateClients() {
        long contClients = clientesRepository.count();
        if (contClients == 0) {
            CapsulaCliente cli1 = CapsulaCliente.builder()
                    .celular("990584475")
                    .direccion("LAS ALMENDRAS 136 - SURCO")
                    .razonsocial("JUAN JOSE MEDINA CACERES")
                    .rucdni("45525645")
                    .estado(1)
                    .build();
            CapsulaCliente cli2 = CapsulaCliente.builder()
                    .celular("990682222")
                    .direccion("VILLA ATAÑO 342 INT A - COMAS")
                    .razonsocial("BODEGA LUIS FELIPE")
                    .rucdni("20054212559")
                    .estado(1)
                    .build();
            List<CapsulaCliente> listClient = new ArrayList<>();
            listClient.add(cli1);
            listClient.add(cli2);
            clientesRepository.saveAll(listClient);
            log.trace("Los CapsulaCliente fueron generadas correctamente.");
            return true;
        }
        return false;
    }
}
