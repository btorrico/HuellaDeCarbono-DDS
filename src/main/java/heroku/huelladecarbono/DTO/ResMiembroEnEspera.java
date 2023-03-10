package heroku.huelladecarbono.DTO;

import lombok.Getter;

@Getter
public class ResMiembroEnEspera {
    private ResMiembro miembro;
    private ResArea area;

    public ResMiembroEnEspera(MiembroEnEspera miembro) {
        this.miembro = new ResMiembro(miembro.getMiembro());
        this.area = new ResArea(miembro.getArea());
    }
}
