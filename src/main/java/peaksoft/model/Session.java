package peaksoft.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime start = LocalDateTime.now();
    private int duration;
    private LocalDateTime finish = LocalDateTime.now().plusHours(duration);
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Transient
    private int movieId;
    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private List<Room> rooms;
    @Transient
    private int roomId;

}
