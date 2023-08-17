package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String rating;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @Transient
    private int cinemaId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", fetch = FetchType.LAZY)
    private List<Place> places;

    @ManyToMany(cascade =
            {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "session",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Session> sessions;

}

