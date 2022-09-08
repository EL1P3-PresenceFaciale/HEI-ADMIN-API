package school.hei.haapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "\"course\"")
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

    @Column(name = "event_title")
    private String eventTitle ;

    @Column(name = "event_description")
    private String eventDescription ;

    @Column(name = "start_date_time")
    private Instant startDateTime ;

}
