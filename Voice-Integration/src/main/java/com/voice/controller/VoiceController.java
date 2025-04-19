package com.voice.controller;


import com.voice.dto.CallRequestDto;
import com.voice.service.VoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class VoiceController {

    private final VoiceService voiceService;

    @PostMapping("/ivr_call")
    public ResponseEntity<String> ivrCall(String phoneNumber) throws URISyntaxException {
        voiceService.makeCall(phoneNumber);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/voice/call")
    public ResponseEntity<String> voiceCall(@RequestBody CallRequestDto callRequestDto) {

        return ResponseEntity.ok(voiceService.makeVoiceCall(callRequestDto));
    }

    @PostMapping(value = "/voice/handler", produces = MediaType.APPLICATION_XML_VALUE)
    public String handleCall(@RequestParam String employee) {

        System.out.println("Voice handler called " + employee);

        return """
        <?xml version="1.0" encoding="UTF-8"?>
        <Response>
            <Say voice="alice">Connecting you to your agent now.</Say>
            <Dial record="record-from-answer-dual">
                <Number>%s</Number>
            </Dial>
        </Response>
        """.formatted(employee);
    }

    @PostMapping("/voice/recording-status")
    public ResponseEntity<Void> recordingStatus(@RequestParam String CallSid,
                                                @RequestParam String RecordingUrl,
                                                @RequestParam String To,
                                                @RequestParam String From) {
        System.out.println("recording status api called");
        System.out.println("recording url " + RecordingUrl);
        // Save call log and recording URL
        return ResponseEntity.ok().build();
    }


    @GetMapping("/get")
    public ResponseEntity<String> run() {
        System.out.println("Progresss................")
        return ResponseEntity.ok("Success");
    }
}
