package nucleus.tutget.service.profile.repository;

import nucleus.tutget.service.profile.model.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, String> {

    public List<Profile> findByDescription(String description);

    public List<Profile> findByFirstName(String firstName);

    public List<Profile> findByLastName(String lastName);

    public List<Profile> findByUserID(String userID);

}
