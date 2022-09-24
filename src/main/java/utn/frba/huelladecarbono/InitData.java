package utn.frba.huelladecarbono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.OrganizacionController;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Distancia;
import utn.frba.huelladecarbono.model.Creador.*;
import utn.frba.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoTransportePublico;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.*;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    OrganizacionRepository repoOrganizacion;

    @Autowired
    RepositorioOrganizaciones repositorioOrganizaciones;

    @Autowired
    OrganizacionController organizacionController;

    //Usuarios
    @Autowired
    UsuarioRepository repoUsuario;

    @Autowired
    RecorridoRepository repoRecorrido;
    

    //Paradas
    @Autowired
    ParadaRepository repoParadas;

    //Transporte

    @Autowired
    TransportePublicoRepository repoTransportes;

    @Autowired
    MiembroRepository repoMiembros;


    @Autowired
    RepositoryRestConfiguration config;

    @Autowired

    CreadorDeObjetos creadorDeObjetos;

    @Override
    public void run(String... args) throws Exception {

        cargarRecorridos();
        cargarParadas();
        cargarUsuarios();
        cargarMiembros();

    }


   public void cargarRecorridos() throws Exception
    {
        config.exposeIdsFor(Recorrido.class);
        if(repoRecorrido.count() == 0) {

            Organizacion organizacion1 = new Organizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
            Recorrido recorrido1 = new Recorrido(organizacion1,0.5, Calendario.crearFecha(1, 2020), Calendario.crearFecha(9, 2022));
            List<Recorrido> recorridos = List.of(recorrido1);
            recorridos.stream().forEach(recorrido -> {
                repoRecorrido.save(recorrido);
            });
            //De esta manera se carga el recorrido
            Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
            //CrearRecorrido.crearRecorrido(organizacion3,0.8,Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021),repoRecorrido);
        }
        else{
            System.out.println("Ya existen Miembros creados anteriormente");
        }
    }

    public void cargarParadas() throws Exception
    {
        config.exposeIdsFor(Usuario.class);
        if(repoParadas.count() == 0) {

            Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            Parada paradaPruebaUno = new Parada("120", ubicacionPruebaUno);
            Parada paradaPruebaDos = new Parada("50",ubicacionPruebaUno);
            List<Parada> paradas = List.of(paradaPruebaUno,paradaPruebaDos);
            paradas.stream().forEach(parada -> {repoParadas.save(parada);});
            CrearParada.crearParada("280",ubicacionPruebaUno,repoParadas);
            ArrayList<Parada> paradas1 = new ArrayList<>();
            paradas1.add(paradaPruebaUno);
        }
        else{
            System.out.println("Ya existen Miembros creados anteriormente");
        }
    }

    public void cargarUsuarios() throws Exception
    {
        config.exposeIdsFor(Usuario.class);
        if(repoUsuario.count() == 0) {

            Usuario usuario1 = new Usuario("prueba", "Yagni3210+", Rol.MIEMBRO);
            List<Usuario> usuarios = List.of(usuario1);
            usuarios.stream().forEach(usuario -> {
                repoUsuario.save(usuario);
            });
        }
        else{
            System.out.println("Ya existen Miembros creados anteriormente");
        }
    }

    //Solo a modo prueba es esto

    public void cargarMiembros() throws Exception
    {
        config.exposeIdsFor(Miembro.class);
        if(repoMiembros.count() == 0) {

            Miembro miembroPruebaUno = creadorDeObjetos.crearMiembro(20,"Pablo","Ortiz","pablo@prueba","2323");
        }
        else{
            System.out.println("Ya existen Miembros creados anteriormente");
        }
    }

    public void cargarOrganizaciones() throws Exception
    {
        config.exposeIdsFor(Organizacion.class);
        if(repoOrganizacion.count() == 0) {
            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
            Organizacion organizacion2 =creadorDeObjetos.crearOrganizacion("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false);
            Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
            Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false);

        }
        else{
            System.out.println("Ya existen Miembros creados anteriormente");
        }
    }
}

