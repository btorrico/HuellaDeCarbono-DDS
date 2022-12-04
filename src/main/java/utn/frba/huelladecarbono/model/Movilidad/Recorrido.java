package utn.frba.huelladecarbono.model.Movilidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Recorrido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    public List<Trayecto> trayectos = new ArrayList<>();
    @OneToOne
    private Organizacion organizacion;
    @Column
    private Double peso;
    @Column(columnDefinition = "DATE")
    private LocalDate mesInicio;
    @Column(columnDefinition = "DATE")
    private LocalDate mesFin;
    @Column
    private Boolean estaActivo;

    public Recorrido(Organizacion organizacion, Double peso, LocalDate mesInicio, LocalDate mesFin,Boolean estaActivo) {
        this.organizacion = organizacion;
        this.mesInicio = mesInicio;
        this.mesFin = mesFin;
        this.peso = peso;
        this.estaActivo = estaActivo;
    }

    public Recorrido(Integer id, ArrayList<Trayecto> trayectos, Organizacion organizacion, Double peso, LocalDate mesInicio, LocalDate mesFin) {
        this.id = id;
        this.trayectos = trayectos;
        this.organizacion = organizacion;
        this.peso = peso;
        this.mesInicio = mesInicio;
        this.mesFin = mesFin;
    }

    public Recorrido(Organizacion organizacion, Double peso, LocalDate mesInicio, LocalDate mesFin) {
        this.organizacion = organizacion;
        this.peso = peso;
        this.mesInicio = mesInicio;
        this.mesFin = mesFin;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recorrido() {
    }


    public void addTrayecto(Trayecto trayecto){
        this.trayectos.add(trayecto);
    }

    public void removeTrayecto(Trayecto trayecto){
        trayectos.remove(trayecto);
    }

    public Double distanciaTotal() throws Exception {
        Double distanciaTotal = Double.parseDouble("0");
        for (Trayecto trayecto : trayectos){
            distanciaTotal = distanciaTotal + trayecto.distanciaMedia();
        }
        return distanciaTotal;
    }

    @Override
    public String toString() {
        return "Recorrido{" +
                "id=" + id +
                ", trayectos=" + trayectos +
                ", organizacion=" + organizacion +
                ", peso=" + peso +
                ", mesInicio=" + mesInicio +
                ", mesFin=" + mesFin +
                '}';
    }
}

