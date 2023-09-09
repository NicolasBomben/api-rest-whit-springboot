package med.voll.api.medico;

public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String emial) {
    
    /*cosntructor para traer lo solicidato por el modelo de negocio */
    public DatosListadoMedico(Medico medico){
        this(
            medico.getId(),
            medico.getNombre(),
            medico.getEspecialidad().toString(),
            medico.getDocumento(),
            medico.getEmail()
        );
    }
}
