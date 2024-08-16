package edu.espe.contable.services;

import edu.espe.contable.dtos.CiudadVentasDTO;
import edu.espe.contable.dtos.ReporteVentasDTO;
import edu.espe.contable.entities.Articulo;
import edu.espe.contable.entities.FacturaDetalle;
import edu.espe.contable.repository.ArticuloRepository;
import edu.espe.contable.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<CiudadVentasDTO> obtenerTodasLasCiudadesConVentasTotales() {
        return ciudadRepository.findAll().stream()
                .map(ciudad -> new CiudadVentasDTO(ciudad.getCodigo() ,ciudad.getNombre(), ciudad.obtenerVentasTotales()))
                .collect(Collectors.toList());
    }

    public List<ReporteVentasDTO> generarReporteVentas() {
        List<Articulo> articulos = articuloRepository.findAll();

        return articulos.stream().map(articulo -> {
            Map<Long, BigDecimal> ventasPorCliente = articulo.getFacturaDetalles().stream()
                    .collect(Collectors.groupingBy(
                            detalle -> detalle.getFactura().getCliente().getId(),
                            Collectors.reducing(BigDecimal.ZERO,
                                    detalle -> detalle.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())),
                                    BigDecimal::add)
                    ));
            return new ReporteVentasDTO(articulo.getCodigo(), articulo.getNombre(), ventasPorCliente);
        }).collect(Collectors.toList());
    }
}
