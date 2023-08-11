package peaksoft.model;

import jakarta.persistence.*;
import lombok.Data;

    @Data
    @Entity
    @Table(name = "places")
    public class Place {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private int x;
        private int y;
        private int price;

        @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        @JoinColumn(name = "room_id")
        private Room room;

    }
