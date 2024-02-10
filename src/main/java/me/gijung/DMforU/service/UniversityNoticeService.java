package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Major;
import me.gijung.DMforU.model.entity.DepartmentNotice;
import me.gijung.DMforU.model.entity.UniversityNotice;
import me.gijung.DMforU.repository.UniversityNoticeRepository;
import me.gijung.DMforU.service.parser.UniversityNoticeParser;
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
}
