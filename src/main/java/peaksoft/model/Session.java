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
        private LocalDateTime start;
        private LocalDateTime finish;
         private LocalDateTime duration;
        @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        @JoinColumn(name = "movie_id")
        private Movie movie;

        @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        @JoinTable(
                name = "room",
                joinColumns = @JoinColumn(name = "room_id"),
                inverseJoinColumns = @JoinColumn(name = "session_id")
        )
        private List<Room> rooms;

}
