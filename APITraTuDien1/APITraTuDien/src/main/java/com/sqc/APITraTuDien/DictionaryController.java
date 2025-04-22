package com.sqc.APITraTuDien;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DictionaryController {

    private final Map<String, String> dictionaryMap = Map.ofEntries(
            Map.entry("apple", "quả táo"),
            Map.entry("banana", "quả chuối"),
            Map.entry("orange", "quả cam")
    );

    @GetMapping("/dictionary")
    public ResponseEntity<String> dictionary(@RequestParam(defaultValue = "") String word) {

        String translation = dictionaryMap.get(word.trim().toLowerCase());

        if(translation != null){
            return ResponseEntity.ok(translation);
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy từ này trong từ điển");
    }

}

