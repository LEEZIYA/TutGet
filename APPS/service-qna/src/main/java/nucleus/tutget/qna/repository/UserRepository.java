package nucleus.tutget.qna.repository;

import java.util.ArrayList;
import java.util.List;

import nucleus.tutget.qna.domain.User;
import nucleus.tutget.qna.domain.UserType;

public class UserRepository {
    private List<User> usersList;

    // stub with placeholder data. TODO change to database commands
    public UserRepository() {
        usersList = new ArrayList<>();
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public User getUserById(String userId) {
        for (User user: usersList) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        return null;
    }
}
