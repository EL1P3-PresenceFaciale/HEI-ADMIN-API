package school.hei.haapi.model;

import lombok.*;

import javax.persistence.*;

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

public class EventParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idEventParticipant ;

    @ManyToOne
    private User eventParticipant ;

    @ManyToOne
    private Event eventToAttend ;

    @Transient
    private school.hei.haapi.endpoint.rest.model.EventParticipant.StatusEnum status ;

}
