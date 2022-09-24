package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.repository.ParadaRepository;
import utn.frba.huelladecarbono.service.IParadaService;

import java.util.List;

@RestController
public class ParadaController {

    @Autowired
    private IParadaService interfazParada;

    @Autowired
    ParadaRepository paradaRepository;
    @GetMapping("/paradas")
    public List<Parada> getParadas(){
        return interfazParada.getParadas();
    }


    @DeleteMapping("parada/eliminar/{id}")
    public String deleteParada(@PathVariable Integer id) {
        interfazParada.deleteParada(id);
        return "La parada fue eliminada correctamente";
    }


    @PostMapping("/parada/crear")
    public String saveParada(@RequestBody Parada parada){
        interfazParada.saveParada(parada);
        return "La parada fue creada correctamente";
    }


}
