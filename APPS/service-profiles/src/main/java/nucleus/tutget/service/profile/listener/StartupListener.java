package nucleus.tutget.service.profile.listener;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import nucleus.tutget.service.profile.model.Profile;
import nucleus.tutget.service.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StartupListener {

  @Autowired
  private ProfileService profileService;

  @EventListener
  public void onApplicationReady(ApplicationReadyEvent event) throws IOException {
    // Data initialisation
    ClassPathResource resource = new ClassPathResource("/seeders/profile.seeders.json");
    String json = resource.getContentAsString(StandardCharsets.UTF_8);

    // Convert from string json to Profile class
    Gson gson = new Gson();
    TypeToken<List<Profile>> collectionType = new TypeToken<List<Profile>>(){};
    var jsonArray = gson.fromJson(json, collectionType);

    // Add Data
    jsonArray
      .forEach(
        jsonElement -> profileService.addProfile(jsonElement)
      );

    System.out.println("Data initialised");
  }
}
