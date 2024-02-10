package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.entity.UniversityNotice;
import me.gijung.DMforU.repository.UniversityNoticeRepository;
import me.gijung.DMforU.service.parser.UniversityNoticeParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityNoticeService {

    private final UniversityNoticeParser parser;
    private final UniversityNoticeRepository universityNoticeRepository;

    @Scheduled(cron = "0 0 10, 17 * * MON-FRI")
    public void crawling() {
        parser.setPageNumber(1);
        Integer maxNumber = universityNoticeRepository.findByMaxNumber();
        int currentMaxNumber = maxNumber != null ? maxNumber : 0;

        while (true) {
            List<UniversityNotice> departmentNotices = parser.Parsing();
            boolean isNewNoticeFound = saveNewNotices(departmentNotices, currentMaxNumber);

            if (!isNewNoticeFound) {
                return;
            }
        }
    }

    public List<NoticeDto> findUniversityNotices(int page, int size) {
        Page<UniversityNotice> universityNoticePage = universityNoticeRepository.findAll(
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "number")));
        return universityNoticePage.map(this::mapToDto).stream().toList();
    }

    private boolean saveNewNotices(List<UniversityNotice> universityNotices, int currentMaxNumber) {
        for (UniversityNotice universityNotice : universityNotices) {
            if (universityNotice.getNumber() <= currentMaxNumber) {
                return false;
            }

            universityNoticeRepository.save(universityNotice);

            if (universityNotice.getNumber() == 1) {
                return false;
            }
        }

        return true;
    }

    private NoticeDto mapToDto(UniversityNotice universityNotice) {
        return NoticeDto.builder()
                .date(universityNotice.getDate())
                .title(universityNotice.getTitle())
                .author(universityNotice.getAuthor())
                .url(universityNotice.getUrl())
                .build();
    }
}
