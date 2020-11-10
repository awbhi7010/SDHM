package bqtest.web;

import bqtest.service.Member;
import bqtest.service.MemberImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class FileController {

    /*
    Don't modify this class
     */

    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private final MemberImporter fileProcessor;

    public FileController(MemberImporter fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @GetMapping(value = "/api/load-data")
    public Map<String, List<Member>> loadData(@Value("${config.file-to-load}") String fileToLoad) throws Exception {

        log.info("Got an request to load file {}", fileToLoad);

        File uploadedFile = new File(fileToLoad);

        Map<String, List<Member>> groupedMembers = fileProcessor.processFile(uploadedFile);

        log.info("Successfully grouped to {} states ", groupedMembers.keySet().size());

        return groupedMembers;
    }
}
