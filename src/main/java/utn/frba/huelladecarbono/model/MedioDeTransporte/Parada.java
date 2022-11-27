package utn.frba.huelladecarbono.model.MedioDeTransporte;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.Distancia;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;

    // aca hay un error
    @Transient
    private Ubicacion ubicacion;
    @Transient
    private Distancia distanciaAProximaParada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parada() {
    }

    public Parada(String nom, Ubicacion ubi){
        this.nombre = nom;
        this.ubicacion = ubi;
    }

    public Parada(String nombre, Ubicacion ubicacion, Distancia distanciaAProximaParada) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.distanciaAProximaParada = distanciaAProximaParada;
    }

    public Double distancaAProximaParada() {
        if (distanciaAProximaParada.getValor()!= 0) {
            return distanciaAProximaParada.getValor();
        }
        else{
            throw new RuntimeException("No hay siguiente parada (Parada Terminal)");
        }
    }

    @Override
    public String toString() {
        return "Parada{" +
                "ID='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ubicacion=" + ubicacion +
                ", distanciaAProximaParada=" + distanciaAProximaParada +
                '}';
    }
}
