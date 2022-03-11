package pl.robertprogramista.noticeboard.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findAll();
    Optional<Notice> findById(Integer id);
    Notice save(Notice notice);

    @Override
    void deleteById(Integer integer);
}
