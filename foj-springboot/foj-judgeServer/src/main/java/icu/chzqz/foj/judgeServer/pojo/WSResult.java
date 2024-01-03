package icu.chzqz.foj.judgeServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WSResult {
    String requestId;
    QuestionResult[] results;
    String error;
}
