package school.hei.haapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "\"event\"")
@Getter
@Setter
//@Data : getter et setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id ;

    @Column(name = "event_name")
    private String eventName ;

    @Column(name = "event_description")
    private String eventDescription ;

    @Column(name = "start_time")
    private Instant startTime ;

    @Column(name = "ending_time")
    private Instant endingTime;

    @ManyToOne
    private Place place ;

    @ManyToOne
    private User eventResponsible;
}
