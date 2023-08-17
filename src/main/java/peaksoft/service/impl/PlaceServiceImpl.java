package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Place;
import peaksoft.model.Room;
import peaksoft.service.ServiceLayer;

import java.util.List;

@Service
@Transactional

public class PlaceServiceImpl implements ServiceLayer<Place> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Place save(Place place) {
        Room room = entityManager.find(Room.class,place.getRoomId());
        place.setRoom(room);
        entityManager.persist(place);
        return place;
    }

    @Override
    public Place findById(int id) {
        return entityManager.find(Place.class, id);
    }

    @Override
    public List<Place> findAll() {
        return (List<Place>) entityManager.createQuery("from Place ").getResultList();
    }

    @Override
    public Place update(int id, Place place) {
        Place place1 = entityManager.find(Place.class, id);
        place1.setRow(place.getRow());
        place1.setPlace(place.getPlace());
        place1.setPrice(place.getPrice());
        place1.setRoom(place.getRoom());
        entityManager.merge(place1);
        return place1;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Place.class, id));
    }
    public List<Place>findAll(int id){
        return entityManager.createQuery("from Place r where r.room.id =:id", Place.class)
                .setParameter("id", id).getResultList();
    }


}
