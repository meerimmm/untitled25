package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Movie;
import peaksoft.service.ServiceLayer;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements ServiceLayer<Movie> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Movie save(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @Override
    public Movie findById(int id) {
        return entityManager.find(Movie.class, id);
    }

    @Override
    public List<Movie> findAll() {
        return (List<Movie>) entityManager.createQuery("from Movie").getResultList();
    }

    @Override
    public Movie update(int id, Movie movie) {
        Movie movie1 = entityManager.find(Movie.class, id);
        movie1.setName(movie.getName());
        movie1.setCountry(movie.getCountry());
        movie1.setGenres(movie.getGenres());
        movie1.setCreatedDate(movie.getCreatedDate());
        movie1.setLanguage(movie.getLanguage());
        movie1.setSessions(movie.getSessions());
        entityManager.merge(movie1);
        return movie1;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Movie.class, id));
    }

}
