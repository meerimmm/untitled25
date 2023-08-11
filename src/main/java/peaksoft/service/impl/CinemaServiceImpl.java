package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Cinema;
import peaksoft.service.ServiceLayer;

import java.util.List;
@Service
@Transactional
public class CinemaServiceImpl implements ServiceLayer<Cinema> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cinema save(Cinema cinema) {
        entityManager.persist(cinema);
        return cinema;
    }

    @Override
    public Cinema findById(int id) {
        return entityManager.find(Cinema.class, id);
    }

    @Override
    public List<Cinema> findAll() {
        return (List<Cinema>) entityManager.createQuery("from Cinema").getResultList();
    }

    @Override
    public Cinema update(int id, Cinema cinema) {
        Cinema cinema1 = entityManager.find(Cinema.class, id);
        cinema1.setName(cinema.getName());
        cinema1.setAddress(cinema.getAddress());
        cinema1.setRooms(cinema.getRooms());
        entityManager.merge(cinema1);
        return cinema1;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Cinema.class, id));
    }

}

