package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "places")
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int row;
    private int place;
    private int price;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
    CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
    @Transient
    private int roomId;

}
