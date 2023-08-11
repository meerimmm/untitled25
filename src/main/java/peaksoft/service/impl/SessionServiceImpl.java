package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Session;
import peaksoft.service.ServiceLayer;

import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements ServiceLayer<Session>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Session save(Session session) {
        entityManager.persist(session);
        return session;
    }

    @Override
    public Session findById(int id) {
        return entityManager.find(Session.class, id);
    }

    @Override
    public List<Session> findAll() {
        return (List<Session>) entityManager.createQuery("from Session").getResultList();
    }

    @Override
    public Session update(int id, Session session) {
        Session session1 = entityManager.find(Session.class, id);
        session1.setName(session.getName());
        session1.setStart(session.getStart());
        session1.setFinish(session.getFinish());
        session1.setDuration(session.getDuration());
        session1.setRooms(session.getRooms());
        entityManager.merge(session1);
        return session1;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Session.class, id));
    }

}
