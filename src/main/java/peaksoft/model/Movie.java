package peaksoft.model;

import jakarta.persistence.*;
import lombok.Data;
import peaksoft.enums.Genres;
import peaksoft.enums.Languages;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Genres genres;
    @Column(name = "created_date")
    private LocalDate createdDate;
    private String country;
    @Enumerated(EnumType.STRING)
    private Languages language;
    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.DETACH}, mappedBy = "movie")
    private List<Session> sessions;
}

