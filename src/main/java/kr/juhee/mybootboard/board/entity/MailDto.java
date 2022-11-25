package kr.juhee.mybootboard.board.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailDto {
    private String address;
    private String title;
    private String message;
}
