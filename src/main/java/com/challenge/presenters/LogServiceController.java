package com.challenge.presenters;

import com.challenge.domain.usecase.LogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/log")
@AllArgsConstructor
public class LogServiceController {

    private final LogService logService;

    @PostMapping("")
    public ResponseEntity<Void> logService() {
        logService.linkedLineCommonLog();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
