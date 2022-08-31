package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;

import java.util.Calendar;
import java.util.Date;

public class CalculadoraHCMiembro {

    public static Double calcularHC(Miembro miembro, Calendar mesInicio, Calendar mesFin) throws Exception {
        Double HC = 0.0;
        for(Recorrido recorrido : miembro.getRecorridos()) {
            if(Calendario.estaEntre(mesInicio, mesFin, recorrido.getMesInicio()))
            HC += calcularHCRecorrido(recorrido);
        }
        //Se multiplica por 20 para generar el impacto de un mes
        return HC * 20;
    }

    private static Double calcularHCRecorrido(Recorrido recorrido) throws Exception {
        Double HC = 0.0;
        for(Trayecto trayecto : recorrido.getTrayectos()) {
            HC += calcularHCTrayecto(trayecto) * trayecto.getPeso() / trayecto.getPasajeros().size();
        }
        return HC;
    }

    private static Double calcularHCTrayecto(Trayecto trayecto) throws Exception {
        FactoresDeEmision FE = FactoresDeEmision.getInstance();
        System.out.println(FE.getFE("AUTO"));
        return trayecto.distanciaMedia() * FE.getFE(trayecto.getMedioTransporte().getTipo()) / trayecto.getPasajeros().size();

    }

//    //Falta Implementar peso
//    public static Double calcularHC(Trayecto trayecto) {
//        Double HC = 0.0;
//        Date fechaI = trayecto.getFechaDeInicio();
//        Date fechaF = trayecto.getFechaDeFin();
//        int mesInicio = fechaI.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
//        int mesFin = fechaF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
//        int cantMeses = mesFin - mesInicio;
//        for(int i= 0; i<cantMeses; i++) {
//            List<Miembro> pasajeros = trayecto.getPasajeros();
//            for(Miembro pasajero: pasajeros) {
//                HC += calcularHCMensual(pasajero);
//
//            }
//        }
//        return HC;
//    }

    public static Double calcularImpactoIndividual(Miembro miembro, Organizacion organizacion, Calendar mesInicio, Calendar mesFin) throws Exception {
        Double HCMiembro;
        Double HCOrganizacion;
        Double promedio;
        Double impacto;
        //  Double k = CalcularHuellaDeCarbono.getCalculadora().getK();

        HCOrganizacion = CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(organizacion, mesInicio, mesFin);
        //TODO: Asociar recorridos a las organizaciones donde se usan
        HCMiembro = CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin);
        promedio = HCOrganizacion / organizacion.getMiembros().size();
        impacto = (HCMiembro * promedio) / 100;

        System.out.println("La Huella de carbono de su compañía es: " + HCOrganizacion +". Su Huella de carbono"
                +" individual es: " + HCMiembro + ". Eso equivale a un: " +impacto+ "% de la Huella de carbono de " +
                "organización");
        return impacto;
    }
}
