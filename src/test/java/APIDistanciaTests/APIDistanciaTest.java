package APIDistanciaTests;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import heroku.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;
import BaseDeDatos.BaseDeDatos;
import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {APIDistanciaService.class})

public class APIDistanciaTest {
    BaseDeDatos BD = BaseDeDatos.getInstance();

    public APIDistanciaTest() throws Exception {
    }

    @Test
    public void testDistanciaValida() {
        Ubicacion ubicacion1Test = BD.getUbicaciones().get(0);
        Ubicacion ubicacion2Test = BD.getUbicaciones().get(1);
        APIDistanciaService distanciaService = new APIDistanciaService();

        Assertions.assertDoesNotThrow(() -> {
            Double distancia = distanciaService.medirDistancia(ubicacion1Test, ubicacion2Test);
            System.out.println("La distancia es: " + distancia);
        });
    }

}
