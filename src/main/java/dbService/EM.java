package dbService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EM {

    private static EntityManager em = Persistence.createEntityManagerFactory("My").createEntityManager();

    public static EntityManager em() {
        return em;
    }

    public static void stopEm() {
        em.close();
    }
}
