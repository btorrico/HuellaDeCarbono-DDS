package utn.frba.huelladecarbono.model.CalculoDeDistancias.Strategies;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.APIDistanciaService;
import utn.frba.huelladecarbono.model.ManejoAmbiental.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;

public class StrategyVehiculoMotorizado extends StrategyTransporte{

    public Double calcularDistancia(Ubicacion ubicacion1,Ubicacion ubicacion2, Medio transporteMotorizado) throws Exception {
       return new APIDistanciaService().medirDistancia(ubicacion1, ubicacion2);
    }

}
