package heroku.huelladecarbono.DTO;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResDatoDeActividad {
    private ResArea area;
    private LocalDate fecha;

    public ResDatoDeActividad(Area area, LocalDate fecha) {
        this.area = new ResArea(area);
        this.fecha = fecha;
    }
}
