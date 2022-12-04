package utn.frba.huelladecarbono.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.apache.poi.ss.formula.functions.Areas;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import utn.frba.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;
import utn.frba.huelladecarbono.service.HandleBars;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

  //  private Handlebars handlebars = HandleBars.getHandleBars();
    @GetMapping({"/login", "/", "/index", "/login.html"})
    public String login() {return "login";}

    //Rutas de vistas de miembro

    @GetMapping({"/miembro/calcularHuella", "/miembro/calcularHuella.html"})
    public String calcularHuellaM() {
        return "MiembroCalculadora";
    }

    @GetMapping({"/miembro/datosPersonales", "/miembro/datos-personales.html"})
    public String datosPersonalesM(){return "MiembroDatosPersonales";}

    @GetMapping({"/miembro/organizaciones", "/miembro/organizaciones.html"})
    public String organizacionesM(){return "MiembroOrganizaciones";}

    @GetMapping({"/miembro/recorridos", "/miembro/recorridos.html"})
    public String recorridosM(){return "MiembroRecorridos";}

    //Rutas de vistas de organizacion
/*
    @GetMapping(value="/{idOrganizacion}/areas", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> areas(@PathVariable String idOrganizacion) throws Exception {

        Template template = handlebars.compile("/templates/OrgAreas");

        //despues borrar cuando funcione lo de los repos en memoria
        //usar ID de organizacion 1 y 2 para probar

        Organizacion organizacion1 = new Organizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
        Organizacion organizacion2 = new Organizacion("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false);
        Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
        Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false);
        Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
        ArrayList<Double> listaHCPrueba = new ArrayList<>();
        Area area1 = new Area("AreaPrueba1", organizacion1);
        Area area2 = new Area("AreaPrueba2", organizacion1);
        organizacion2.setArea(area1);
        listaHCPrueba.add(100.00);
        HuellaCarbono huellaPrueba = new HuellaCarbono(Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021), 250.00);
        List<HuellaCarbono> hashMapPrueba = new ArrayList<>();
        hashMapPrueba.add(huellaPrueba);
        organizacion1.setID(1);
        organizacion2.setID(2);
        RepositorioOrganizaciones.getRepositorio().getOrganizaciones().add(organizacion2);
        RepositorioOrganizaciones.getRepositorio().getOrganizaciones().add(organizacion1);

        //borrar arriba

        List<Area> areas = RepositorioOrganizaciones
                .getRepositorio()
                .findOrganizacion(Integer.parseInt(idOrganizacion))
                .getAreas();

        Map<String, Object> model = new HashMap<>();
        model.put("area", areas);
        model.put("organizacionID", idOrganizacion);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }*/

    @GetMapping({"/organizacion/calcularHuella", "/organizacion/calcular-huella.html"})
    public String calcularHuellaO(){return "OrgCalculadora";}

    @GetMapping({"/organizacion/contactos", "/organizacion/contactos.html"})
    public String contactosO(){return "OrgContactos";}

    @GetMapping({"/organizacion/datosDeActividad", "/organizacion/datos-de-actividad.html"})
    public String datosDeActividadO(){return "OrgDatosDeActividad";}

    @GetMapping({"/organizacion/datosInternos", "/organizacion/datos-internos.html"})
    public String datosInternosO(){return "OrgDatosInternos";}

    @GetMapping({"/organizacion/miembros", "/organizacion/miembros.html"})
    public String miembrosO(){return "OrgMiembros";}

    @GetMapping({"/organizacion/recomendaciones", "/organizacion/recomendaciones.html"})
    public String recomendacionesO(){return "OrgRecomendaciones";}

    @GetMapping({"/organizacion/recorridos", "/organizacion/recorridos.html"})
    public String recorridosO(){return "OrgRecorridos";}

    @GetMapping({"/organizacion/reportes", "/organizacion/reportes.html"})
    public String reportesO(){return "OrgReportes";}

    // rutas de vistas de Agente Sectorial

    @GetMapping({"/AS/reportes", "/AS/reportes.html"})
    public String reportesAS(){return "AgenteReportes";}

    @GetMapping({"/AS/recomendaciones", "/AS/recomendaciones.html"})
    public String recomendacionesAS(){return "AgenteRecomendaciones";}
}
