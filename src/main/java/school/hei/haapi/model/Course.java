package school.hei.haapi.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

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
public class Course {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotBlank(message = "ref should not be blank")
    private String ref;

    @Min(message = "must be positive", value = 1)
    private int credits;

    @Min(message = "", value = 1)
    private int totalHours;
}
