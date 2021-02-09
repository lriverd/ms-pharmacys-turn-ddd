package cl.duamit.location.infraestructure.provider.db.repository;

import cl.duamit.location.infraestructure.provider.db.model.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends QueryByExampleExecutor<Region>, CrudRepository<Region, String> {

    Iterable<Region> findAll();

    Optional<Region> findById(String id);
}
