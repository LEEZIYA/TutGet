package nucleus.tutget.service.listing.repository;

import nucleus.tutget.service.listing.model.Listing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingRepository extends CrudRepository<Listing, String> {

    public List<Listing> findByDescription(String description);

    public List<Listing> findByIdContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String key1, String key2);

    //public List<Listing> findByIdContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String key1, String key2);

}
