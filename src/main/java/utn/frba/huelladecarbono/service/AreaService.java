package utn.frba.huelladecarbono.service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.repository.AreaRepository;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AreaService implements IAreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private OrganizacionRepository organizacionRepository;
    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    public Area findById(Integer id) throws Exception {
        return areaRepository.findById(id).get();
    }

    @Override
    public List<Area> getAreas() {
        List <Area> listaAreas = areaRepository.findAll();
        return listaAreas;
    }

    @Override
    public void saveArea(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void deleteArea(Integer id) {
        cambiarEstadoArea(id);
    }


    @Override
    public Area findArea(Integer id) {
        Area area = areaRepository.findById(id).orElse(null);
        return area;
    }

    @Override
    public void crearArea(String areaJson) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(areaJson);// json
        String nombre = (String) jObject.get("nombre");
        Integer organizacionID = Integer.parseInt((String) jObject.get("id"));
        Organizacion orgTemp = organizacionRepository.findById(organizacionID).get();
        Area areaMapeado = new Area(nombre, orgTemp);
        orgTemp.setAreas(null);
        areaRepository.save(areaMapeado);
    }

    @Override
    public List<Area> findAreaByEstadoActivo() {

        return areaRepository.findByEstaActivo(true);
    }


    public List<Area> findByOrganizacion(Integer idOrganizacion) {
        return areaRepository.findByEstaActivo(true).stream()
                .filter(area -> area.getOrganizacion().getId()==idOrganizacion)
                .collect(Collectors.toList());
    }

    @Override
    public void solicitarSerParte(Integer areaId, Integer miembroId) {
        Area area = areaRepository.findById(areaId).get();
        area.solicitarSerParte(miembroRepository.findById(miembroId).get());
        areaRepository.save(area);
    }

    @Override
    public void cambiarEstadoArea(Integer id) {
        Area area = findArea(id);
        area.setEstaActivo(false);

        this.saveArea(area);
    }

    @Override
    public Area modificarArea(Integer id, Area area) {
        return null;
    }


    @Override
    public Area crearArea(Area area) {
        areaRepository.save(area);
        return area;
    }




}
