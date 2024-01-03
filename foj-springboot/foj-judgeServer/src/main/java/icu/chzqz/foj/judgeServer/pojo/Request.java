package icu.chzqz.foj.judgeServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    String requestId;
    Cmd[] cmd;
    PipeMap[] pipeMapping;
}
