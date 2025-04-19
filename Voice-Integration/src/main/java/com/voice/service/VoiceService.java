package com.voice.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.voice.dto.CallRequestDto;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class VoiceService {

    private final String ACCOUNT_SID = "AC8d53e5b04d00db1bad3286636ed5dfa9";
    private final String AUTH_TOKEN = "0d81955139d35925c5fd572dffbb819d";
    private final String twilioNumber = "+15075800875";


    public void makeCall(String to) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Call call = Call
                .creator(
                        new PhoneNumber(to),
                        new PhoneNumber(twilioNumber),
                        new URI("http://demo.twilio.com/docs/voice.xml")
                )
                .create();

        System.out.println(call.getSid());
    }

    public String makeVoiceCall(CallRequestDto callRequestDto) {
        System.out.println("Progress................");
        System.out.println("Customer number " + callRequestDto.getCustomerPhone());
        System.out.println("Employee number " + callRequestDto.getEmployeePhone());
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Call call = Call.creator(
                new PhoneNumber(callRequestDto.getCustomerPhone()),
                new PhoneNumber(twilioNumber),
                URI.create("https://d44f-45-115-190-94.ngrok-free.app/api/voice/handler?employee=" + callRequestDto.getEmployeePhone())
        ).setRecord(true).create();
        System.out.println("Call generated................");
        return "Call started with SID: " + call.getSid();
    }
}
