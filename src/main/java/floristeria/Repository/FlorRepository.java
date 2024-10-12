package floristeria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import floristeria.Model.Flor;

@Repository
public interface FlorRepository extends JpaRepository<Flor, Long> {
}