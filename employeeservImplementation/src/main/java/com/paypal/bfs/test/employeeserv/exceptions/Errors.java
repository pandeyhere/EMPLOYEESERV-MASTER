package com.paypal.bfs.test.employeeserv.exceptions;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Errors {

    public String field;
    public String message;

}
