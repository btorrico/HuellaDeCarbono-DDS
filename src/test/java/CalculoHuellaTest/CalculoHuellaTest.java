package CalculoHuellaTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Optional;

import utn.frba.huelladecarbono.model.MedioDeTransporte.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.*;
import CargaDatos.CargarDatos;

public class CalculoHuellaTest {

    @Test
    public void calcularHuellaMiembro() throws Exception {
        CargarDatos.cargarFE();
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Miembro miembro = CargarDatos.cargarMiembro1();

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaOrganizacion() throws Exception {
        CargarDatos.cargarFE();
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Organizacion organizacion = CargarDatos.cargarOrganizacion1();
        organizacion.addArea(CargarDatos.cargarArea1());

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(organizacion, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaArea() throws Exception {
        CargarDatos.cargarFE();
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        Area area = CargarDatos.cargarArea1();

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCArea(area, mesInicio, mesFin);

        Assert.assertEquals(Optional.of(1.1),huella);
    }

    //No calcula respeecto a nada porque esta vacío el repo de organizaciones
    @Test
    public void calcularHuellaSectorTerritorial() throws Exception{
        CargarDatos.cargarFE();
        Calendar mesInicio = Calendario.crearFecha(0, 2022);
        Calendar mesFin = Calendario.crearFecha(11, 2022);
        SectorTerritorial sector = CargarDatos.cargarSector1();

        Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCSectorTerritorial(sector, mesInicio, mesFin);
    
        Assert.assertEquals(Optional.of(1.1),huella);
    }

    @Test
    public void calcularHuellaPromedio() throws Exception{
        CargarDatos.cargarFE();
    }
}
