package com.abbascoban.gallerist.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exception<E> {

    private String path;

    private Date createdTime;

    private String hostName;

    private E message;


}
