package school.hei.haapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.haapi.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
  Student getByUserId(String userId);
}
