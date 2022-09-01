package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCService;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioTrayectos;
import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Organizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String razonSocial;
    @Enumerated(EnumType.STRING)
    private TipoOrg tipo;
    @Transient
    private Ubicacion ubicacion;

    //TODO cambiar esto porque así no lo persiste
    @Transient
    private ArrayList<Area> areas;
    @Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;
    @Transient
    private ArrayList<Miembro> contactosMail = null;
    @Transient
    private ArrayList<Miembro> contactosWP = null;
    @Transient
    private ArrayList<Double> hcMensual = new ArrayList<>();

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public TipoOrg getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrg tipo) {
        this.tipo = tipo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public ArrayList<Miembro> getContactosMail() {
        return contactosMail;
    }

    public ArrayList<Miembro> getContactosWP() {
        return contactosWP;
    }

    public void setContactosWP(ArrayList<Miembro> contactosWP) {
        this.contactosWP = contactosWP;
    }

    public void setContactosMail(ArrayList<Miembro> contactos) {
        this.contactosMail = contactos;
    }
    public void agregarContactoMail(Miembro Contacto){
        contactosMail.add(Contacto);
    }

    public void agregarContactoWP(Miembro Contacto){
        contactosWP.add(Contacto);
    }

    public Organizacion() {
    }

    public Organizacion(Integer id, String razonSocial, TipoOrg tipo, Ubicacion ubicacion, ArrayList<Area> areas, Clasificacion clasificacion, ArrayList<Miembro> contactosMail, ArrayList<Miembro> contactosWP) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.areas = areas;
        this.clasificacion = clasificacion;
        this.contactosMail = contactosMail;
        this.contactosWP = contactosWP;
    }

    public Organizacion(String razonSocial, TipoOrg tipo, Ubicacion ubicacion, ArrayList<Area> areas, Clasificacion clasificacion, ArrayList<Miembro> contactosMail, ArrayList<Miembro> contactosWP) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.areas = areas;
        this.clasificacion = clasificacion;
        this.contactosMail = contactosMail;
        this.contactosWP = contactosWP;
    }


    public List<Miembro> getMiembros(){
        List<Miembro> miembros = new ArrayList<>();
        for (Area area: areas){
            for (Miembro miembro: area.getMiembros()){
                miembros.add(miembro);
            }
        }
        return miembros;
    }

    public void agregarSector(Area area){
        this.areas.add(area);
    }

    public void generarTrayecto(Ubicacion salida, Ubicacion llegada, Medio medio){
        Trayecto nuevoTrayecto = new Trayecto(salida, llegada, medio);
        RepositorioTrayectos.getRepositorio().agregarTrayecto(nuevoTrayecto);
    }

    public List<List<DatoDeMedicion>> getMediciones(){
        List<List<DatoDeMedicion>> medicionesOrga = new ArrayList<>();
        for (Area area : this.areas){
            for (List<DatoDeMedicion> datoDeMedicion : area.getMediciones()){
                medicionesOrga.add(datoDeMedicion);
            }
        }
        return medicionesOrga;
    }

    //La idea es que cada mes se agrega el del mes correspondiente
    //Cuando vuelva a ser enero, se limpia esa lista con HC mensuales
    public void agregarHCMensual() throws Exception{
        Double valor = 0.0;
        List<Miembro> miembrosOrg = this.getMiembros();
        Calendar mes = Calendario.fechaActual();
        for(Miembro miembro : miembrosOrg){
            valor += CalculadoraHCService.getCalculadoraHC().calcularHCMensual(miembro, mes);
        }
        this.hcMensual.add(valor);
    }

    public Double calculoHCAnual(){
        Double valor = 0.0;
        for(Double hcMensual : this.hcMensual){
            valor += hcMensual;
        }
        return valor;
    }
}