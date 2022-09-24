package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.MiembroController;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.util.ArrayList;
import java.util.List;
@Component
public class RepositorioMiembros {

  @Autowired
  MiembroController miembrobd;
  private static RepositorioMiembros instance = new RepositorioMiembros();
  private List<Miembro> miembros;

  private RepositorioMiembros() {
    this.miembros = new ArrayList<>();
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }

  public void setMiembros(List<Miembro> miembros) {
    this.miembros = miembros;
  }

  public static RepositorioMiembros getRepositorio() {
    return instance;
  }

  public void agregarMiembro(Miembro miem){
    this.miembros.add(miem);
  }

  //TODO probar
  public void cargarDeMiembrosDeBdAlSistema() {
    for(Miembro miembroclase : miembrobd.getMiembros()) {
      Miembro miembro = new Miembro(miembroclase.getId(),miembroclase.getNombre(),miembroclase.getApellido(),miembroclase.getTipoDoc(),miembroclase.getNumDoc(),miembroclase.getAreas(),miembroclase.getRecorridos(),miembroclase.getMail(),miembroclase.getTelefono());
      this.agregarMiembro(miembro);
    }
  }
}