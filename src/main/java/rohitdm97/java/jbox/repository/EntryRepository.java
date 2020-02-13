package rohitdm97.java.jbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rohitdm97.java.jbox.model.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, String> {
}
