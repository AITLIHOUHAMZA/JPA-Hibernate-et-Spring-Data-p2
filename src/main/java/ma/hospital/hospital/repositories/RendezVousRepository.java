package ma.hospital.hospital.repositories;

import ma.hospital.hospital.entities.Medecin;
import ma.hospital.hospital.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {

}
