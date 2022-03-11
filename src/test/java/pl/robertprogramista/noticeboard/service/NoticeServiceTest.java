package pl.robertprogramista.noticeboard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.robertprogramista.noticeboard.model.Notice;
import pl.robertprogramista.noticeboard.model.NoticeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NoticeServiceTest {
    private NoticeService noticeService;
    private NoticeRepository repository;

    @BeforeEach
    void setUp() {
        repository = getNoticeRepository();
        noticeService = new NoticeService(repository);
    }

    @Test
    void create() {
        //given
        Notice notice = getNotice(0);

        //when
        noticeService.create(notice);

        //then
        assertEquals(repository.getById(0), notice);
        assertEquals(repository.count(), 1L);
    }

    @Test
    void update() {
        //given
        Notice notice = getNotice(0);
        noticeService.create(notice);
        notice.setDescription("abcd");

        //when
        noticeService.update(notice);

        //then
        assertEquals(repository.getById(0), notice);
        assertEquals(repository.count(), 1L);
        assertEquals(repository.getById(0).getDescription(), "abcd");
    }

    @Test
    void findAll() {
        //given
        Notice notice1 = getNotice(0);
        Notice notice2 = getNotice(1);
        noticeService.create(notice1);
        noticeService.create(notice2);

        //when
        List<Notice> notices = noticeService.findAll();

        //then
        assertEquals(repository.getById(0), notice1);
        assertEquals(repository.getById(1), notice2);
        assertEquals(notices.size(), 2);
    }

    @Test
    void remove() {
        //given
        Notice notice = getNotice(0);
        noticeService.create(notice);

        //when
        noticeService.remove(notice);

        //then
        assertEquals(repository.count(), 0);
    }

    private Notice getNotice(int id) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setTheme("orange");
        notice.setDescription("test test test");
        notice.setPositionLeft("0");
        notice.setPositionTop("0");
        return notice;
    }

    private NoticeRepository getNoticeRepository() {
        return new NoticeRepository() {
            private List<Notice> notices = new ArrayList<>();

            @Override
            public List<Notice> findAll() {
                return notices;
            }

            @Override
            public Optional<Notice> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public Notice save(Notice notice) {
                notices.add(notice);
                return notice;
            }

            @Override
            public void deleteById(Integer noticeId) {
                notices.removeIf(notice -> notice.getId() == noticeId);
            }

            @Override
            public List<Notice> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Notice> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public <S extends Notice> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Notice> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Notice> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Notice> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Notice getOne(Integer integer) {
                return null;
            }

            @Override
            public Notice getById(Integer noticeId) {
                return notices.stream().filter(notice -> notice.getId() == noticeId).collect(Collectors.toList()).get(0);
            }

            @Override
            public <S extends Notice> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Notice> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Notice> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public long count() {
                return notices.size();
            }

            @Override
            public void delete(Notice entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends Notice> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Notice> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Notice> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Notice> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Notice> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Notice, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }
}