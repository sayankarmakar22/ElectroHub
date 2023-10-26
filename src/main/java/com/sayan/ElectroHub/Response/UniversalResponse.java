package com.sayan.ElectroHub.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
public class UniversalResponse {

    private String message;
    private List<Object> response;
}
