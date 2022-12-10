package utn.frba.huelladecarbono.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.parser.ParseException;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;


import java.util.List;

public interface IMiembroService {

    //metodo para obtener a todos los miembros
    public List<Miembro> getMiembros();

    //Metodo para dar de alta a un miembro
    public void saveMiembro(Miembro miembro);

    //Metodo para eliminar a un miembro
    public void deleteMiembro(Integer id);

    //Metodo para encontrar a un miembro
    public Miembro findMiembro(Integer id);

    //Metodo para actualizar la informacion de un miembro
    public Miembro modificarMiembro(Integer id, String miembroJson) throws JsonProcessingException, ParseException;

    void cambiarEstadoMiembro(Integer id);

}
