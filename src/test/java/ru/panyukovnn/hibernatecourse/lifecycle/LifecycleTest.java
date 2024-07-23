package ru.panyukovnn.hibernatecourse.lifecycle;

import org.junit.jupiter.api.Test;
import ru.panyukovnn.hibernatecourse.AbstractTest;
import ru.panyukovnn.hibernatecourse.reference.model.User;

public class LifecycleTest extends AbstractTest {

    @Test
    void lifecycleExample() {
        User user = new User(); // Transient

        transactionTemplate.executeWithoutResult(ts -> {
            entityManager.persist(user); // Persistent / Managed

//            entityManager.clear(); // Detached, очистили кеш PersistenceContext

            entityManager.remove(user); // Removed
        });
    }

    @Test
    void queriesAtTheEndOfTransaction() {
        User user = new User(); // Transient

        transactionTemplate.executeWithoutResult(ts -> {
            entityManager.persist(user); // Persistent / Managed

            entityManager.remove(user); // Removed

//            entityManager.flush(); // Выполняет накопившиеся в кеше запросы

            System.out.println("After all actions"); // Выполнение запросов происходит до
        });
    }

    @Test
    void lifecycleUpdateWithoutSaving() {
        User user = new User();
        user.setFirstName("John");
        userRepository.save(user);

        transactionTemplate.executeWithoutResult(ts -> {
            User fetchedUser = userRepository.findById(user.getId()).orElseThrow();

            fetchedUser.setFirstName("Tyrion");
        });

        System.out.println(userRepository.findById(user.getId()));

        userRepository.delete(user);
    }
}
